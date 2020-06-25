package algorithm;

public class LinkedList<T> implements List<T> {
	private Node header;
	private int length;

	public LinkedList() {
		header = new Node();
		length = 0;
	}

	private class Node {
		private T data;
		private Node next;

		Node() {
			data = null;
			next = null;
		}

		Node(final T data) {
			this.data = data;
			this.next = null;
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

	@Override
	public boolean add(T data, int index) {
		if (header != null) {
			if (index == 0) {
				addFirst(data);
				return true;
			}

			Node prev = getNode(index - 1);
			Node next = getNode(index);
			Node newNode = new Node(data);

			newNode.next = next;
			prev.next = newNode;
			length++;
			return true;
		}
		return false;
	}

	@Override
	public boolean addFirst(T data) {
		if (header != null) {
			Node newNode = new Node(data);
			if (header.next == null) {
				header.next = newNode;
				length++;
				return true;
			} else {
				newNode.next = header.next;
				header.next = newNode;
				length++;
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addLast(T data) {
		if (header != null) {
			if (length == 0) {
				addFirst(data);
				return true;
			}
			Node next = getNode(length - 1);
			Node newNode = new Node(data);
			next.next = newNode;
			length++;
			return true;
		}
		return false;
	}

	@Override
	public boolean add(T data) {
		addLast(data);
		return false;
	}

	@Override
	public T get(int index) {
		if (header == null && length == 0) {
			throw new IndexOutOfBoundsException();
		}
		Node cur = header;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.data;
	}

	@Override
	public int size() {
		return this.length;
	}

	@Override
	public T getLast() {
		return get(length);
	}

	@Override
	public T getFirst() {
		return get(0);
	}

	@Override
	public T remove(int index) {
		if (header != null) {
			if (length < index) {
				throw new IndexOutOfBoundsException();
			} else if (length <= 0 || index < 0) {
				throw new IndexOutOfBoundsException();
			} else if (index == 0) {
				return removeFirst();
			} else {
				Node prev = getNode(index - 1);
				Node remove = getNode(index);
				if (remove.next == null) {
					prev.next = null;
					length--;
					return remove.data;
				} else {
					prev.next = remove.next;
					length--;
					return remove.data;
				}
			}
		}
		return null;
	}

	@Override
	public T removeFirst() {
		if (header == null && length ==0 ) { 
			throw new IndexOutOfBoundsException();
		}
	
		Node remove = header.next;
		header.next = remove.next;
		length--;
		return remove.data;
	}

	@Override
	public T removeLast() {
		return remove(length - 1);
	}

	@Override
	public T remove() {
		return removeLast();
	}

	@Override
	public String toString() {
		Node temp = header.next;
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
