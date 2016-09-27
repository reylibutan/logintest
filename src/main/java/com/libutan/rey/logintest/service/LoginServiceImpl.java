package com.libutan.rey.logintest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.libutan.rey.logintest.dto.LoginRequest;
import com.libutan.rey.logintest.repository.LoginRepository;

@Service
@Transactional(readOnly = true)
public class LoginServiceImpl implements LoginService {

	private LoginRepository loginRepo;

	public LoginServiceImpl() {

	}

	@Autowired
	public LoginServiceImpl(LoginRepository loginRepo) {
		this.loginRepo = loginRepo;
	}

	@Override
	public List<Date> getUniqueDates() {
		return loginRepo.findUniqueDates();
	}

	@Override
	public List<String> getUniqueUsers(LoginRequest loginRequest) {
		return loginRepo.findUniqueUsers(loginRequest.getStartDate(), loginRequest.getEndDate());
	}

	@Override
	public List<Map<String, Long>> getUserLogins(LoginRequest loginRequest) {
		return loginRepo.findUserLogins(loginRequest.getStartDate(), loginRequest.getEndDate(), loginRequest.getAttribute1(),
				loginRequest.getAttribute2(), loginRequest.getAttribute3(), loginRequest.getAttribute4());
	}
}
