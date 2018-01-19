package angia.ds.algos.sort;

import java.util.Arrays;

public class RadixSort {
	
	private static int[] countingSort(int[] arr){
		
		int[] indexArr = new int[255];
		int[] output = new int[arr.length];
		Arrays.fill(indexArr, 0);
		
		//calculating histogram
		for(int i=0;i<arr.length;i++){
			indexArr[arr[i]]+=1;
		}
		
		int total = indexArr[0];
		for(int i=1;i<indexArr.length;i++){
			indexArr[i] += total;
			total=indexArr[i];
		}
		
		for(int i=0;i<arr.length;i++){
			try{
				output[indexArr[arr[i]]-1] = arr[i];
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Output arr so far : " + Arrays.toString(output));
				System.out.println("index of i - "+ i);
				System.out.println("input arr for this index value - "+ arr[i]);
			}
			
			indexArr[arr[i]]-=1;
		}
		
		return output;
	}
	
	private static int[] countingSort(int[] arr,int base){
		
		System.out.println("Base - " + String.valueOf(base));
		System.out.println("Before Sort - " + Arrays.toString(arr));
		
		int[] indexArr = new int[10];
		int[] output = new int[arr.length];
		Arrays.fill(indexArr, 0);
		
		//calculating histogram
		for(int i=0;i<arr.length;i++){
			indexArr[((arr[i]/base)%10)]+=1;
		}
		
		System.out.println("Histogram : - " + Arrays.toString(indexArr));
		
		int total = indexArr[0];
		for(int i=1;i<indexArr.length;i++){
			indexArr[i] += total;
			total=indexArr[i];
		}
		
		System.out.println("Index Array : - " + Arrays.toString(indexArr));
		
		for(int i=arr.length-1;i>=0;i--){
			try{
				output[indexArr[((arr[i]/base)%10)]-1] = arr[i];
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Output arr so far : " + Arrays.toString(output));
				System.out.println("index of i - "+ i);
				System.out.println("input arr for this index value - "+ arr[i]);
			}
			
			indexArr[((arr[i]/base)%10)]-=1;
		}
		
		System.out.println("After Sort - " + Arrays.toString(output));
		System.out.println("--------------");
		return output;
	}
	
	private static int[] radixSort(int[] arr){
		//getting max number
		int max = arr[0];
		for(int i=1;i<arr.length;i++){
			if(max>arr[i]) continue;
			max=arr[i];
		}
		
		int exp=1;
		while((max/exp)>0){
			arr=countingSort(arr, exp);
			exp*=10;
		}
		
		
		return arr;
	}

	public static int[] sort(int[] arr){
		return radixSort(arr);
	}

}
