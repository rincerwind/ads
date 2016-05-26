import java.util.Scanner;

public class UnionFind {

	// Init. union-find data-struct with N objs. (indexed from 0 to N-1)
	public UnionFind(int N){}
	
	// create a connection between p and q
	public void union(int p, int q){}
	
	// check if there is a path between p and q (are they in the same component)
	public boolean connected(int p, int q){return true;}
	
	// returns component identifier of p
	public int find(int p){return 0;}
	
	// returns number of components
	public int count(){return 0;}
	
	// Tester code
	public static void main(String[] args) {
		int N;
		int p, q;
		Scanner line_scanner = new Scanner(System.in);
		UnionFind uf;
		
		N = Integer.parseInt(line_scanner.nextLine());
		uf = new UnionFind(N);
		
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
