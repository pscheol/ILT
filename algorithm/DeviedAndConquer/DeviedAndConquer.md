Devied And Conquer(분할정복)
----------------------------

#### 정의

-	기본적으로는 엄청나게 크고 방대한 문제를 조금씩 조금씩 나눠가면서 직접 풀 수 있는 문제가 간단해질 때까지 재귀적으로 분할하고 부속문제들의 해답들이 다시 합쳐서 해결하자는 개념

-	문제를 더 이상 나눌 수 없을 때까지 나눈 후 문제들을 해결하며 병합하여 결과 값을 얻는 알고리즘

##### 알고리즘 설계 방법

(1) Devied(분할) : 문제가 분할이 가능한 경우, 2개 이상 하위 문제로 나눈다. (2) Recursion(재귀) : 재귀적으로 부속문제들을 푼다. (3) Conquer(정복) : 해답들을 적절하게 합친다.

##### 중요사항

-	문제를 잘 나누는 것
-	문제를 잘 합치는 것
-	분할 정복 기법에서는 재귀를 이용하여 비슷한 종류의 부속 문제들을 해결

##### 장점

-	어려운 문제를 해결한다.(하노이탑 등)
-	병렬화(Paralleism) : 프로세스 시스템
-	메모리접근(Memory Access) : 캐시의 효율성

##### 단점

-	재귀가 느리다는 것(반복적인 부속문제 호출 부담)
-	재귀를 저장하기 위해 스택이 필요.
-	어떤 문제들에서는 반복 방법보다 더 복잡할 수 있다.

#### Merge Sort

###### 분할(devied)

(1) 정렬할 데이터 집합을 반으로 나눈다.  
(2) 나누어진 하위데이터 집합의 크기가 2 이상이면 하위 데이터 집합에 대해 (1)을 반복  
(3) 원래 같은 집합에서 나누어진 하위 데이터 집합 둘을 병합하여 하나의 데이터 집합으로 만든다. (병합할 때 데이터 집합의 원소는 순서에 맞춰 정렬)  
(4) 데이터 집합이 다시 하나가 될 때까지 (3)을 반복

###### 병합(combine)

(1) 두 데이터의 집합을 합한 것 만큼 비어 있는 데이터 집합을 마련  
(2) 두 데이터의 집합의 첫 번째 요소들을 비교하여 작은 요소를 새 데이터 집합에 추가한다. 그리고 새 데이터 집합에 추가된 요소는 원래 데이터 집합에서 삭제한다.  
(3) 양쪽 데이터 집합이 빌 때까지 (2) 의 과정을 반복

#### 구현

MergeSort

```java
/**
 * 1. 데이터를 나눌 수 있는지 확인
 * 2. 중간 값 계산
 * 3. 오른쪽 분할
 * 4. 왼쪽 분할
 * 5. 결합
 *
 * @param data
 * @param startIdx
 * @param endIdx
 */
public static void mergeSort(int[] data, int startIdx, int endIdx) {
  int midIdx = 0;
  if (endIdx - startIdx < 1) {
    return;
  }
  midIdx = (startIdx + endIdx) / 2;
  mergeSort(data, startIdx, midIdx);
  mergeSort(data, midIdx + 1, endIdx);
  combine(data, startIdx, midIdx, endIdx);
}
```

Combine

```java

	/**
	 * 데이터 결합
	 *
	 * 1. 데이터 비교 후 정렬
	 * 2. 남은 데이터 정렬
	 *
	 * @param data
	 * @param startIdx
	 * @param midIdx
	 * @param endIdx
	 */
	private static void combine(int[] data, int startIdx, int midIdx, int endIdx) {
		int[] destData = new int[(endIdx - startIdx) + 1];
		int rightIdx = midIdx + 1;
		int leftIdx = startIdx;
		int destIdx = 0;

		while (leftIdx <= midIdx && rightIdx <= endIdx) {
			if (data[leftIdx] < data[rightIdx]) {
				destData[destIdx] = data[leftIdx];
				leftIdx++;
			} else {
				destData[destIdx] = data[rightIdx];
				rightIdx++;
			}
			destIdx++;
		}
		while (leftIdx <= midIdx) {
			destData[destIdx++] = data[leftIdx++];
		}

		while (rightIdx <= endIdx) {
			destData[destIdx++] = data[rightIdx++];
		}
		destIdx = 0;
		for (int i = startIdx; i <= endIdx; i++) {
			data[i] = destData[destIdx++];
		}
	}
```

### 거듭제곱

Sequence

```java
public static long power(int base, int exp) {
  long result = 1;
  for(int i=0; i<exp; i++) {
    result *= base;
  }
  return result;
}
```

Recursion

```java
/**
 * C(n) = C^n/2 C^n/2 (even)
 * C(n) = C^(n-1)/2 C^(n-1)/2 * C(odd)
 * exp == 1=> base
 * exp == 0 => 1
 * @param base
 * @param exp
 * @return
 */
public static long power1(int base, int exp) {
  if (exp == 1) {
    return base;
  } else if (exp == 0) {
    return 1;
  }

  if (exp % 2 == 0) {
    long newBase = power1(base, exp/2);
    return newBase * newBase;
  } else {
    long newBase = power1(base, (exp-1)/2);
    return (newBase * newBase) * base;
  }
}
```

### 피보나치 수

F(0) = 0  
F(1) = 1  
F(n) = F(n-1) + F(n-2)

```java
/**
 * F(0) = 0
 * F(1) = F(2)= 1
 * F(n) = F(n-1) + F(n-2)
 * @param n
 * @return
 */
public static long fibonacci(int n) {
  if (n == 0) {
    return 0;
  } else if (n == 1 || n == 2) {
    return 1;
  }
  return fibonacci(n-1) + fibonacci(n-2);
}
```

2x2 행렬 피보나치 수열 찾기

```java
public static long fibonacciMatrix(int n) {
  long[][] matrix2x2 = new long[2][2];
  matrix2x2[0][0] = 1;
  matrix2x2[0][1] = 1;
  matrix2x2[1][0] = 1;
  matrix2x2[1][1] = 0;

  matrix2x2 = matrix2x2_Power(matrix2x2, n);
  return matrix2x2[0][1];
}


private static long[][]  matrix2x2_Power(long[][] matrixA, int n) {
  if (n > 1) {
    matrixA = matrix2x2_Power(matrixA, n /2);
    matrixA = matrix2x2_multiply(matrixA, matrixA);
    if ((n & 1)  == 1) {
      long[][] matrixB = new long[2][2];
      matrixB[0][0] = 1;
      matrixB[0][1] = 1;
      matrixB[1][0] = 1;
      matrixB[1][1] = 0;
      matrixA = matrix2x2_multiply(matrixA, matrixB);
    }
  }
  return matrixA;
}

private static long[][] matrix2x2_multiply(long[][] matrixA, long[][] matrixB) {
  long[][] result = new long[2][2];
  result[0][0] = matrixA[0][0] * matrixB[0][0] + matrixA[0][1] * matrixB[1][0];
  result[0][1] = matrixA[0][0] * matrixB[0][1] + matrixA[0][1] * matrixB[1][1];
  result[1][0] = matrixA[1][0] * matrixB[0][0] + matrixA[1][1] * matrixB[1][0];
  result[1][1] = matrixA[1][0] * matrixB[0][1] + matrixA[1][1] * matrixB[1][1];
  return result;
}
```
