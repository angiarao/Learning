package angia.ds.algos.stringutils;

import java.util.Arrays;

public class LongestCommonSubsequence {
	
	
	private static int[][] lcsDataTracker(char[] a_chars, char[] b_chars){
		
		//filling data tracker with 0
		int[][] dp = new int[a_chars.length+1][b_chars.length+1];
		for(int i=0;i<dp.length;i++){
			Arrays.fill(dp[i],0);
		}
	
		for(int i=a_chars.length-1;i>=0;i--){
			for(int j=b_chars.length-1;j>=0;j--){
				if(a_chars[i]=='\0' || b_chars[j]=='\0') dp[i][j]=0;
				else if(a_chars[i]==b_chars[j])  dp[i][j] = 1+dp[i+1][j+1];
				else{
					dp[i][j] = Math.max(dp[i+1][j],dp[i][j+1]);
				}
			}
		}
		System.out.println(" "+Arrays.toString(b_chars));
		for(int i=0;i<dp.length-1;i++){
			System.out.println(a_chars[i]+Arrays.toString(dp[i]));
		}
		System.out.println(" "+Arrays.toString(dp[dp.length-1]));
		
		return dp;
	}

	public static int findLCSLength(String a, String b){
		return lcsDataTracker(a.toCharArray(),b.toCharArray())[0][0];
	}
	
	private static String _getLCS(char[] a_chars, char[] b_chars){
		int[][] dataTracker;
		dataTracker = lcsDataTracker(a_chars,b_chars);
		StringBuffer stgBf = new StringBuffer();
		
		int i=0,j=0;
		
		while(i<=a_chars.length-1 && j<=b_chars.length-1){
			if(a_chars[i]!=b_chars[j]){
				if(dataTracker[i+1][j]>=dataTracker[i][j+1]){
					i++;
				}else if(dataTracker[i+1][j]<dataTracker[i][j+1]){
					j++;
				}
			}else{
				stgBf.append(a_chars[i]);
				i++;j++;
			}
		}
		
		return stgBf.toString();
	}
	
	public static String getLongestCommonSubsequence(String a, String b){
		return _getLCS(a.toCharArray(), b.toCharArray());
	}


}
