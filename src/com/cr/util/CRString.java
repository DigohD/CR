package com.cr.util;

public class CRString {

	public static String create(String s){
		int rest = 31 - s.length();
		
		for(int i = 0; i < rest/2; i++)
			s = s + "  ";
		
		return s;
	}
	
}
