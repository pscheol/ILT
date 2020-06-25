Sort(정렬)
==========

-	데이터를 정렬 하는 것!

##### 정렬 알고리즘의 방법

-	내부 정렬 알고리즘(Internal Sort Algorithm) : 메모리에 있는 데이터를 정렬
-	외부 정렬 알고리즘(External Sort Algorithm) : 파일이나 외부의 특정 저장공간에 있는 데이터를 정렬
-	직접 정렬 알고리즘(Direct Sort Algorithm) : 실제 데이터를 직접 재배치하며 정렬
-	간접 정렬 알고리즘(Indirect Sort Algorithm) :데이터가 저장된 주소 값만을 바꾸어 정렬

##### 정렬 알고리즘의 선택

1.	어떤 데이터를 사용하는가?
2.	어떤 정렬 조건을 사용하는가?

##### 종류

-	Insert Sort
-	Selection Sort
-	Bubble Sort
-	Merge Sort
-	Quick Sort

등이 있다.

##### 1. Selection Sort

-	데이터의 처음부터 끝까지 쭉 흝어가면서 가장 적은 값을 찾아 그 값을 첫 번째 데이터와 자리를 바꾸고, 두 번째로 작은 데이터를 찾아 두 번째 데이터와 자리를 바꾸는 방법으로 해당 위치에 맞는 자료를 선택하여 위치를 교환하는 정렬 방식
-	loop문을 반복하면서 현재 정렬되지 않은 데이터 중 가장 작은 값을 '선택'

```java
/**
* 1. i번째 위치 선택
* 2. i+1 번째 위치부터 마지막위치까지 loop를 돌면서 최소 값을 선택
* 3. i번째 위치와 min 번째 위치와 데이터 교환
* (n-1)+(n-2) = n(n-1) = O(n^2)
*/
void selectionSort(int[] list) {
  int min = 0, dummy = 0, length = list.length;
  for (int i = 0; i < length-1; i++) {
    min = i;
    for (int j = i + 1; j < length; j++) {
      if (list[min] > list[j]) //선택정렬 비교연산
        min = j;
    }
    dummy = list[i];
    list[i] = list[min];
    list[min] = dummy;
  }
}
```

##### 2. Insert Sort

-	자료 배열의 모든 요소를 앞에서부터 차례대로 이미 정렬된 배열 부분과 비교하여, 자신의 위치를 찾아 삽입함으로써 정렬을 완성하는 알고리즘으로 정렬이 안된 부분의 데이터를 정렬 된 부분의 특정 위치에 삽입해 가면서 정렬을 진행한다.

```java
/**
* 1. i번 째 위치 j에 삽입(2번째 loop문)
* 2. j-1 > j 보다 크면 swap
*/
void insertSort(int[] list) {
  int dummy = 0, length = list.length;
  for (int i = 1; i < length; i++) {
    for (int j = i; j > 0 && list[j - 1] > list[j]; j--) {
      dummy = list[j - 1];
      list[j - 1] = list[j];
      list[j] = dummy;
    }
  }
}

/**
* 1. i번째 데이터를 dummy에 삽입
* 2. dummy와 list[j]와 비교하여 swap
* 3. dummy데이터를 list[j+1]번째에 삽입
*/
void insertSort2(int[] list) {
  int j=0 , dummy = 0, length = list.length;
  for(int i =1; i<length; i++) {
    dummy = list[i];
    j = i-1;
    while((j>=0) && (list[j] > dummy)) {
      list[j+1]= list[j];
      j--;
    }
    list[j+1] = dummy;
  }
}
```

##### 3. Bublle Sort

-	인접한 두 개의 데이터를 비교해가면서 정렬을 진행하는 방식

```java
void bubbleSort(int[] list) {
		int dummy = 0, length = list.length;
		for (int i =0; i<length-1; i++) {
			for(int j=0; j<(length-i)-1; j++) {
				if (list[j] > list[j+1]) {
					dummy = list[j];
					list[j] = list[j+1];
					list[j+1] = dummy;
				}
			}
		}
	}
```

##### 4. Merge Sort

-	Devied And Conquer에 근거하여 만들어진 정렬 방법
-	문제를 Devied 하고 Conquer하는 방법
-	Conquer하고 난 후 내용을 combine을 해야함

Devied And Conquer 3단계

1.	Devied : 문제를 분할 할 수 있을 때까지 문제를 분할한다.
2.	Conquer : 해결이 용이한 수준까지 분할된 문제를 해결
3.	Combine : 분할해서 해결한 결과를 결합한다.

