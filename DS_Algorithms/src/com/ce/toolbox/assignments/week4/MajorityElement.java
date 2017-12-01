package com.ce.toolbox.assignments.week4;

import java.util.*;
import java.io.*;

/**
 * Task.
 * The goal in this code problem is to check whether an input sequence contains a majority element.
 * 
 * Output Format.
 * Output 1 if the sequence contains an element that appears strictly more than n/2 times, and 0 otherwise.
 */
public class MajorityElement {
	private static int getMajorityElement(int[] a, int left, int right) {

		if (a == null || a.length == 0)
			return -1;

		mergeSort(a, left, right);

		int count = 1;
		for (int i = 1; i < a.length; i++) {
			if (a[i] == a[i - 1]) {
				if (++count > a.length / 2)
					return 1;
			} else {
				count = 1;
			}
		}

		return -1;
	}

	private static void mergeSort(int[] a, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSort(a, left, mid);
			mergeSort(a, mid + 1, right);
			merge(a, left, mid, right);
		}

	}

	private static void merge(int[] a, int left, int mid, int right) {

		/**
		 * Allocate extra space to store the left and right array.
		 */
		int leftSize = mid - left + 1;
		int[] leftArr = new int[leftSize];

		int rightSize = right - mid;
		int[] rightArr = new int[rightSize];

		for (int i = 0; i < leftSize; i++) {
			leftArr[i] = a[left + i];
		}

		for (int j = 0; j < rightSize; j++) {
			rightArr[j] = a[j + mid + 1];
		}

		int i = 0;
		int j = 0;
		int k = left;
		while (i < leftSize && j < rightSize) {
			if (leftArr[i] <= rightArr[j]) {
				a[k] = leftArr[i];
				i++;
			} else {
				a[k] = rightArr[j];
				j++;
			}
			k++;
		}

		while (i < leftSize) {
			a[k] = leftArr[i];
			k++;
			i++;
		}

		while (j < rightSize) {
			a[k] = rightArr[j];
			k++;
			j++;
		}

	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		if (getMajorityElement(a, 0, a.length - 1) != -1) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	static class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		FastScanner(InputStream stream) {
			try {
				br = new BufferedReader(new InputStreamReader(stream));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
