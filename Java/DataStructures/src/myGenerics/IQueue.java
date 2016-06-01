package myGenerics;

public interface IQueue<T> {
	void enqueue(T item);
	
	T dequeue();
	
	boolean isEmpty();
	
	int size();
}
