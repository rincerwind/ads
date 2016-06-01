package myGenerics;

public class Stack_LList<T> implements IStack<T> {
	private class StackNode{
		T val;
		StackNode next;
		
		public StackNode(T item, StackNode next){
			this.val = item;
			this.next = next;
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
}
