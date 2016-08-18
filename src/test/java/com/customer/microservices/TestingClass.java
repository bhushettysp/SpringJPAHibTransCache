package com.customer.microservices;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TestingClass {

	@Autowired
	private SessionFactory sessionFactory;
	public static void main(String[] args) {
		System.out.println("is Palindrome : "+isPalindromeIgnoreCase("malayalaM"));
		System.out.println("is Palindrome : "+removeCharAt("malayalaM",8));
	}

	public static boolean isPalindrome(String string){
		int length=string.length();
		for(int i=0;i<length/2;i++){
			if(!(string.charAt(i)==string.charAt(length-i-1)))
				return false;
		}
		return true;
	}
	
	public static boolean isPalindromeIgnoreCase(String string){
		int length=string.length();
		String buffer = string.toLowerCase();
		for(int i=0;i<length/2;i++){
			if(!(buffer.charAt(i)==buffer.charAt(length-i-1)))
				return false;
		}
		return true;
	}
	
	public static String removeCharAt(String string, int index){
		char mychars[]  = string.toCharArray();
		char result[] = new char[string.length()];
		int j=0;
		for(int i=0;i<string.length();i++){
			if(i  != index )
				result[j] = mychars[i];
			j++;
		}
		return new String(result);
	}
}
