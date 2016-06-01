package myGenerics;

import java.util.Iterator;

public class Stack_Array<T> implements IStack<T> {
	private T[] storage;
	private int top;
	private int cap;
	
	private class ArrayIterator implements Iterator<T>{
		private int current = top;
		
		@Override
		public boolean hasNext() {
			return current > 0;
		}

		@Override
		public T next() {
			T item = storage[--current];
			return item;
		}
		
	}
	
	public Stack_Array(int cap){
		storage = (T[])new Object[cap];
		top = 0;
		this.cap = cap;
	}
	
	@Override
	public void push(T item) {
		if( top >= cap )
			return;
		storage[top++] = item;
	}

	@Override
	public T pop() {
		if(top - 1 < 0)
			return null;
		T return_item = storage[--top];
		storage[top] = null; // so GC can reclaim memory
		return return_item;
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
