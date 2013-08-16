package com.j1tth4.mobile.util;

import java.security.MessageDigest;

public class EncryptSHA1 {
	public static String encryptSHA1(String passText) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
			messageDigest.update(passText.getBytes("UTF-8"));
			byte[] digestBytes = messageDigest.digest();

			String hex = null;
			for (int i = 0; i < digestBytes.length; i++) {
				hex = Integer.toHexString(0xFF & digestBytes[i]);
				if (hex.length() < 2)
					sb.append("0");
				sb.append(hex);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String pass = sb.toString();
		return pass.toUpperCase();
	}
}
