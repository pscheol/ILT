package algorithm;

public class LinkedListStack<T> implements Stack<T> {

	private Node stack;
	private Node top;
	private static int length;

	public class Node {
		private T data;
		private Node top;

		public Node() {
			this.data = null;
			this.top = null;
		}

		public Node(T data) {
			this.data = data;
			this.top = null;
		}
	}

	public LinkedListStack() {
		stack = new Node();
		top = new Node();
		length = 0;
	}

	@Override
	public void push(T data) {
		Node newNode = new Node(data);
		if (stack.top == null) {
			stack.top = newNode;
		} else {
			Node oldStack = stack;
			do {
				oldStack = oldStack.top;
			} while (oldStack.top != null);
			oldStack.top = newNode;
		}
		top = newNode;
		length++;
	}

	@Override
	public T pop() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Stack is Empty ");
		}
		Node remove = top;
		if (stack.top == top) {
			stack.top = null;
			top = null;
		} else {
			Node curTop = stack;
			do {
				curTop = curTop.top;
			} while (curTop.top != top);
			curTop.top = null;
			top = curTop;

		}
		length--;
		return remove.data;
	}

	@Override
	public T peek() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("stack is empty");
		}
		return top.data;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		if (length == 0 && stack.top == null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		Node temp = stack.top;
		if (temp == null) {
			return "[ ]";
		} else {
			// StringBuilder 클래스를 이용하여 데이터를 출력
			StringBuilder sb = new StringBuilder("[");
			sb.append(temp.data);
			temp = temp.top;
			while (temp != null) {
				sb.append(", ");
				sb.append(temp.data);
				temp = temp.top;
			}
			sb.append("]");
			return sb.toString();
		}
	}
}
