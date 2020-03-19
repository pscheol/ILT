package algorithm;

public interface Queue<T> {
	public void enqueue(T t);
	public T dequeue();
	public boolean isFull();
	public boolean isEmpty();
}
