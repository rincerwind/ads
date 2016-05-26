import java.util.Scanner;

public class UFTester {

	// Tester code
	public static void main(String[] args) {
		int N;
		int p, q;
		Scanner line_scanner = new Scanner(System.in);
		iUnionFind uf;
		
		N = Integer.parseInt(line_scanner.nextLine());
		uf = new QuickFind(N);
		
		while(line_scanner.hasNextLine()){
			String line = line_scanner.nextLine();
			String[] split_line = line.split(" ");
			p = Integer.parseInt(split_line[0]);
			q = Integer.parseInt(split_line[1]);
						
			if(!uf.connected(p, q)){
				uf.union(p, q);
				System.out.println("" + p + " " + q);
			}
		}
		
		line_scanner.close();
	}
}
