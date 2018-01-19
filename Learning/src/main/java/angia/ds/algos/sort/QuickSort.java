package angia.ds.algos.sort;

import java.util.Arrays;

public class QuickSort {
	
	int[] arr = null;
	
	private QuickSort(int[] arr) {
		this.arr = arr;
	}
	
	private int partition(int start, int finish){
		int pivot = 1 + ((finish+start)/2);
		int pivotElem = arr[pivot];
		swap(pivot,finish);
		int lowPointer = start-1;
		for(int i=start;i<=finish-1;i++){
			if(arr[i]<=pivotElem){
				lowPointer++;
				swap(lowPointer,i);
			}
		}
		swap(finish,lowPointer+1);
		return lowPointer+1;
	}

	private int[] sort_recursive(int start, int finish){
		if(start<finish){
			int pi = partition(start,finish);
			sort_recursive(start, pi-1);
			sort_recursive(pi+1,finish);
		}
		return this.arr;
	}

	private int[] sort_iterative(int start, int finish){
		int[] stack = new int[finish-start+1];
		int h,l;
		int top=-1;
		stack[++top] = start;stack[++top] = finish;
		while(top>=0){
			h = stack[top--];l = stack[top--];
			int p =partition(l, h);
			if(p-1>l){
				stack[++top]=l;stack[++top]=p-1;
			}
			if(p+1<h){
				stack[++top]=p+1;stack[++top]=h;
			}	
		}
		
		return this.arr;
	}
	
	private void swap(int m, int n){
		int temp;
		temp = arr[m];
		arr[m] = arr[n];
		arr[n] = temp;
	}
	
	
	public static int[] sort(int[] arr){
		QuickSort qSort = new QuickSort(arr);
		return qSort.sort_recursive(0, arr.length-1); 
	}

}
