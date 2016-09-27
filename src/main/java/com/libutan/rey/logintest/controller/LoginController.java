package com.libutan.rey.logintest.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.libutan.rey.logintest.dto.LoginRequest;
import com.libutan.rey.logintest.service.LoginService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

	private LoginService loginService;

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public LoginController(LoginService dateService) {
		this.loginService = dateService;
	}

	@RequestMapping(value = "/dates", method = RequestMethod.GET)
	public List<Date> getUniqueDates() {
		return loginService.getUniqueDates();
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getUniqueUsers(@Valid LoginRequest loginRequest, BindingResult result) {
		if (result.hasErrors()) {
			// TODO
			//		- include all error messages in the response
			//		- create a better and more uniform response
			for (ObjectError error : result.getAllErrors()) {
				LOG.warn(error.getDefaultMessage());
			}

			return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<String>>(loginService.getUniqueUsers(loginRequest), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/logins", method = RequestMethod.GET)
	public ResponseEntity<List<Map<String, Long>>> getUserLogins(@Valid LoginRequest loginRequest, BindingResult result) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				LOG.warn(error.getDefaultMessage());
			}

			return new ResponseEntity<List<Map<String, Long>>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Map<String, Long>>>(loginService.getUserLogins(loginRequest), HttpStatus.OK);
		}
	}
}