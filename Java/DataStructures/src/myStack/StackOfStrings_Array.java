package myStack;

public class StackOfStrings_Array implements iStackOfStrings {

	private String[] storage;
	private int top;
	private int cap;
	
	public StackOfStrings_Array(int cap){
		storage = new String[cap];
		top = 0;
		this.cap = cap;
	}
	
	@Override
	public void push(String item) {
		if( top >= cap )
			return;
		storage[top++] = item;
	}

	@Override
	public String pop() {
		if(top - 1 < 0)
			return null;
		String return_item = storage[--top];
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

}
