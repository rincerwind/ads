import java.util.Random;
import java.util.Scanner;

public class SelectTester {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();		
		Integer[] a = new Integer[N];
		Random r = new Random();
		
		for(int i = 0; i < N; i++)
			a[i] = r.nextInt(100);
		
		for(int i = 0; i < N; i++)
			System.out.print("" + a[i] + " ");
		System.out.println();		
		
		Integer num = (Integer)Quick.select(a, 1, 0, a.length-1);
		
		System.out.println("" + num);
	}

}
