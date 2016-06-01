package myQueue;

public interface IQueueOfStrings {
	void enqueue(String item);
	
	String dequeue();
	
	boolean isEmpty();
	
	int size();
}
