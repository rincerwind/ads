
public class Utils {
	public static void swap(Comparable[] a, int i, int j){
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void cpy(Comparable[] a, Comparable[] b, int l, int r){
		for(int i = l; i <= r; i++)
			a[i] = b[i];
	}
	
	public static boolean isSorted(Comparable[] a, int l, int r){
		for(int i = l; i <= r; i++)
			if( a[i].compareTo(a[i+1]) > 0 )
				return false;
		return true;
	}
	
	public static void printArr(Comparable[] a){
		for(int i = 0; i < a.length; i++)
			System.out.print("" + a[i] + " ");
		System.out.println();
	}
}
