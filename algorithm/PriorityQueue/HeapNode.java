package algorithm.priorityQueue;

public class HeapNode<T> {
	private int priority;
	private T data;
	
	public HeapNode() {
		priority = 0;
		data = null;
	}
	public HeapNode(int priority) {
		this.priority = priority;
		this.data = null;
	}
	public HeapNode(int priority, T data) {
		this.priority = priority;
		this.data = data;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
