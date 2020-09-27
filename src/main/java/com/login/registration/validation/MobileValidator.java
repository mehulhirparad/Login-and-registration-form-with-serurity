package com.login.registration.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<ValidMobile, String> {

	private Pattern pattern;
	private Matcher matcher;
	private static final String MOBILE_PATTERN = "^[789]\\d{9}$";
	
	@Override
	public boolean isValid(final String mobile, ConstraintValidatorContext context) {
		pattern = Pattern.compile(MOBILE_PATTERN);
		if (mobile == null) {
			return false;
		}
		matcher = pattern.matcher(mobile);
		return matcher.matches();
	}

}
