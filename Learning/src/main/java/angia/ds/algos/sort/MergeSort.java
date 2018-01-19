package angia.ds.algos.sort;

public class MergeSort {

	int[] arr = null;
	
	private MergeSort(int[] arr) {
		this.arr = arr;
	}
	

	private int[] sort_recursive(int start,int finish){
		if(start<finish){
			int m=(start+finish)/2;
			sort_recursive(start, m);
			sort_recursive(m+1,finish);
			merge(start,m,finish);
		}
		return this.arr;
	}
	
	private void merge(int l,int m, int h){
		int L[] = new int[m-l+1];
		int R[] = new int[h-m];
		
		//copying arr
		for(int i=0;i<L.length;i++){
			L[i] = this.arr[l+i];
		}
		for(int i=0;i<R.length;i++){
			R[i] = this.arr[m+1+i];
		}
		
		int i=0,j=0,k=l;
		
		while(i<L.length && j<R.length){
			if(L[i]>R[j]){
				arr[k] = R[j];
				j++;
			}else{
				arr[k] = L[i];
				i++;
			}
			k++;
		}
		
		while (i < L.length){
			arr[k] = L[i];
			i++;k++;
		}
	 
        while (j < R.length)
        {
            arr[k] = R[j];
            j++;k++;
        }
	}
	
	private void swap(int m, int n){
		int temp;
		temp = arr[m];
		arr[m] = arr[n];
		arr[n] = temp;
	}
	
	
	public static int[] sort(int[] arr){
		MergeSort mSort = new MergeSort(arr);
		return mSort.sort_recursive(0, arr.length-1); 
	}


}