```java
void mergeSort(int[] list, int left, int right) {
  int mid = 0;
  if ((right - left) < 1) {
    return;
  }
  mid = (left + right) / 2;
  mergeSort(list, left, mid);
  mergeSort(list, mid + 1, right);
  merge(list, left, mid, right);
}

void merge(int[] list, int start, int mid, int end) {
  int left = start;
  int right = mid + 1;
  int[] dest = new int[(end - start) + 1];
  int idx = 0;
  while (left <= mid && right <= end) {
    if (list[left] < list[right]) {
      dest[idx] = list[left++];
    } else {
      dest[idx] = list[right++];
    }
    idx++;
  }

  while (left <= mid) {
    dest[idx++] = list[left++];
  }
  while (right <= end) {
    dest[idx++] = list[right++];
  }

  for (int i = start, j = 0; i <= end; i++) {
    list[i] = dest[j++];
  }
}
```

##### 5. Quick Sort

-	Devied And Conquer에 기반하여 만들어진 정렬방법으로 기준 값을 중심으로 전체 원소들을 왼쪽 부분집합과 오른쪽 부분집합으로 분할(Divide)하고 왼쪽 부분 집합에는 기준 값보다 작은 원소들을 이동시키고, 오른쪽 부분 집합에는 기준 값보다 큰 원소들을 이동시킨다.

-	Quick Sort는 pivot을 어떻게 선택하느냐에 따라 알고리즘의 성능을 좌우한다.

Quick Sort의 요소들

-	left : 정렬 대상의 가장 왼쪽 지점

-	right : 정렬 대상의 가장 오른쪽 지점

-	pivot : 중심점, 중심축, 기준

-	low : pivot을 제외한 가장 왼쪽에 위치한 지점으로 pivot 보다 정렬의 우선순위가 낮은 데이터를 만날 때까지(pivot 보다 큰 값) 오른쪽 방향으로 이동한다.

-	high : pivot을 제외한 가장 오른쪽에 위치한 지점으로 pivot 보다 정렬의 우선순위가 높은 데이터를 만날 때까지(pivot보다 작은 값) 왼쪽 방향으로 이동한다.

정렬방법

1.	pivot을 정하고 low 와 high을 설정한다.
2.	기준이 되는 pivot은 low와 high를 비교한다.
3.	기준이 되는 데이터보다 작으면 왼쪽으로 이동하고 기준이 되는 데이터가 크면 오른쪽으로 이동한다.
4.	low는 pivot보다 작으면 low의 위치는 오른쪽으로 한칸 이동하고 pivot 보다 크면 잠시 멈추고 high와 pivot과 비교하며 이동한다.
5.	high는 pivot보다 크면 high의 위치는 왼쪽으로 한칸 이동하고 pivot 보다 작으면 멈추고 low와 high위치에 있는 데이터를 swap한다.
6.	low와 high의 위치가 교차할 때까지 2,3,4, 5를 반복한다
7.	low와 high의 위치가 교차되면 pivot의 데이터와 high의 위치에 있는 데이터를 swap한다
8.	정렬이 안되어 있다면 분할하고 위 조건을 반복한다.

```java
void quickSort(int data[], int left, int right) {
		if (left < right) {
			int pivot = partition(data, left, right);
			quickSort(data, left, pivot - 1);
			quickSort(data, pivot + 1, right);
		}
	}

	int partition(int[] data, int left, int right) {
		int pivot = data[left];
		int low = left;
		int high = right+1;

		do {
			do {
				low++;
			}while(low <= right && pivot > data[low]);
			do {
				high--;
			} while(high >= left && pivot < data[high]);
			if (low < high) {
				swap(data, low, high);
			}
		} while(low < high);

		swap(data, left, high);
		System.out.print("left : "+ data[left]  + ", pivot : "+ pivot + ", ");
		for(int i : data) {
			System.out.print(i + " ");
		}
		System.out.println();
		return high;
	}

	void swap(int[] data, int idx1, int idx2) {
		int tmp = data[idx1];
		data[idx1] = data[idx2];
		data[idx2] = tmp;
	}
```

pivot이 중간값 인경우

```java

	/**
	 * pvivot이 중간 으로 세팅
	 * @param list
	 * @param left
	 * @param right
	 */
	void quickSort2(int[] list, int left, int right) {
		if (left < right) {
			int pivot = partitionByCenter(list, left,right);
			quickSort2(list, left, pivot-1);
			quickSort2(list, pivot+1, right);
		}
	}

	int partitionByCenter(int[] list, int left, int right) {
		int mid = (left+right) / 2;
		int pivot = list[mid];
		int low = left+1;
		int high = right;
		swap(list, mid, left);
		while (low <= high) {
			while(low <= right && pivot > list[low]) {
				low++;
			}
			while(high >= left && pivot < list[high]) {
				high--;
			}
			if (low < high) {
				swap(list, low, high);		
			}
		}

		swap(list, left, high);
		System.out.print("left : "+ list[left]  + ", pivot : "+ pivot + ", ");
		for(int i : list) {
			System.out.print(i + " ");
		}
		System.out.println();
		return high;
	}
```
