package com.javatpoint.Bmodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class BookModel {

//	public static void main(String[] args) {
//
//		List<String> allOutPuts = new ArrayList<String>();
//		TreeMap<Integer, Set> map = new TreeMap<Integer, Set>();
//
//		String input = "abcdefghfdsasfdf";
//
//		for (int k = 0; k < input.length(); k++) {
//			String input1 = input.substring(k);
//			String longestSubString = getLongestSubString(input1);
//			allOutPuts.add(longestSubString);
//		}
//
//		for (String str : allOutPuts) {
//			int strLen = str.length();
//			if (map.containsKey(strLen)) {
//			
//				Set set2 = new LinkedHashSet();
//				set2.add(str);
//				map.put(strLen, set2);
//			} else {
//				Set set1 = new HashSet();
//				set1.add(str);
//				map.put(strLen, set1);
//				
//			}
//
//		}
//		System.out.println(map.lastKey());
//
//		System.out.println(map.get(map.lastKey()));
//	}
//
//	private static String getLongestSubString(String input) {
//
//		Set<Character> set = new LinkedHashSet<Character>();
//		String longestString = "";
//		int len = input.length();
//		for (int i = 0; i < len; i++) {
//			char currentChar = input.charAt(i);
//			boolean isCharAdded = set.add(currentChar);
//			if (isCharAdded) {
//				if (i == len - 1) {
//					String currentStr = getStringFromSet(set);
//
//					if (currentStr.length() > longestString.length()) {
//						longestString = currentStr;
//					}
//				}
//				continue;
//			} else {
//				String currentStr = getStringFromSet(set);
//
//				if (currentStr.length() > longestString.length()) {
//					longestString = currentStr;
//				}
//				set = new LinkedHashSet<Character>(input.charAt(i));
//			}
//
//		}
//
//		return longestString;
//	}
//
//	private static String getStringFromSet(Set<Character> set) {
//
//		Object[] charArr = set.toArray();
//
//		StringBuffer strBuff = new StringBuffer();
//		for (Object obj : charArr) {
//			strBuff.append(obj);
//
//		}
//
//		return strBuff.toString();
//	}
	
	
//	public static void main(String[] args) {
//		
//	
//	String s = "as,ps,as,jk";
//	
//String[] s1 = s.split(",");	
//	Set<String> set = new HashSet<String>();
//for(String ns : s1) {
//	set.add(ns);
//}
//System.out.println(set);
//
//}

}