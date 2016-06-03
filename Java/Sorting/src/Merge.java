
public class Merge {
	
	private static void merge(Comparable[] a, Comparable[] aux, int l, int mid, int r){
		int l1 = l;
		int r1 = mid;
		int l2 = mid+1;
		int r2 = r;
		
		// this doesn't work for some reason???
		//if(a[l2].compareTo(a[r1]) > 0)
		//	return;
		
		int pleft = l1;
		int pright = l2;
		for(int i = l; i <= r; i++)
			if(pleft <= r1 && (pright > r2 || a[pleft].compareTo(a[pright]) < 0))
				aux[i] = a[pleft++];
			else
				aux[i] = a[pright++];
	}
	
	private static void msort(Comparable[] a, Comparable[] aux, int l, int r){
		if( r - l < 1 )
			return;
		
		int mid = l + (r - l)/2;
		msort(aux, a, l, mid);
		msort(aux, a, mid+1, r);
		merge(a, aux, l, mid, r);
	}
	
	public static void sort(Comparable[] a){
		Comparable[] aux = new Comparable[a.length];
		Utils.cpy(aux, a, 0, a.length-1);
		msort(a, aux, 0, a.length-1);
		Utils.cpy(a, aux, 0, a.length-1);
	}
}
