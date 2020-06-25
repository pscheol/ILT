package algorithm.hash;

public interface Hash {
	public void put(String key, Object value);
	public Object get(String key);
	public void remove(String key);
}
