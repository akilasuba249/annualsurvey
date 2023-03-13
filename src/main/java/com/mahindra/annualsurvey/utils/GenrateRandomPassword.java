package com.mahindra.annualsurvey.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.log4j.Logger;

import com.mahindra.annualsurvey.controllers.AreaController;

import java.math.BigInteger;

public class GenrateRandomPassword {
	
	public static final Logger logger = Logger.getLogger(GenrateRandomPassword.class);

	private static SecureRandom random = new SecureRandom();

	   public static void main(String[] args) {

		   
		//   logger.info(nextSessionId());
//	logger.info(genPassword(random));
	System.out.println(genPassword(random));	
	   }
	   /*public static String nextSessionId() {
		    return new BigInteger(130, random).toString(32);
	}*/

	   
	   private static final String symbols = 
			     "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789$@#";

			public static String genPassword(Random r) {
			    while(true) {
			        char[] password = new char[r.nextBoolean()?12:13];
			        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
			        for(int i=0; i<8; i++) {
			            char ch = symbols.charAt(r.nextInt(symbols.length()));
			            if(Character.isUpperCase(ch))
			                hasUpper = true;
			            else if(Character.isLowerCase(ch))
			                hasLower = true;
			            else if(Character.isDigit(ch))
			                hasDigit = true;
			            else
			                hasSpecial = true;
			            password[i] = ch;
			        }
			        if(hasUpper && hasLower && hasDigit && hasSpecial) {
			            return new String(password);
			        }
			    }
			}

	
	
	
}
