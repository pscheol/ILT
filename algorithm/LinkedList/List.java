package algorithm;

public interface List<T> {
	// 임의의 위치에 데이터 삽입
	public boolean add(T data, int index);

	// 맨 첫번째에 데이터 삽입
	public boolean addFirst(T data);

	// 맨 마지막에 데이터 삽입
	public boolean addLast(T data);

	// 맨 끝에 데이터 삽입
	public boolean add(T data);

	// 인덱스 값을 통해 임의 위치 값 줄력
	public T get(int index);

	// 리스트의 크기 값
	public int size();

	// 마지막 리스트 의 값 출력
	public T getLast();

	// 맨 첫번째 리스트의 값 출력
	public T getFirst();

	// 임의 위치에 삭제
	public T remove(int index);

	// 첫번째 위치 삭제
	public T removeFirst();

	// 마지막 위치 삭제
	public T removeLast();

	// 마지막 위치 삭제
	public T remove();
}
