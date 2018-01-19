package angia.ds.algos.stringutils;

import java.util.Arrays;

public class FindPattern {

	public static int[] findPatternUsingNaive(String source, String dest){
		int[] patternPos = new int[dest.length()];
		Arrays.fill(patternPos,-1);
		char[] srcArr = source.toCharArray();
		int i=0,j=0,startPos=-1,patternPosIndex=-1;
		while(i<dest.length()){
			if(dest.charAt(i)==srcArr[j]){
				if(j==0) startPos=i;
				j++;
			}else{
				if(j>0) i=i-j;
				j=0;
			}
			if(j==srcArr.length){
				patternPosIndex++;
				patternPos[patternPosIndex] = startPos;
				j=0;
			}
			i++;
		}
		if(patternPosIndex == -1) return new int[]{}; 
		return Arrays.copyOf(patternPos, patternPosIndex+1);
	}
	
	
	static int[] kmp_preprocess(String s){
		int len = s.length();
		int[] kmp_arr = new int[len];
		char[] patt = s.toCharArray();
		int i=1,pointer=0;
		kmp_arr[0]=0;
		while(i<len){
			if(patt[i]==patt[pointer]){
				pointer++;
				kmp_arr[i]=pointer;
				i++;
			}else{
				if(pointer>0){
					pointer = kmp_arr[pointer-1];
				}else{
					kmp_arr[i]=pointer;
					i++;
				}
			}
		}
		return kmp_arr;
	}
	
	public static int[] findPatternUsingKMP(String pattern, String text){
		int[] patternPos = new int[text.length()];
		Arrays.fill(patternPos,-1);
		char[] srcArr = pattern.toCharArray();
		int[] kmpArr = kmp_preprocess(pattern);
		System.out.println(Arrays.toString(srcArr));
		System.out.println(Arrays.toString(kmpArr));
		int i=0,j=0,startPos=-1,patternPosIndex=-1;
		while(i<text.length()){
			
			if(text.charAt(i)==srcArr[j]){
				i++;j++;
			}else{
				if(j>0){
					j=kmpArr[j-1];
				}else{
					i++;
				}
			}
			
			if(j==srcArr.length){
				patternPosIndex++;
				patternPos[patternPosIndex] = i-j;
				j=kmpArr[j-1];
			}
			
		}
		if(patternPosIndex == -1) return new int[]{}; 
		return Arrays.copyOf(patternPos, patternPosIndex+1);
	}


}
