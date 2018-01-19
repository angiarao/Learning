package angia.problems;

public class NumberDigits {

	public int findNextBigNumberUsingSameDigits(int num){
		String nextBigNum = _findNextBigNumberUsingSameDigits(String.valueOf(num));
		return Integer.parseInt(nextBigNum); 
	}
	
	private String _findNextBigNumberUsingSameDigits(String num){
		int len = num.length();
		if(len==1) return num;
		char[] numArr = num.toCharArray();
		int i,j;
		for(i=len-1;i>=1;i--){
			if(numArr[i]>numArr[i-1]) break;
		}
		
		if(i==0) return "-1";
		
		//find the smallest char in the remaining digits
		int smallestPos = i;
		for(j=i+1;j<len;j++){
			if(numArr[smallestPos]>numArr[j]) smallestPos = j;
		}
		
		numArr = swap(numArr,smallestPos,i-1);
		
		//sorting the remaining characters in ascending format
		for(j=i;j<len;j++){
			for(int k=j+1;k<len;k++){
				if(numArr[j]>numArr[k]){
					numArr = swap(numArr,j,k);
				}
			}
		}
		
		return new String(numArr);
	}
	
	private char[] swap(char[] arr, int i, int j){
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return arr;
	}
	
	int MAX_DIGITS = 120;
	
	public int[] factorial(int[] res, int n,int filledDigits){
		if(res==null || res.length==0){
			res = new int[MAX_DIGITS];
		}
		
		if(n<=1){
			res[MAX_DIGITS-1]=1;
			return res;
		}
		
		
		
		
		return res;
	}

}
