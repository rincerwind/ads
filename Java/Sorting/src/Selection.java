
public class Selection {
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i = 0; i < N; i++){
			int min = i;
			
			for(int j = i+1; j < N; j++)
				if(a[min].compareTo(a[j]) > 0)
					min = j;
			
			if(min != i)
				swap(a, min, i);
		}
	}
	
	private static void swap(Comparable[] a, int i, int j){
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
