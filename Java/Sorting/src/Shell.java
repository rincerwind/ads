
public class Shell {
	/*
	 * A more generalized version of Insertion sort.
	 * 
	 * The algorithm h-sorts the array and makes use of the fact
	 * that insertion sort has linear complexity for almost sorted
	 * arrays. The way it works is that for h = 13, for example, it
	 * compares and sorts every 13th element. Then h is decreased
	 * according to some rule, lets say h = 7 - we repeat the insertion
	 * sort procedure for every 7th element. This strategy is followed
	 * until h = 1, when the original Insertion sort is performed. However,
	 * since the array is almost sorted, it will be linear in time. So, the
	 * complexity should be N^{3/2} for h=3*h+1 rule (in practice is much less).
	 * Nobody has been able to prove how many comparisons it does, by finding
	 * an accurate model but it seems to be some multiple of 2.5*N*Log(N).
	 * 
	 * 
	 * Applications:
	 * 
	 * 1. Useful for medium size arrays
	 * 2. In embedded systems because simple but powerful
	 * 3. Hardware sort prototype
	 * */
	
	public static void sort(Comparable[] a){
		int N = a.length;
		
		int h = 1;
		while(h < N/3) h = h*3 + 1;
		
		while(h >= 1){
			for(int i = h; i < N; i++){
				Comparable cpy = a[i];
				int j;
				for(j = i; j >= h && a[j-h].compareTo(cpy) > 0; j-=h)
					a[j] = a[j-h];
				
				a[j] = cpy;
			}
			h /= 3;
		}
	}
}
