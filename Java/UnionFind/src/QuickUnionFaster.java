
public class QuickUnionFaster implements iUnionFind {
	private int id[];
	private int sz[];
	
	public QuickUnionFaster(int N){
		id = new int[N];
		sz = new int[N];
		for(int i = 0; i < N; i++){
			id[i] = i;
			sz[i] = 1;
		}
	}
	
	@Override
	// weighted union
	public void union(int p, int q) {
		int rp = root(p);
		int rq = root(q);
		if( rp == rq )
			return;
		
		if( sz[rp] >= sz[rq] ){  
			int temp = rp;
			rp = rq;
			rq = temp;
		}
		
		id[rp] = rq;
		sz[rq] += sz[rp];
	}

	@Override
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	private int root(int v){
		int i = v;
		while( i != id[i] ){
			id[i] = id[id[i]]; // one-pass path compression
			i = id[i];
		}
		
		return i;
	}
	
	@Override
	public int find(int p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
