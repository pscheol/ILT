package algorithm.priorityQueue;

import java.util.ArrayList;
import java.util.List;

public class HeapMain {
	public static void main(String[] args) {
		Priority<String> priorityQueue = new PriorityQueue<>();

		List<HeapNode<String>> list = new ArrayList<>();

		list.add(new HeapNode<>(34, "일하기"));
		list.add(new HeapNode<>(12, "이닦"));
		list.add(new HeapNode<>(84, "밥먹기"));
		list.add(new HeapNode<>(35, "회의하기"));
		list.add(new HeapNode<>(66, "디버깅"));
		list.add(new HeapNode<>(45, "코딩"));

		System.out.println("최소힙 ");
		for (HeapNode<String> node : list) {
			priorityQueue.insertMinHeap(node);
		}
		priorityQueue.heapPrint();
		System.out.printf("큐에 남아있는작업수 : %d\n", priorityQueue.getUsedSize());

		while (!priorityQueue.isEmpty()) {
			priorityQueue.printNode(priorityQueue.deleteMinHeap());
		}
		System.out.println();
		System.out.println();
		System.out.println();

		System.out.println("최대힙");
		for (HeapNode<String> node : list) {
			priorityQueue.insertMinHeap(node);
		}
		priorityQueue.heapPrint();
		System.out.printf("큐에 남아있는작업수 : %d\n", priorityQueue.getUsedSize());

		while (!priorityQueue.isEmpty()) {
			priorityQueue.printNode(priorityQueue.deleteMinHeap());
		}
	}
}
