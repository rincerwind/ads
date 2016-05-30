package myQueue;

public interface iQueueOfStrings {
	void enqueue(String item);
	
	String dequeue();
	
	boolean isEmpty();
	
	int size();
}
