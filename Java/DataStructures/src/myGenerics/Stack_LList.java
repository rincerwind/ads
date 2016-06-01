package myGenerics;

import java.util.Iterator;

public class Stack_LList<T> implements IStack<T>, Iterable<T> {
	private class StackNode{
		T val;
		StackNode next;
		
		public StackNode(T item, StackNode next){
			this.val = item;
			this.next = next;
		}
	}
	
	private class ListIterator implements Iterator<T>{
		private StackNode current = top;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			T item = current.val;
			current = current.next;
			return item;
		}
		
	}
	
	private StackNode top;
	private int size_;
	
	public Stack_LList(){
		top = null;
		size_ = 0;
	}
	
	@Override
	public void push(T item) {
		StackNode new_item = new StackNode(item, top);
		
		if(new_item != null){
			top = new_item;
			size_++;
		}
	}

	@Override
	public T pop() {
		T top_val = null;
		if( !isEmpty() ){
			top_val = top.val;
			top = top.next;
			size_--;
		}
		return top_val;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public int size() {
		return size_;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}
}
