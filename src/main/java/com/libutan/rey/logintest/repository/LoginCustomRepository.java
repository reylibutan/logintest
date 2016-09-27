package com.libutan.rey.logintest.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface LoginCustomRepository {

	public List<String> findUniqueUsers(Date start, Date end);

	public List<Map<String, Long>> findUserLogins(Date start, Date end, String attribute1, String attribute2, String attribute3, String attribute4);
}