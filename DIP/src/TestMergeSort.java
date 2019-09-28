import java.util.Arrays;

public class TestMergeSort {
	public static void main(String[] args) {
		
		
		int [] oldArray= {67,11,5,50,30,700};
		
		int []NewArray=Arrays.copyOf(oldArray,oldArray.length);
		Arrays.sort(NewArray);
	for (int x =0; x<NewArray.length;x++) {
		
		//newArray[x]+=100;
		
		System.out.println("newArray: "+oldArray[x]+"		 resultArray: "+NewArray[x] );
	}
		
	}

}
