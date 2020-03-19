package algorithm;

public class ArrayQueue<T> implements Queue<T> {

	private Object[] queue;
	private int front;
	private int rear;
	private int capacity;
	private int length;
	private static final int DEFAULT_CAPACITY = 5;
	
	public ArrayQueue() {
		this.capacity = DEFAULT_CAPACITY;
		this.front = 0;
		this.rear = 0;
		this.length  = 0;
		this.queue = new Object[capacity];
	}
	public ArrayQueue(int capacity) {
		this.capacity = capacity;
		this.front = 0;
		this.rear = 0;
		this.length = 0;
		this.queue = new Object[capacity];
	}
	
	@Override
	public void enqueue(T data) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("queue is Full...");
		}
		int rear = (this.rear) % this.capacity;
		queue[rear] = data;
		this.rear = rear+ 1;
		this.length++;
		System.out.println("enqueue : rear : "+  rear +" front : "+ front);
	}

	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("queue is empty...");
		}
		int front = ((this.front) % this.capacity);
		Object data = queue[front];
		queue[front] = null;
		this.front  = front + 1;
		this.length--;
		System.out.println("dequeue : rear : "+  rear +" front : "+ front);
		return (T)data;
	}

	@Override
	public boolean isFull() {
		if (length == capacity) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
	
		if ( length == 0 || front == rear) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		if (length == 0) {
			return "[ ]";
		} else {
			StringBuilder sb = new StringBuilder("[");
			for(int i =0; i<length; i++) {
				sb.append(queue[i]);
				if (i != length-1) {
					sb.append(", ");	
				}
			}
			sb.append("]");
			return sb.toString();
		}
	}
	
}
