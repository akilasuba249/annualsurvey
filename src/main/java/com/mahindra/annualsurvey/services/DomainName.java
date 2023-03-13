package com.mahindra.annualsurvey.services;

public class DomainName {

	public static void main(String[] args) {
		
		String a="gaurav.parab@gmail.com";
		
		String b = a.substring(a.indexOf('@') + 1);
		
		System.out.println(b);

		
	}
	
}
