package angia.ds.algos.stringutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringUtils {

	
	public static List<Character> findDuplicateCharacters(String s){
		char[] cArr = s.toCharArray();
		int[] charMap = new int[256];
		List<Character> duplicateCharacter = new ArrayList<Character>(); 
		Arrays.fill(charMap, 0);
		for(int i=0;i<cArr.length;i++){
			charMap[(int) cArr[i]] = charMap[(int) cArr[i]]+1;
		}
		for(int i=0;i<charMap.length;i++){
			if(charMap[i]>1) duplicateCharacter.add((char) i);
		}
		
		return duplicateCharacter;
	}
	
	public static String findLongestSubstringContainingKUniqueCharactersUsingWindows(String s,int k){
		String lUniqueSub = "";
		int u = 0; // number of unique characters
	    int n = s.length();
	    int uniqueCharLimit =k-1;
	    int maxLength = 0;  
	    
	    HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
	    for(int i=0;i<n;i++){
	    	Integer val = charCountMap.get(s.charAt(i));
	    	val = (val!=null?val+=1:0); 
	    	charCountMap.put(s.charAt(i), val);
	    }
	    
	    int uniqueCount = charCountMap.keySet().size();
	    if(k>n){
	    	System.out.println("unique char limit greater than given string lenth");
	    	return "";
	    }else if(k>uniqueCount){
	    	System.out.println("Not enough unique characters");
	    	return "";
	    }
	    
	    for(int i=0;i<n;i++){
	    	
	    }
	    
	    for(int i=0;i<n;i++){
	    	Set<Character> uniqueSet = new HashSet<Character>();
	    	StringBuffer sBuff = new StringBuffer();
	    	int j=0;
	    	int currP=i;
	    	while(j!=(uniqueCharLimit+1) && currP<n){
	    		if(!uniqueSet.contains(s.charAt(currP))){
	    			uniqueSet.add(s.charAt(currP));
	    			j++;
	    		}
	    		sBuff.append(s.charAt(currP));
	    		currP++;
	    	}
	    	
	    	if(j!=(uniqueCharLimit+1)) continue;
	    	
	    	//add only duplicate characters
	    	while(currP<n){
	    		if(uniqueSet.contains(s.charAt(currP))){
	    			sBuff.append(s.charAt(currP));
	    			currP++;
	    		}else{
	    			break;
	    		}
	    	}
	    	System.out.println("String for consideration : "+ sBuff.toString());
	    	if(sBuff.length()>maxLength){
	    		lUniqueSub = sBuff.toString();
	    		maxLength = sBuff.length();
	    		if(maxLength==n) break;
	    	}
	    }
	    return lUniqueSub;
	}
	
	public static String findLongestSubstringContainingKUniqueCharacters(String s,int k){
		String lUniqueSub = "";
		int u = 0; // number of unique characters
	    int n = s.length();
	    int uniqueCharLimit =k-1;
	    int maxLength = 0;   
	    if(k>n){
	    	System.out.println("unique char limit greater than given string lenth");
	    	return "";
	    }
	    for(int i=0;i<n;i++){
	    	Set<Character> uniqueSet = new HashSet<Character>();
	    	StringBuffer sBuff = new StringBuffer();
	    	int j=0;
	    	int currP=i;
	    	while(j!=(uniqueCharLimit+1) && currP<n){
	    		if(!uniqueSet.contains(s.charAt(currP))){
	    			uniqueSet.add(s.charAt(currP));
	    			j++;
	    		}
	    		sBuff.append(s.charAt(currP));
	    		currP++;
	    	}
	    	
	    	if(j!=(uniqueCharLimit+1)) continue;
	    	
	    	//add only duplicate characters
	    	while(currP<n){
	    		if(uniqueSet.contains(s.charAt(currP))){
	    			sBuff.append(s.charAt(currP));
	    			currP++;
	    		}else{
	    			break;
	    		}
	    	}
	    	System.out.println("String for consideration : "+ sBuff.toString());
	    	if(sBuff.length()>maxLength){
	    		lUniqueSub = sBuff.toString();
	    		maxLength = sBuff.length();
	    		if(maxLength==n) break;
	    	}
	    }
	    return lUniqueSub;
	}
	
	
	
	
	public static String findLongestPalindrome(String s){
		String longestPalindrome = "";
		if(s==null) return longestPalindrome;
		for(int i=0;i<s.length();i++){
			String tempPalin = null;
			int indexToSkip=0;
			boolean odd = false;
			if(((i+1)<s.length()) && (s.charAt(i) == s.charAt(i+1))){
				odd=false;
				tempPalin = searchForPalindrome(s,i,false);
			}else if(((i+2)<s.length()) && (s.charAt(i) == s.charAt(i+2))){
				odd=true;
				tempPalin = searchForPalindrome(s,i,true);
			}
			if(tempPalin!=null && longestPalindrome.length()<tempPalin.length()){
				longestPalindrome = tempPalin;
				if(tempPalin.length()==s.length()) break;
			}
		}
		return longestPalindrome;
	}
	
	private static String searchForPalindrome(String s, int indexToExpand,boolean odd){
		int i=indexToExpand-1;
		int j=indexToExpand+2;
		if(odd) j=indexToExpand+3;
		while(i>=0 && j<s.length()){
			if(s.charAt(i) != s.charAt(j)){
				break;
			}
			i--;j++;
		}
		System.out.println("Temp palindrome : " + s.substring(i+1,j));
		return s.substring(i+1,j);
	}

}
