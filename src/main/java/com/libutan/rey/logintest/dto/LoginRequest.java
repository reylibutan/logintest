package com.libutan.rey.logintest.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.libutan.rey.logintest.annotation.ValidDate;

public class LoginRequest {

	private static String DATE_FORMAT = "yyyyMMdd";
	private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

	@ValidDate(fieldName = "Start Date", format = "yyyyMMdd")
	private String start;

	@ValidDate(fieldName = "End Date", format = "yyyyMMdd")
	private String end;

	private String attribute1;

	private String attribute2;

	private String attribute3;

	private String attribute4;

	public String getStart() {
		return start;
	}

	public Date getStartDate() {
		Date date = null;
		try {
			date = formatter.parse(this.start);
		} catch (ParseException | NullPointerException e) {
			// do nothing
		}

		return date;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public Date getEndDate() {
		Date date = null;
		try {
			date = formatter.parse(this.end);
		} catch (ParseException | NullPointerException e) {
			// do nothing
		}

		return date;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
}