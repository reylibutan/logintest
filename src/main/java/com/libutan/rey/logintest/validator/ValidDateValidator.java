package com.libutan.rey.logintest.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.libutan.rey.logintest.annotation.ValidDate;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

	private String format;

	@Override
    public void initialize(ValidDate notNullOrEmpty) {
		this.format = notNullOrEmpty.format();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	boolean result = true;

    	if (!(value == null || value.trim().isEmpty())) {
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
        	sdf.setLenient(false);

        	try {
        		sdf.parse(value);
        	} catch (ParseException pe) {
        		result = false;
        	}
    	}

    	return result;
    }
}
