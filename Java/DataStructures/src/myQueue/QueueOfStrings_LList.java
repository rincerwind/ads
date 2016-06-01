package myQueue;

public class QueueOfStrings_LList implements IQueueOfStrings {

	private class QueueNode{
		String item;
		QueueNode next;
		
		QueueNode(String newItem, QueueNode newNext){
			item = newItem;
			next = newNext;
		}
	}
	
	private QueueNode head;
	private QueueNode tail;
	private int size_;
	
	public QueueOfStrings_LList(){
		head = tail = null;
		size_ = 0;
	}
	
	@Override
	public void enqueue(String item) {
		QueueNode n = new QueueNode(item, null);
		
		if(n != null){
			if(head == null){
				head = tail = n;
				return;
			}
			tail.next = n;
			tail = n;
		}
	}

	@Override
	public String dequeue() {
		String item = null;
		if(head != null){
			item = head.item;
			head = head.next;
		}
		return item;
	}

	@Override
	public boolean isEmpty() {
		return size_ == 0;
	}

	@Override
	public int size() {
		return size_;
	}

}
