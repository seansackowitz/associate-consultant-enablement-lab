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
	 * Function returns a Login object by attribute "email"
	 * 
	 * @param email
	 * @return
	 */
	public Login findByEmail(String email);

}
