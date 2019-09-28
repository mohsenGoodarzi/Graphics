
public class MergeSort {
	public int[] merge(int arr[], int l, int m, int r) 
	    { 
	        // Find sizes of two subarrays to be merged 
	        int n1 = m - l + 1; 
	        int n2 = r - m; 
	  int [] result = new int[arr.length];
	  
	        /* Create temp arrays */
	        int L[] = new int [n1]; 
	        int R[] = new int [n2]; 
	  
	        /*Copy data to temp arrays*/
	        for (int i=0; i<n1; ++i) 
	            L[i] = arr[l + i]; 
	        for (int j=0; j<n2; ++j) 
	            R[j] = arr[m + 1+ j]; 
	  
	  
	        /* Merge the temp arrays */
	  
	        // Initial indexes of first and second subarrays 
	        int i = 0, j = 0; 
	  
	        // Initial index of merged subarry array 
	        int k = l; 
	        while (i < n1 && j < n2) 
	        { 
	            if (L[i] <= R[j]) 
	            { 
	            	result[k] = L[i]; 
	            	arr[k] = L[i]; 
	                i++; 
	            } 
	            else
	            { 
	            	result[k] = R[j]; 
	            	arr[k] = R[j]; 
	                j++; 
	            } 
	            k++; 
	        } 
	  
	        /* Copy remaining elements of L[] if any */
	        while (i < n1) 
	        { 
	        	result[k] = L[i]; 
	        	arr[k] = L[i]; 
	            i++; 
	            k++; 
	        } 
	  
	        /* Copy remaining elements of R[] if any */
	        while (j < n2) 
	        { 
	        	result[k] = R[j]; 
	        	arr[k] = R[j]; 
	        	
	            j++; 
	            k++; 
	        } 
	   
	    return result;
	    } 
	  
	    // Main function that sorts arr[l..r] using 
	    // merge() 
	 public   int[] sort(int arr[], int l, int r) 
	    { 
		 int [] result = new int[arr.length];
	        if (l < r) 
	        { 
	            // Find the middle point 
	            int m = (l+r)/2; 
	  
	            // Sort first and second halves 
	            sort(arr, l, m); 
	            sort(arr , m+1, r); 
	  
	            // Merge the sorted halves 
	          result=  merge(arr, l, m, r); 
	        } 
	    return  result;
	    
	    } 
}