
public class QuickUnion implements iUnionFind {
	private int id[];
	
	// Init. union-find data-struct with N objs. (indexed from 0 to N-1)
	public QuickUnion(int N){
		id = new int[N];
		for(int i = 0; i < N; i++)
			id[i] = i;
	}
	
	// create a connection between p and q
	public void union(int p, int q){
		int rp = root(p);
		int rq = root(q);
		id[rp] = rq;
	}
	
	// check if there is a path between p and q (are they in the same component)
	public boolean connected(int p, int q){
		return root(p) == root(q);
	}
	
	private int root(int v){
		int i = v;
		while( i != id[i] )
			i = id[i];
		
		return i;
	}
	
	// returns component identifier of p
	public int find(int p){return 0;}
	
	// returns number of components
	public int count(){return 0;}
}
