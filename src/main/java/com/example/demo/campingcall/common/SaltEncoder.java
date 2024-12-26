package com.example.demo.campingcall.common;

public class SaltEncoder {

	public static String saltEncoding(String message) {
		
		String sha256Encoding = SHA256HashingEncoder.encode(message);
		
		for(int i = 0; i < 3; i++) {
			sha256Encoding += message;
			sha256Encoding = SHA256HashingEncoder.encode(sha256Encoding);
		}
		
		return sha256Encoding;
	}
}
