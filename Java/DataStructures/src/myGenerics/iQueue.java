package myGenerics;

public interface iQueue<T> {
	void enqueue(T item);
	
	T dequeue();
	
	boolean isEmpty();
	
	int size();
}
