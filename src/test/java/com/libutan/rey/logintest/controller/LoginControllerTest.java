package com.libutan.rey.logintest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.libutan.rey.logintest.LogintestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LogintestApplication.class})
public class LoginControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private DataSource dataSource;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@After
	public void cleanup() {
		try {
			// in case some tests fail and @Sql AFTER_TEST_METHOD is not executed
			ScriptUtils.executeSqlScript(dataSource.getConnection(), new ByteArrayResource("DELETE FROM login WHERE \"user\" LIKE '@@@%';".getBytes()));
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetUniqueDates() {
		/*
		 * TODO
		 * 		- If I test this without mocking, I don't have any means to 'filter' the results to only return test data
		 * 			since this endpoint doesn't have any filters
		 * 		- Deleting all records in the table is also not an option since I want to run all of the tests in a way that the DB state is preserved
		 */
	}


	@Test
	@Sql("classpath:insert-data.sql")
	@Sql(scripts = "classpath:cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetUniqueUsers_StartAndEndDatesPresent() throws Exception {
		String url = "/users?start=10000101&end=10000101";
		String expected = "[\"@@@Test1\",\"@@@Test2\"]";
		mockMvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(content().json(expected, true))
		.andDo(print())
		.andReturn();

		url = "/users?start=10000101&end=10000103";
		expected = "[\"@@@Test1\",\"@@@Test2\",\"@@@Test3\",\"@@@Test4\"]";
		mockMvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(content().json(expected, true))
		.andDo(print())
		.andReturn();
	}

	@Test
	@Sql("classpath:insert-data.sql")
	@Sql(scripts = "classpath:cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetUniqueUsers_OnlyStartDatePresent() throws Exception {
		String url = "/users?start=99990101";
		String expected = "[\"@@@Test999\"]";
		mockMvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(content().json(expected, true))
		.andDo(print())
		.andReturn();
	}

	@Test
	@Sql("classpath:insert-data.sql")
	@Sql(scripts = "classpath:cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetUniqueUsers_OnlyEndDatePresent() throws Exception {
		String url = "/users?end=10000101";
		String expected = "[\"@@@Test1\",\"@@@Test2\"]";
		mockMvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(content().json(expected, true))
		.andDo(print())
		.andReturn();
	}

	@Test
	public void testGetUniqueUsers_InvalidStartDate() throws Exception {
		String url = "/users?start=aaaa";

		mockMvc.perform(get(url))
		.andExpect(status().is4xxClientError())
		.andDo(print())
		.andReturn();
	}

	@Test
	public void testGetUniqueUsers_InvalidEndDate() throws Exception {
		String url = "/users?end=aaaa";

		mockMvc.perform(get(url))
		.andExpect(status().is4xxClientError())
		.andDo(print())
		.andReturn();
	}

	@Test
	@Sql("classpath:insert-data.sql")
	@Sql(scripts = "classpath:cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetUserLogins_AllFitersPresent() throws Exception {
		String url = "/logins?start=10000101&end=10000101&attribute1=1&attribute2=1&attribute3=1&attribute4=1";
		String expected = "[{\"@@@Test1\":1}]";

		mockMvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(content().json(expected, true))
		.andDo(print())
		.andReturn();
	}

	@Test
	@Sql("classpath:insert-data.sql")
	@Sql(scripts = "classpath:cleanup-data.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetUserLogins_SomeFitersPresent() throws Exception {
		String url = "/logins?start=88880101&end=88880101&attribute1=888&attribute2=888&attribute3=888";
		String expected = "[{\"@@@Test888.1\":1},{\"@@@Test888.2\":1}]";

		mockMvc.perform(get(url))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(content().json(expected, true))
		.andDo(print())
		.andReturn();
	}

	@Test
	public void testGetUserLogins_InvalidStartDate() throws Exception {
		String url = "/logins?start=aaaa";

		mockMvc.perform(get(url))
		.andExpect(status().is4xxClientError())
		.andDo(print())
		.andReturn();
	}

	@Test
	public void testGetUserLogins_InvalidEndDate() throws Exception {
		String url = "/logins?end=aaaa";

		mockMvc.perform(get(url))
		.andExpect(status().is4xxClientError())
		.andDo(print())
		.andReturn();
	}
}