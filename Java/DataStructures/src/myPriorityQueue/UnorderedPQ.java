package myPriorityQueue;

public class UnorderedPQ<T extends Comparable<T>> implements IPriorityQueue<T> {

	private T[] storage;
	private int size_;
	private POrder order;
	
	public UnorderedPQ(int capacity, POrder o){
		storage = (T[])new Comparable[capacity+1];
		size_ = 0;
		order = o;
	}
	
	public UnorderedPQ(T[] items){}
	
	@Override
	public void insert(T item) {
		storage[size_++] = item;
	}

	@Override
	public T removeFirst() {
		int first = 0;
		
		for(int i = 1; i < size_; i++)
			if(order == POrder.LESSER && storage[first].compareTo(storage[i]) > 0)
				first = i;
			else if(order == POrder.GREATER && storage[first].compareTo(storage[i]) < 0)
				first = i;
		
		T temp = storage[first];
		storage[first] = storage[size_-1];
		storage[size_-1] = temp;
		return storage[--size_];
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
