package algorithm;

public class LinkedQueue<T> implements Queue<T> {

	private Node front;
	private Node rear;
	private static int length;
	private class Node {
		private T data;
		private Node next;
		public Node() {
			this.data = null;
			this.next = null;
		}
		public Node(T data) {
			this.data =data;
			this.next = null;
		}
	}
	
	public LinkedQueue() {
		this.length = 0;
	}
	@Override
	public void enqueue(T data) {
		Node newNode = new Node(data);
		if (front == null) {
			front = newNode;
			rear = newNode;
		} else {
			rear.next = newNode;
			rear = newNode;
		}
		length++;
	}

	@Override
	public T dequeue() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("is empty");
		}
		Node remove = front;
		front = front.next;
		remove.next = null;
		length--;
		return remove.data;
	}


	@Override
	public boolean isEmpty() {
		if (length == 0) {
			return true;
		}
		return false;
	}
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		Node temp = front;
		if (temp == null) {
			return "[ ]";
		} else {
			// StringBuilder 클래스를 이용하여 데이터를 출력
			StringBuilder sb = new StringBuilder("[");
			sb.append(temp.data);
			temp = temp.next;
			while (temp != null) {
				sb.append(", ");
				sb.append(temp.data);
				temp = temp.next;
			}
			sb.append("]");
			return sb.toString();
		}
	}
}
