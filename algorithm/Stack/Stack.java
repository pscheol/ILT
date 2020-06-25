package algorithm;

public interface Stack<T> {
	public void push(T data);
	public T pop();
	public T peek();
	public boolean isFull();
	public boolean isEmpty();
}
