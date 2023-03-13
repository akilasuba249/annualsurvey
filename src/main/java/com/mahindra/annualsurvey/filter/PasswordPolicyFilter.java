package com.mahindra.annualsurvey.filter;

import org.springframework.stereotype.Component;

/**
 * 
 * @author MUTKSA-CONT
 * 
 */
@Component
public class PasswordPolicyFilter {
	public boolean passwordValidation(String password) {
		boolean valid = true;
		if (password.length() > 10 || password.length() < 6) {
			System.out
					.println("Password should be less than 10 and more than 6 characters in length.");
			valid = false;

		}

		String upperCaseChars = "(.*[A-Z].*)";
		if (!password.matches(upperCaseChars)) {
			System.out
					.println("Password should contain atleast one upper case alphabet");
			valid = false;
		}
		String lowerCaseChars = "(.*[a-z].*)";
		if (!password.matches(lowerCaseChars)) {
			System.out
					.println("Password should contain atleast one lower case alphabet");
			valid = false;
		}
		String numbers = "(.*[0-9].*)";
		if (!password.matches(numbers)) {
			System.out.println("Password should contain atleast one number.");
			valid = false;
		}
		String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
		if (!password.matches(specialChars)) {
			System.out
					.println("Password should contain atleast one special character");
			valid = false;
		}
		if (valid) {
			System.out.println("Password is valid.");
		}
		return valid;
	}

}
