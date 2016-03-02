package br.edu.unoesc.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	public static String convertStringToMD5(String s) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("MD5");
		 
        BigInteger hash = new BigInteger(1, md.digest(s.getBytes()));
 
        return String.format("%32x", hash);
	}
}
