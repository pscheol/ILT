package algorithm;

public class CircularLinkedList<T> implements List<T> {

	private Node header;
	private int length;

	private class Node {
		private T data;
		private Node prev;
		private Node next;

		public Node(T data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}

		public Node() {
			this.data = null;
			this.prev = null;
			this.next = null;
		}
	}

	public CircularLinkedList() {
		header = new Node();
		header.prev = header;
		header.next = header;
		this.length = 0;

	}

	@Override
	public boolean add(T data, int index) {
		if (header != null) {
			if (index == 0) {
				return addFirst(data);
				
			} else if (index == length - 1) {
				return addLast(data);
			} else {
				Node prev = getNode(index - 1);
				Node newNode = new Node(data);

				newNode.next = prev.next;
				newNode.prev = prev;
				prev.next.prev = newNode;
				prev.next = newNode;
				length++;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addFirst(T data) {
		if (header != null) {
			Node newNode = new Node(data);
			Node nextNode = header.next;
			newNode.next = nextNode;
			newNode.prev = header;
			nextNode.prev = newNode;
			header.next = newNode;
			length++;
			return true;
		}
		return false;
	}

	@Override
	public boolean addLast(T data) {
		if (header != null) {
			Node newNode = new Node(data);
			Node tailNode = header.prev;
			tailNode.next = newNode;
			newNode.prev = tailNode;
			newNode.next = header;
			header.prev = newNode;
			length++;
			return true;
		}
		return false;
	}

	@Override
	public boolean add(T data) {
		return addLast(data);
	}

	@Override
	public T get(int index) {
		if (header == null && length == 0) {
			throw new IndexOutOfBoundsException();
		}
		T data = null;
		if (index == 0) {
			data = getFirst();
		} else if (index == length - 1) {
			data = getLast();
		} else {
			Node cur = header;
			for (int i = 0; i < index; i++) {
				cur = cur.next;
			}
		}
		return data;
	}

	@Override
	public int size() {
		return this.length;
	}

	@Override
	public T getLast() {
		return header.prev.data;
	}

	@Override
	public T getFirst() {
		return header.next.data;
	}

	@Override
	public T remove(int index) {
		if (length < index) {
			throw new IndexOutOfBoundsException();
		} else if (length <= 0 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if (header != null) {
		  if (index == 0) {
				return removeFirst();
			} else if (index == length - 1) {
				return removeLast();
			} else {
				Node remove = getNode(index);
				Node prev = remove.prev;
				prev.next = remove.next;
				remove.next.prev = prev;
				remove.next = null;
				remove.prev = null;
				length--;
				return remove.data;
			}
		}
		return null;
	}

	@Override
	public T removeFirst() {
		if (header == null && length == 0) {
			throw new IndexOutOfBoundsException();
		}

		Node remove = header.next;
		if (remove.next == header) {
			header.next = header;
			header.prev = header;
		} else {
			header.next = remove.next;
			remove.next.prev = header;
		}
		remove.next = null;
		remove.next = null;
		length--;
		return remove.data;
	}

	@Override
	public T removeLast() {
		if (header == null && length <= 0) {
			throw new IndexOutOfBoundsException();
		}
		Node remove = header.prev;
		
		if (remove.prev == header) {
			header.next = header;
			header.prev = header;
		} else {
			remove.prev.next = header;
			header.prev = remove.prev;
		}
		remove.next = null;
		remove.prev = null;
		length--;
		return remove.data;
	}

	@Override
	public T remove() {
		return removeLast();
	}

	@Override
	public String toString() {
		Node temp = header.next;
		if (temp == header) {
			return "[ ]";
		} else {
			// StringBuilder 클래스를 이용하여 데이터를 출력
			StringBuilder sb = new StringBuilder("[");
			sb.append(temp.data);
			temp = temp.next;
			while (temp != header) {
				sb.append(", ");
				sb.append(temp.data);
				temp = temp.next;
			}
			sb.append("]");
			return sb.toString();
		}
	}

	private Node getNode(int index) {
		if (header == null && (length == 0 || index <= 0 || index > length)) {
			throw new IndexOutOfBoundsException();
		}
		Node cur = header;
		for (int i = 0; i <= index; i++) {
			cur = cur.next;
		}
		return cur;
	}
}
