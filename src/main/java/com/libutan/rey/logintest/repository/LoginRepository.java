package com.libutan.rey.logintest.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.libutan.rey.logintest.entity.Login;
import com.libutan.rey.logintest.entity.LoginPK;

@Repository
public interface LoginRepository extends CrudRepository<Login, LoginPK>, LoginCustomRepository {

	@Query("select distinct cast(l.loginTime as date) from Login l")
	public List<Date> findUniqueDates();
}