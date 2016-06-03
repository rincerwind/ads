
public class Insertion {
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i = 1; i < N; i++){
			Comparable cpy = a[i];
			int j;
			for(j = i - 1; j >= 0 && a[j].compareTo(cpy) > 0; j--)
				a[j+1] = a[j];
			
			a[j+1] = cpy;
		}
	}
}
