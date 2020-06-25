package algorithm.hash;

/**
 * 선형탐색법 사용, 이중 해싱, 리해싱.
 * 
 * @author pscheol
 *
 */
public class HashTable implements Hash {

	private TagHashTable hash;

	public HashTable() {
		this.hash = new TagHashTable();
	}

	public HashTable(int capacity) {
		this.hash = new TagHashTable(capacity);
	}

	private class TagHashTable {
		Node[] table = null;
		private int capacity = 0;
		private int hashTableCount = 0;
		private static final int DEFAULT_CAPACITY = 100;

		public TagHashTable(int capacity) {
			this.capacity = capacity;
			this.hashTableCount = 0;
			this.table = new Node[this.capacity];
		}

		public TagHashTable() {
			this.capacity = DEFAULT_CAPACITY;
			this.hashTableCount = 0;
			this.table = new Node[this.capacity];
		}
	}
	
	private class Node {
		private int key;
		private Object value;
		private boolean usaged;

		public Node() {
			this.key = 0;
			this.value = null;		
			this.usaged = false;		
		}

		public Node(int key, Object value) {
			this.key = key;
			this.value = value;
			this.usaged = false;
		}
	}

	
	/**
	 * 1. 해시테이블  크기 계산 
	 * 2. 해시테이블 크기가 50%가 넘으면 리해싱 
	 * 3. 중복키 확인 
	 * 4. 해시 적용 
	 */
	@Override
	public void put(String key, Object value) {
		if (isHashSizeOver()) {
			rehashing();
		}
		int key1 = hash(key, key.length(), hash.capacity);
		int key2 = hash2(key, key.length(), hash.capacity);
		while (hash.table[key1] != null && hash.table[key1].usaged != false && hash.table[key1].key != key1) {
			key1 = (key1 + key2) % hash.capacity;
		}
		Node newNode = new Node();
		newNode.key  = key1;
		newNode.value = value;
		newNode.usaged = true;
		hash.table[key1] = newNode;
		hash.hashTableCount++;
	}

	@Override
	public Object get(String key) {
		int key1 = hash(key, key.length(), hash.capacity);
		int key2 = hash(key, key.length(), hash.capacity);
		while(hash.table[key1] != null && hash.table[key1].usaged != false && hash.table[key1].key != key1) {
			key1 = (key1 + key2) % hash.capacity;
		}
		return hash.table[key1].value;
	}

	@Override
	public void remove(String key) {

	}

	private int hash(String key, int length, int capacity) {
		int hashKey = 0;
		
		for (int i =0; i<length; i++) {
			hashKey = (hashKey << 3) + key.charAt(i);
		}
		return hashKey % capacity;
	}

	private int hash2(String key, int length, int capacity) {
		int hashKey = 0;
		
		for (int i =0; i<length; i++) {
			hashKey = (hashKey << 3) + key.charAt(i);
		}
		return (hashKey % (capacity -3) ) + 1;
	}
	
	private boolean isHashSizeOver() {
		double size = hash.hashTableCount / hash.capacity;
		if (size > 0.5) {
			return true;
		}
		return false;
	}
	/**
	 * rehashing
	 * @return
	 */
	private void rehashing() {
		Node[] tmpTable = hash.table;
		hash.capacity = hash.capacity *2;
		Node[] newNode = new Node[hash.capacity];
		for(int i =0; i< tmpTable.length; i++) {
			if (tmpTable[i].usaged == true) {
				newNode[i] .key = tmpTable[i].key;
				newNode[i] .value = tmpTable[i].value;
				newNode[i] .usaged = tmpTable[i].usaged;
			}
		}
		hash.table = newNode;
	}
}
