package algorithm.priorityQueue;

public class PriorityQueue<T> implements Priority<T> {
	private Heaps heap;

	private class Heaps {
		private HeapNode[] nodes;
		private static final int DEFAULT_CAPACITY = 5;
		private int capacity;
		private int usedSize;

		public Heaps() {
			this.capacity = DEFAULT_CAPACITY;
			this.nodes = new HeapNode[this.capacity];
			this.usedSize = 0;
		}

		public Heaps(int capacity) {
			this.capacity = capacity;
			this.nodes = new HeapNode[this.capacity];
			this.usedSize = 0;
		}
	}

	public PriorityQueue() {
		heap = new Heaps();
	}

	public PriorityQueue(int capacity) {
		heap = new Heaps(capacity);

	}

	@Override
	public void insertMaxHeap(HeapNode<T> node) {
		int curPosition = heap.usedSize;
		int parentPosition = getParentIdx(curPosition);

		heapResize();

		heap.nodes[curPosition] = node;

		while (curPosition > 0 && heap.nodes[curPosition].getPriority() > heap.nodes[parentPosition].getPriority()) {
			heapSwap(curPosition, parentPosition);
			curPosition = parentPosition;
			parentPosition = getParentIdx(curPosition);
		}
		heap.usedSize++;
	}

	@Override
	public void insertMinHeap(HeapNode<T> node) {
		int curPosition = heap.usedSize;
		int parentPosition = getParentIdx(curPosition);

		heapResize();

		heap.nodes[curPosition] = node;

		while (curPosition > 0 && heap.nodes[curPosition].getPriority() < heap.nodes[parentPosition].getPriority()) {
			heapSwap(curPosition, parentPosition);
			curPosition = parentPosition;
			parentPosition = getParentIdx(curPosition);
		}
		heap.usedSize++;
	}

	@Override
	public HeapNode<T> deleteMaxHeap() {
		int leftPosition = 0;
		int rightPosition = 0;
		int parentPosition = 0;

		HeapNode remove = heap.nodes[parentPosition];
		heap.nodes[parentPosition] = heap.nodes[--heap.usedSize];
		heap.nodes[heap.usedSize+1] = null;

		leftPosition = getLeftChildIdx(parentPosition);
		rightPosition = getRightChildIdx(rightPosition);

		while (true) {
			int selectedPosition = 0;
			if (leftPosition >= heap.usedSize) {
				break;
			}
			if (rightPosition >= heap.usedSize) {
				selectedPosition = leftPosition;
			}

			if (heap.nodes[leftPosition].getPriority() > heap.nodes[rightPosition].getPriority()) {
				selectedPosition = leftPosition;
			} else {
				selectedPosition = rightPosition;
			}
			if (heap.nodes[selectedPosition].getPriority() > heap.nodes[parentPosition].getPriority()) {
				heapSwap(selectedPosition, parentPosition);
				parentPosition = selectedPosition;
			} else {
				break;
			}

			parentPosition = selectedPosition;
			leftPosition = getLeftChildIdx(parentPosition);
			rightPosition = getRightChildIdx(parentPosition);
		}
		heapResize();
		return remove;
	}

	@Override
	public HeapNode<T> deleteMinHeap() {
		int leftPosition = 0;
		int rightPosition = 0;
		int parentPosition = 0;

		HeapNode remove = heap.nodes[parentPosition];
		heap.nodes[parentPosition] = heap.nodes[--heap.usedSize];
		heap.nodes[heap.usedSize+1] = null;
		
		leftPosition = getLeftChildIdx(parentPosition);
		rightPosition = getRightChildIdx(rightPosition);

		while (true) {
			int selectedPosition = 0;
			if (leftPosition >= heap.usedSize) {
				break;
			}
			if (rightPosition >= heap.usedSize) {
				selectedPosition = leftPosition;
			}
			if (heap.nodes[leftPosition].getPriority() > heap.nodes[rightPosition].getPriority()) {
				selectedPosition = rightPosition;
			} else {
				selectedPosition = leftPosition;
			}
			if (heap.nodes[selectedPosition].getPriority() < heap.nodes[parentPosition].getPriority()) {
				heapSwap(selectedPosition, parentPosition);
				parentPosition = selectedPosition;
			} else {
				break;
			}

			parentPosition = selectedPosition;
			leftPosition = getLeftChildIdx(parentPosition);
			rightPosition = getRightChildIdx(parentPosition);
		}
		heapResize();
		return remove;
	}

	private int getParentIdx(int idx) {
		return (idx - 1) / 2;
	}

	private int getLeftChildIdx(int idx) {
		return (idx * 2) + 1;
	}

	private int getRightChildIdx(int idx) {
		return (idx * 2) + 2;
	}

	@Override
	public boolean isEmpty() {
		return heap.usedSize == 0;
	}

	private void heapSwap(int idx1, int idx2) {
		HeapNode tmp = heap.nodes[idx1];
		heap.nodes[idx1] = heap.nodes[idx2];
		heap.nodes[idx2] = tmp;
	}

	private void heapResize() {
		int newCapacity = 0;
		if (heap.capacity == heap.usedSize) {
			newCapacity = heap.capacity * 2;
		} else if (heap.usedSize < (heap.capacity / 2)) {
			newCapacity = heap.capacity /= 2;
		} else {
			return;
		}
		HeapNode[] tmp = new HeapNode[newCapacity];
		HeapNode[] oldheap = heap.nodes;
		for (int i = 0; i < getUsedSize(); i++) {
			tmp[i] = oldheap[i];
		}
		heap.capacity = newCapacity;
		heap.nodes = tmp;
	}
	
	public int getUsedSize() {
		return heap.usedSize;
	}
	public void heapPrint() {
		int i = 0;
		for (i = 0; i < heap.usedSize; i++) {
			System.out.printf("힙에 저장된 데이터 : %s (우선순위 : %d)\n", heap.nodes[i].getData(), heap.nodes[i].getPriority());
		}
		System.out.println();
	}

	public void printNode(HeapNode<T> node) {
		System.out.printf("작업명 : %s (우선순위 : %d)\n", node.getData(), node.getPriority());
	}
}
