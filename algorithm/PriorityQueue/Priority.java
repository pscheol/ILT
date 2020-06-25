package algorithm.priorityQueue;

public interface Priority<T> {
	public void insertMaxHeap(HeapNode<T> node);
	public void insertMinHeap(HeapNode<T> node);
	public HeapNode<T> deleteMaxHeap();
	public HeapNode<T> deleteMinHeap();
	public boolean isEmpty();
	public int getUsedSize();
	public void printNode(HeapNode<T> node) ;
	public void heapPrint();
}