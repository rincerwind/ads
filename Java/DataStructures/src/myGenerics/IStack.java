package myGenerics;

import java.util.Iterator;

public interface IStack<T> {
	// insert a new item onto stack
	void push(T item);
	
	// remove and return an item from the top of the stack
	T pop();
	
	// check if the stack is empty
	boolean isEmpty();
	
	// get the current number of items in the stack
	int size();
	
	// returns an iterator for the particular implementation
	public Iterator<T> iterator();
}
