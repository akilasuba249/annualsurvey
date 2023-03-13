package com.mahindra.annualsurvey.filter;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

public class Util {

	@Autowired
	MultipartXSSFilter multiple;
	
	public static String generateCaptchaTextMethod1()    {		 
        Random rdm=new Random();
        int rl=rdm.nextInt(); // Random numbers are generated.
        String hash1 = Integer.toHexString(rl); // Random numbers are converted to Hexa Decimal. 
        return hash1;
 
	}
	
	public static String generateCaptchaTextMethod2(int captchaLength)   {
		 
	     String saltChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	     StringBuffer captchaStrBuffer = new StringBuffer();
	            java.util.Random rnd = new java.util.Random();
	 
	            // build a random captchaLength chars salt
	            while (captchaStrBuffer.length() < captchaLength)
	            {
	                int index = (int) (rnd.nextFloat() * saltChars.length());
	                captchaStrBuffer.append(saltChars.substring(index, index+1));
	            }
	 
	        return captchaStrBuffer.toString();
	 
	}
	
	public static void main(String[] args) {
		Util util = new Util();
		util.testXXX();

	}
	
	public void testXXX(){
		boolean flag=true;		
		String s1="mahindra leadership University (MLU)";
		MultipartXSSFilter mult = new MultipartXSSFilter();
		if(s1!=null)
	    { 
			
	    	String b = mult.cleanXSS(s1);
	    	if(!(s1.equalsIgnoreCase(b)))
	    	{
	    		flag=false;	
	    		System.out.println("Velnerable");
	    	}
	    	
	    }	
		if(flag){
			//if multipart does not have cross site script then this code will get execute
			System.out.println("XXX NOt found");
			}

	}
}
