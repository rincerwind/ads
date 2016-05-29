
public class StackOfStrings_LList implements iStackOfStrings {
	
	private class StackNode{
		String val;
		StackNode next;
		
		public StackNode(){
			this.val = "";
			this.next = null;
		}
		
		public StackNode(String item, StackNode next){
			this.val = item;
			this.next = next;
		}
	}
	
	private StackNode top;
	private int size_;
	
	public StackOfStrings_LList(){
		top = null;
		size_ = 0;
	}
	
	@Override
	public void push(String item) {
		StackNode new_item = new StackNode(item, top);
		
		if(new_item != null){
			top = new_item;
			size_++;
		}
	}

	@Override
	public String pop() {
		String top_val = null;
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
