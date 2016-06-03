
public class Insertion {
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i = 1; i < N; i++){
			Comparable cpy = a[i];
			int j;
			for(j = i; j >= 1 && a[j-1].compareTo(cpy) > 0; j--)
				a[j] = a[j-1];
			
			a[j] = cpy;
		}
	}
}
