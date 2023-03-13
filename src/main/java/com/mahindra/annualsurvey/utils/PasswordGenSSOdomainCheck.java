package com.mahindra.annualsurvey.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenSSOdomainCheck {
	
	
/*	public static void main(String[] args) {
	
	}*/
	
	public Boolean ssodomain(String emailid){
		List<String> SSO_DOMAINS=new ArrayList<>(Arrays.asList("mahindra.com","bcone.com","mahindraonline.mail.onmicrosoft.com"));  // here techM removed from SSO
		String [] parts=emailid.split("@");
		Boolean result =SSO_DOMAINS.contains(parts[1].toLowerCase());
		
		if(result){
			System.out.println("sso domain");
			return true;
		}else{
			System.out.println("not sso domain");
			return false;
		}
	}
	
	public String getRandomString(int length){
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	} 


}
