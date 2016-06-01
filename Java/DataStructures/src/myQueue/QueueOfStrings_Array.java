package myQueue;

public class QueueOfStrings_Array implements IQueueOfStrings {

	private String[] storage;
	private int head;
	private int tail;
	private int cap;
	private int size_;
	
	public QueueOfStrings_Array(int cap){
		storage = new String[cap];
		head = tail = 0;
		this.cap = cap;
		size_ = 0;
	}
	
	@Override
	public void enqueue(String item) {
		if(size_ < cap){
			storage[tail] = item;
			tail = (tail + 1) % cap;
			size_++;
		}
	}

	@Override
	public String dequeue() {
		String item = null;
		if( size_ > 0 ){
			item = storage[head];
			head = (head + 1) % cap;
			size_--;
		}
		return item;
	}

	@Override
	public boolean isEmpty() {
		return head == tail;
	}

	@Override
	public int size() {
		return size_;
	}

}
