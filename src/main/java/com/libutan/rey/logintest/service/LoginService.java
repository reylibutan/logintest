package com.libutan.rey.logintest.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.libutan.rey.logintest.dto.LoginRequest;

public interface LoginService {

	public List<Date> getUniqueDates();

	public List<String> getUniqueUsers(LoginRequest loginRequest);

	public List<Map<String, Long>> getUserLogins(LoginRequest loginRequest);
}