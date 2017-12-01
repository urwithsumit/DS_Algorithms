package com.ce.toolbox.assignments.week4;

import java.util.*;

public class Inversions {
	private static long size = 0;

	private static void mergeSort(int[] a, int left, int right) {
		if (left < right) {
			int pivot = (left + right) / 2;
			mergeSort(a, left, pivot);
			mergeSort(a, pivot + 1, right);
			merge(a, left, pivot, right);
		}
	}

	private static void merge(int[] a, int left, int pivot, int right) {

		int leftSize = pivot - left + 1;
		int[] leftArr = new int[leftSize];

		int rightSize = right - pivot;
		int[] rightArr = new int[rightSize];

		for (int i = 0; i < leftSize; i++) {
			leftArr[i] = a[left + i];
		}

		for (int k = 0; k < rightSize; k++) {
			rightArr[k] = a[pivot + k + 1];
		}

		int i = 0;
		int j = 0;
		int k = left;

		while (i < leftSize && j < rightSize) {
			if (leftArr[i] <= rightArr[j]) {
				a[k++] = leftArr[i++];
			} else {
				a[k++] = rightArr[j++];
				/**
				 * For inversion, i < j and a[i] > a[j].
				 * Since the left and right Arrays are sorted, so total inversion will be the size of Left Array - the
				 * current index value.
				 */
				size += leftSize - i;
			}
		}

		while (i < leftSize) {
			a[k++] = leftArr[i++];
		}

		while (j < rightSize) {
			a[k++] = rightArr[j++];
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		mergeSort(a, 0, n - 1);
		System.out.println(size);

		Map<String, String> map = new LinkedHashMap<>();
		map.put("1", "A");
		System.out.println(map.toString());
		map.put("2", "B");
		System.out.println(map.toString());
		map.put("3", "C");
		System.out.println(map.toString());
		map.put("4", "D");
		System.out.println(map.toString());
		map.remove("2");
		System.out.println(map.toString());
		map.put("2", "B");
		System.out.println(map.toString());
	}
}
