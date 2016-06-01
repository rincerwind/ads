package myGenerics;

public class Queue_LList<T> implements iQueue<T> {
	private class QueueNode{
		T item;
		QueueNode next;
		
		QueueNode(T newItem, QueueNode newNext){
			item = newItem;
			next = newNext;
		}
	}
	
	private QueueNode head;
	private QueueNode tail;
	private int size_;
	
	public Queue_LList(){
		head = tail = null;
		size_ = 0;
	}
	
	@Override
	public void enqueue(T item) {
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
	public T dequeue() {
		T item = null;
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
