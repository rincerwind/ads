package myGenerics;

public class Queue_Array<T> implements IQueue<T> {
	private T[] storage;
	private int head;
	private int tail;
	private int cap;
	private int size_;
	
	public Queue_Array(int cap){
		storage = (T[]) new Object[cap];
		head = tail = 0;
		this.cap = cap;
		size_ = 0;
	}
	
	@Override
	public void enqueue(T item) {
		if(size_ < cap){
			storage[tail] = item;
			tail = (tail + 1) % cap;
			size_++;
		}
	}

	@Override
	public T dequeue() {
		T item = null;
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
