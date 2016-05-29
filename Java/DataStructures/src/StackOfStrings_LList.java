
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
	
	public StackOfStrings_LList(){
		top = null;
	}
	
	@Override
	public void push(String item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
