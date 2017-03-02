package com.rhc.lab.test.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rhc.lab.dao.LoginRepository;
import com.rhc.lab.domain.Login;

import com.rhc.lab.domain.Login;

public class LoginCucumberRepository implements LoginRepository {

	private Map<String, Login> loginRepo = new HashMap<String, Login>();

	public LoginCucumberRepository() {

	}

	@Override
	public Iterable<Login> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Login> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Login arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Login> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean exists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Login> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Login> findAll(Iterable<String> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login findOne(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Login> S save(S login) {
		loginRepo.put(login.getEmail(), login);
		return login;
	}

	@Override
	public <S extends Login> Iterable<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Login findByEmail(String email) {
		return loginRepo.get(email);
	}

}
