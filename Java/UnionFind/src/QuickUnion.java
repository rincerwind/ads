
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
		// make copies of both because their values might change due to loop below
		int pid = id[p];
		int qid = id[q];
		for(int i = 0; i < id.length; i++)
			id[i] = (id[i] == pid)? qid : pid;
	}
	
	// check if there is a path between p and q (are they in the same component)
	public boolean connected(int p, int q){
		return id[p] == id[q];
	}
	
	// returns component identifier of p
	public int find(int p){return 0;}
	
	// returns number of components
	public int count(){return 0;}
}
