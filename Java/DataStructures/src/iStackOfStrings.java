
public interface iStackOfStrings {
	// insert a new item onto stack
	void push(String item);
	
	// remove and return an item from the top of the stack
	String pop();
	
	// check if the stack is empty
	boolean isEmpty();
	
	// get the current number of items in the stack
	int size();
}
