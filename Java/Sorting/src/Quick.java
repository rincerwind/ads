
public class Quick {
	private static int partition(Comparable[] a, int l, int r){
		int i = l+1;
		int j = r;
		while(true){
			while(i < r && a[i].compareTo(a[l]) < 0)
				i++;
			
			while(j > l && a[l].compareTo(a[j]) < 0)
				j--;
			
			if(i >= j)
				break;
			Utils.swap(a, i, j);
		}
		
		Utils.swap(a, l, j);
		return j;
	}
	
	private static void qsort(Comparable[] a, int l, int r){
		if( l >= r )
			return;
		
		int j = partition(a, l, r);
		qsort(a, l, j-1);
		qsort(a, j+1, r);
	}
	
	public static void sort(Comparable[] a){
		// good idea to shuffle the array at this point
		qsort(a, 0, a.length - 1);
	}
	
	// finds the k-th smallest element
	public static Comparable select(Comparable[] a, int k, int l, int r){
		int i = l;
		int j = r;
		
		while( i < j ){
			int t = partition(a, i, j);
			if( t < k )
				i = t + 1;
			else if(t > k)
				j = t - 1;
			else
				return a[k];
		}
		
		return a[k];
	}
}
