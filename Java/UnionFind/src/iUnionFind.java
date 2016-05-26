
public interface iUnionFind {
	// create a connection between p and q
	public void union(int p, int q);
	
	// check if there is a path between p and q (are they in the same component)
	public boolean connected(int p, int q);
	
	// returns component identifier of p
	public int find(int p);
	
	// returns number of components
	public int count();
}
