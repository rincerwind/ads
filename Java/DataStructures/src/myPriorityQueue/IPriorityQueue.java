package myPriorityQueue;

public interface IPriorityQueue<T extends Comparable<T>> {
	public static enum POrder { LESSER, GREATER }
	
	// inserts and item into the priority queue
	public void insert(T item);
	
	// removes and returns the first item in the queue
	public T removeFirst();
	
	// returns if the queue is empty
	public boolean isEmpty();
	
	public int size();
}
