package com.rhc.lab.service.impl;

import javax.annotation.Resource;

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

@Service("loginService")
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
	 * Check if db contains login and if login is valid
	 * 
	 * @param login
	 */
	@Override
	public String authenticateUser(Login login) {

		// logger.info("login email: " + login.toString());

		String loginMsg;
		// find login by email
		Login foundLogin = loginRepo.findByEmail(login.getEmail());

		// if login is not found return fail message
		// if login is found check if password correct
		if (foundLogin == null) {
			loginMsg = "Login Fail null";
		} else {
			System.out.println("found " + foundLogin.getEmail());
			// if password correct return success message
			// else if password incorrect return fail message
			if (login.getPassword().equals(foundLogin.getPassword())) {
				loginMsg = "Login Success";
			} else {
				loginMsg = "Login Fail pw" + foundLogin.getPassword();
			}
		}

		return loginMsg;
	}
}
