package com.mahindra.annualsurvey.utils;

public class EmailValidation {
	
	public static void main(String[] args) {
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w-]+\\.)+[\\w]+[\\w]$";
		
		String EMAIL_REGEX_NEW =  "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
		
		String mail1 ="VS00434950@TechMahindra.com";
		
		String mail2 ="gaurav.parab@TechMahindra-retail.com";
		
		

		
		
		if(mail2.matches(EMAIL_REGEX)){
			
			System.out.println("true");
		}
		else{
			
			System.out.println("false");
		}
		
	}

}
