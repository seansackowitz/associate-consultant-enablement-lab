package com.rhc.lab.service;

import java.util.List;

import com.rhc.lab.domain.Login;

public interface LoginService {
	/**
	 * Builds session that is needed for the rules session.
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public List<Object> buildSession(Login login) throws Exception;

	
	/**
	 * Returns the login mentioned in the request
	 * 
	 * @param request
	 * @return
	 */
	public List<Object> collectSingleLoginForSession(Login request);

	/**
	 * This method returns all logins in the collection
	 * 
	 * @return
	 */
	
//	public List<Venue> getVenues();
//	/**
//	 * Get Venue by Id. Id is found in the header named id
//	 * 
//	 * @param id
//	 */

}
