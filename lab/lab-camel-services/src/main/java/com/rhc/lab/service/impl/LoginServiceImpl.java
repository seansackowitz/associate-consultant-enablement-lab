package com.rhc.lab.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rhc.lab.dao.LoginRepository;
import com.rhc.lab.domain.Login;
import com.rhc.lab.service.LoginService;

/**
 *
 * This service is used by Camel to execute rules and call saves in the various
 * DAO repositories.
 *
 */

@Service("loginRequestService")
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory
			.getLogger(LoginServiceImpl.class);
	@Resource(name = "loginDao")
	LoginRepository loginRepo;

	public LoginServiceImpl() {

	}

	public LoginServiceImpl(LoginRepository loginRepo) {
		super();
		this.loginRepo = loginRepo;
	}

	/**
	 * Builds session that is needed for the rules session.
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Object> buildSession(@Body Login login) throws Exception {
		// collect all the relevant data for the ksession
		List<Object> facts = collectSingleLoginForSession(login);
		// post the facts to the body of the exchange
		// put on the in so we do not lose the headers
		return facts;
	}

	/**
	 * Returns the loginInfo mentioned in the request
	 *
	 * @param request
	 * @return
	 */
	@Override
	public List<Object> collectSingleLoginForSession(Login request) {
		logger.info("collect login email: " + request.getEmail());
		List<Login> loginInfo= loginRepo.findByEmail(request.getEmail());

		logger.info("email found:" + loginInfo);

		List<Object> facts = new ArrayList<Object>();
		facts.addAll(loginInfo);
		facts.add(request);
		return facts;
	}

	/**
	 * Get login by email. email is found ?
	 *
	 * @param id
	 */
	@Override
	public Login getLoginByEmail(String email) {
		return loginRepo.findByEmail(email);
	}
}
