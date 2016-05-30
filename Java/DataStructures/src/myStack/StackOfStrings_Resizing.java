package myStack;

public class StackOfStrings_Resizing implements iStackOfStrings {

	/*
	 * Useful when I only care a total amount of time it takes to do the
	 * operations. Not useful if I want to land a plane, where resizing can
	 * occur in the wrong moment.
	 * */
	
	private String[] storage;
	private int top;
	
	public StackOfStrings_Resizing(){
		storage = new String[1];
		top = 0;
	}
	
	/*
	 * this makes push and pop have O(N) complexity in the worst case
	 * but O(1) in the amortized
	 * */
	private void resize(int new_len){
		String[] copy = new String[new_len];
		for(int i = 0; i < storage.length; i++)
			copy[i] = storage[i];
		storage = copy;
	}
	
	@Override
	public void push(String item) {
		if( top == storage.length )
			resize(2*storage.length);
		storage[top++] = item;
	}

	@Override
	public String pop() {
		if(top - 1 < 0)
			return null;
		String item = storage[--top];
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

}
