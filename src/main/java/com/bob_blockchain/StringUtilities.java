package com.bob_blockchain;

import java.security.MessageDigest;

import com.google.gson.GsonBuilder;

public class StringUtilities { 
	public static String applySha256(String input){
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        
			//Applies sha256 to our input, 
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
	        
			StringBuilder HXString = new StringBuilder(); 
			for (int i = 0; i < hash.length; i++) {
				String hx = Integer.toHexString(0xff & hash[i]);
				if(hx.length() == 1) HXString.append('0');
				HXString.append(hx);
			}
			return HXString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static String getJson(Object o) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(o);
	}
	public static String getDificultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
	
	
}
