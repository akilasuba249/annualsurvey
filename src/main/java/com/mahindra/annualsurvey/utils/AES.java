package com.mahindra.annualsurvey.utils;

import java.io.IOException;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*import java.util.Base64;*/
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Component;


import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
 import java.sql.*;@Component
public class AES {
	
 
    private static SecretKeySpec secretKey;
    private static byte[] key1;
    
   
   
   // private static String key="key123key123key1";
    private static String key="M@h1ndra$1234567";
    private static final String initVector = "0001000100010001";
    public static void setKey()
    {
    	byte[] raw = { (byte) 0xA5, (byte) 0x01, (byte) 0x7B, (byte) 0xE5,
				(byte) 0x23, (byte) 0xCA, (byte) 0xD4, (byte) 0xD2,
				(byte) 0xC6, (byte) 0x5F, (byte) 0x7D, (byte) 0x8B,
				(byte) 0x0B, (byte) 0x9A, (byte) 0x3C, (byte) 0xF1 };
    	 secretKey = new SecretKeySpec(raw, "AES");
       /* MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
           
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
    }
 
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        { 
        	IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        	
           /* setKey();*/
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            //Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
            byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes());
	        return Base64.encodeBase64String(encrypted);
           // String e=Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
           /* if(e.contains("+")) {
            String finalstr=e.replace("+","#");
            System.out.println("final string ===>"+finalstr);
            return finalstr;
            }else {*/
            
           // e=URLEncoder.encode(e,"UTF-8");
            	//return e;
            /*}*/
        }
        catch (Exception e)
        {
          e.printStackTrace();  
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret)
    {
       /* try
        {
        	 IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
        	String strToDecrypt1=strToDecrypt.replace(" ", "+");
            setKey();
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            //Cipher cipher = Cipher.getInstance("AES/ECB/NOPADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);
            //strToDecrypt=URLDecoder.decode(strToDecrypt, "UTF-8");
            byte[] original = cipher.doFinal(Base64.decodeBase64("+"+encrypted));
	        return new String(original);
            //return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
        	
        }
        return null;*/
    
		    try {
		    	String strToDecrypt1=strToDecrypt.replace("\\s", "+");
		        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
		        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		        byte[] original = cipher.doFinal(Base64.decodeBase64(strToDecrypt));
		        return new String(original);
		    } catch (Exception ex) {
		       
		    }
		    return null;
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
    	  
    	
    	//System.out.println(email);
    	String s=encrypt("gunjal.jitendra@mahindra.com","key123key123key1");
    	//String s=encrypt("admin","key123key123key1");
    	System.out.println(s);
    	
    	String e=URLEncoder.encode(s, "UTF-8");
    	System.out.println(e);
    	/*String d = decrypt(e, "");
    	System.out.println(d);*/
    	
    	String p=AES.decrypt(URLDecoder.decode("p7UAQ4C6A13i4SndII8uZh6REfSzkyqR1lJMC3zp0J4%3D", "UTF-8"),"key123key123key1");
    	System.out.println(p);
    	
	}
	
/*public Sec keyGenerate() {
		
		try {
			this.cipher = Cipher.getInstance("AES");
			byte[] raw = { (byte) 0xA5, (byte) 0x01, (byte) 0x7B, (byte) 0xE5,
					(byte) 0x23, (byte) 0xCA, (byte) 0xD4, (byte) 0xD2,
					(byte) 0xC6, (byte) 0x5F, (byte) 0x7D, (byte) 0x8B,
					(byte) 0x0B, (byte) 0x9A, (byte) 0x3C, (byte) 0xF1 };
			this.key = new SecretKeySpec(raw, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	*/
	
}