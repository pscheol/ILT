package algorithm;

public class ListMain {
	public static void main(String[] args) {
	List<Integer> list = new LinkedList<>();
	list.add(10);
	list.add(20);
	list.add(30);
	list.addFirst(40);
	list.addLast(50);
	list.addLast(70);
	list.addLast(80);
	list.addFirst(100);
	list.addFirst(110);
	list.add(120, 1);
	list.add(130);
	list.add(140, 5);

	list.remove();
	list.remove();
	list.removeLast();
	list.removeLast();
	list.removeFirst();
	list.remove(5);
	
//	list.remove(0);
//	list.remove();
	System.out.println(list + " + "  +list.size());
	
	}
}
