package algorithm.sort;

import java.util.ArrayList;
import java.util.List;

public class Sort {

	private Sort() {

	}

	public static Sort getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static Sort INSTANCE = new Sort();
	}

	public List<Integer> randomNum(int size) {
		List<Integer> array = new ArrayList<>();

		for (int i = 0; i < size; i++) {
			while (true) {
				int rand = (int) (Math.random() * size) + 1;
				if (!array.contains(rand)) {
					array.add(rand);
					break;
				}
			}
		}
		return array;
	}

	public int[] randomNum2(int size) {
		int[] list = new int[size];
		for (int i = 0; i < size; i++) {
			while (true) {
				int rand = (int) (Math.random() * size) + 1;

				if (isNotEqulas(rand, list, i)) {
					list[i] = rand;
					break;
				}
			}
		}
		return list;
	}

	private boolean isNotEqulas(int rand, int[] list, int length) {
		for (int i = 0; i < length; i++) {
			if (rand == list[i]) {
				return false;
			}
		}
		return true;
	}

	public void selectionSort(List<Integer> list) {
		int min, dummy;
		for (int i = 0; i < list.size() - 1; i++) {
			min = i;
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(j) < list.get(min)) {
					min = j;
				}
			}
			dummy = list.get(i);
			list.set(i, list.get(min));
			list.set(min, dummy);

		}
	}

	public void selectionSort(int[] list) {
		int min = 0, dummy = 0, length = list.length;
		for (int i = 0; i < length - 1; i++) {
			min = i;
			for (int j = i + 1; j < length; j++) {
				if (list[min] > list[j])
					min = j;
			}
			dummy = list[i];
			list[i] = list[min];
			list[min] = dummy;
		}
	}

	public void insertSort(List<Integer> list) {
		int dummy = 0, length = list.size();
		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0 && list.get(j - 1) > list.get(j); j--) {
				dummy = list.get(j - 1);
				list.set(j - 1, list.get(j));
				list.set(j, dummy);
			}
		}
	}

	public void insertSort(int[] list) {
		int dummy = 0, length = list.length;
		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0 && list[j - 1] > list[j]; j--) {
				dummy = list[j - 1];
				list[j - 1] = list[j];
				list[j] = dummy;
			}
		}
	}

	void insertSort2(int[] list) {
		int j = 0, dummy = 0, length = list.length;
		for (int i = 1; i < length; i++) {
			dummy = list[i];
			j = i - 1;
			while ((j >= 0) && (list[j] > dummy)) {
				list[j + 1] = list[j];
				j--;
			}
			list[j + 1] = dummy;
		}
	}

	void bubbleSort(int[] list) {
		int dummy = 0, length = list.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = 0; j < (length - i) - 1; j++) {
				if (list[j] > list[j + 1]) {
					dummy = list[j];
					list[j] = list[j + 1];
					list[j + 1] = dummy;
				}
			}
		}
	}

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
}



























