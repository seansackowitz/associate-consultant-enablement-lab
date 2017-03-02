package com.rhc.lab.service;

import java.util.List;

import com.rhc.lab.domain.Login;

public interface LoginService {
	/**
	 * Returns success or fail message for login mentioned in the request
	 * 
	 * @param request
	 * @return
	 */
	public String authenticateUser(Login request);
}
