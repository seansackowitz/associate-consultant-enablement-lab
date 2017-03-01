package com.rhc.lab.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.rhc.lab.domain.Login;

/**
 * 
 * This interface extends the Spring-Data-MongoDB interface, exposing base
 * MongoDB operations and additional field level queries for the logins
 * collection
 * 
 */
@Repository(value = "loginDao")
public interface LoginRepository
		extends
			PagingAndSortingRepository<Login, String> {

	/**
	 * Function returns a list of Logins by attribute "email" (expected return
	 * is list size of 1)
	 * 
	 * @param email
	 * @return
	 */
	public List<Login> findByEmail(String email);

}
