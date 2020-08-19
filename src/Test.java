import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test {

	final static int CUTOFF = 10;
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(
				"src/normallyDistributedTruncatedFrom-12BTo+12B.csv"))) 
		//switch source to "uniformlyDistributedFrom-12BTo+12B.csv" when
		//testing scenario 1
		{
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        records.add(Arrays.asList(values));
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create list of strings with the elements from the csv file
		
		double[] a = new double[records.size()];
		
		for (int i = 0; i < a.length; i++) {
			a[i] = Double.valueOf(records.get(i).get(0));
		}
		//add elements from string array to a double array

		for(int i : new int[] 
				{100, 1000, 4000, 16000, 32000, 64000, 128000, 256000, 512000})
		{
			long startTime = System.nanoTime();
			quicksort(a, 0, i-1);
			long endTime = System.nanoTime();
			long totalTime = endTime - startTime;
			System.out.println(i + ", " + totalTime);
		}
	}
	
	   /**
	* Internal quicksort method that makes recursive calls.
	* Uses median-of-three partitioning and a cutoff of 10.
	* @param a an array of Comparable items.
	* @param left the left-most index of the subarray.
	* @param right the right-most index of the subarray.
	*/
	private static void quicksort( double [ ] a, int left, int right )
	{
		if( left + CUTOFF <= right )
		{
			double pivot = pivotPick( a, left, right );
			// Begin partitioning
			int i = left - 1, j = right + 1;
			for( ; ; )
			{
				while( a[ ++i ] < pivot ) {}
				while( a[ --j ] > pivot ) {}
				if( i < j )
					swapReferences( a, i, j );
				else
					break;
			}		
			quicksort( a, left, i - 1 ); // Sort small elements
			quicksort( a, j+1 , right ); // Sort large elements
		}
		else // Do an insertion sort on the subarray
			insertionSort( a, left, right );
		
	}

	private static void insertionSort(double[] a, int left, int right) {
		// TODO Auto-generated method stub
		int i = 0;
		int j = 0;
        double tmp = 0;

		for (i = left + 1; i <= right; i++) {
            j = i;

            while(j > left && a[j] < a[j-1])
            {
            	tmp = a[j];
            	a[j] = a[j-1];
            	a[j-1] = tmp;
            	j--;
            }
        }
	}

	private static void swapReferences(double[] a, int i, int j) {
		// TODO Auto-generated method stub
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
	}

	private static double pivotPick(double[] a, int left, int right) {
		// TODO Auto-generated method stub
		//uncomment each pivotpick for different results
		
		//pivotPick1
//		return a[(left+right)/2];
		
//		pivotPick2
//		double x = a[(left+right)/2];
//		double y = a[left];
//		double z = a[right];
//		
//		double[] k = {x, y, z};
//		
//		quicksort(k, 0, 2);
//		
//		return k[1];
		
		//pivotPick3
//		double v = a[left];
//		double w = a[(3*left + right)/4];
//		double x = a[(left+right)/2];
//		double y = a[(left+(3*right))/4];
//		double z = a[right];
//
//		double[] k = {v, w, x, y, z};
//		
//		quicksort(k, 0, 4);
//		
//		return k[2];
		
		//pivotPick4
//		long max = 12000000000L;
//		long min = -12000000000L;
//				
//		double v = a[left];
//		double w = a[(2*left + right)/3];
//		double mu = min + ((left+right)/(2*(a.length-1))*(max-min));
//		double y = a[(left + 2*right)/3];
//		double z = a[right];
//		
//		double[] k = {v, w, mu, y, z};
//		
//		quicksort(k, 0, 4);
//		
//		return k[2];
		
		//pivotPick5
		double m = 0;
		double s = 3000000000L;
		
		double v = a[left];
		double w = a[(2*left + right)/3];
		double ups = m + 4*(((left+right)/(a.length-1))-1)*s;
		double y = a[(left + 2*right)/3];
		double z = a[right];
		
		double[] k = {v, w, ups, y, z};
		
		quicksort(k, 0, 4);
		
		return k[2];
		
	}
	
	


}

