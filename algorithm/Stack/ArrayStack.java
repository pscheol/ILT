package algorithm;

public class ArrayStack<T> implements Stack<T> {
	private static int DEFAULT_SIZE = 10;
	private static int top;
	private static int capacity;
	private Object[] stack;
	
	public ArrayStack() {
		top = -1;
		capacity = DEFAULT_SIZE;
		stack = new Object[capacity];
	}
	public ArrayStack(int capacity) {
		top = -1;
		capacity = DEFAULT_SIZE;
		stack = new Object[capacity];
	}
	@Override
	public void push(T data) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("stack overflow");
		}
		stack[++top] = data;
	}
	@Override
	public T pop() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("data impty");
		}
		return (T)stack[top--];
	}
	@Override
	public T peek() {
		return (T)stack[top];
	}
	@Override
	public boolean isFull() {
		if (top == capacity-1) {
			return true;
		}
		return false;
	}
	@Override
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}
	
}
