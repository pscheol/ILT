package algorithm;

public class DeviedAndConquer {

	public static void main(String[] args) {
		int n = 10;
		
		long result  = fibonacciMatrix(n);
		System.out.println(result);
	}
	public static int factorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		} else {
			return n * factorial(n - 1);
		}
	}

	/**
	 * 1. 데이터를 나눌 수 있는지 확인
	 *  2. 중간 값 계산
	 *  3. 오른쪽 분할
	 *  4. 왼쪽 분할
	 *   5. 결합
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

	/**
	 * 데이터 결합
	 * 
	 * 1. 데이터 비교 후 정렬 2. 남은 데이터 정렬
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

	public static long power(int base, int exp) {
		long result = 1;
		for(int i=0; i<exp; i++) {
			result *= base;
		}
		return result;
	}
	
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

	//1 부터 n 까지의 합을 구하는 분할정복 알고리즘
	public static int fastSum(int n) {
		if (n == 1)
			return 1;
		if (n % 2 == 1)
			return fastSum(n - 1) + n;
		return 2 * fastSum(n / 2) + (n / 2) * (n / 2);
	}
}
