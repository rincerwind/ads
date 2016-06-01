package myGenerics;

import java.util.Iterator;

public class Stack_Resizing<T> implements IStack<T> {
	/*
	 * Useful when I only care a total amount of time it takes to do the
	 * operations. Not useful if I want to land a plane, where resizing can
	 * occur in the wrong moment.
	 * */
	
	private T[] storage;
	private int top;
	
	private class ArrayIterator implements Iterator<T>{
		private int current = top;
		
		@Override
		public boolean hasNext() {
			return current == 0;
		}

		@Override
		public T next() {
			T item = storage[current--];
			return item;
		}
		
	}
	
	public Stack_Resizing(){
		storage = (T[])new Object[1];
		top = 0;
	}
	
	/*
	 * this makes push and pop have O(N) complexity in the worst case
	 * but O(1) in the amortized
	 * */
	private void resize(int new_len){
		T[] copy = (T[])new Object[new_len];
		for(int i = 0; i < Math.min(new_len, storage.length); i++)
			copy[i] = storage[i];
		storage = copy;
	}
	
	@Override
	public void push(T item) {
		if( top == storage.length )
			resize(2*storage.length);
		storage[top++] = item;
	}

	@Override
	public T pop() {
		if(top - 1 < 0)
			return null;
		T item = storage[--top];
		storage[top] = null;
		if(top > 0 && top == storage.length/4)
			resize(storage.length/2);
		return item;
	}

	@Override
	public boolean isEmpty() {
		return top == 0;
	}

	@Override
	public int size() {
		return top;
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}
}
