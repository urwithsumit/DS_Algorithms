package com.ce.toolbox.assignments.week4;
import java.io.*;
import java.util.*;

/**
 * 
 * To force the given implementation of the quick sort algorithm to efficiently process sequences with
 * few unique elements, your goal is replace a 2-way partition with a 3-way partition. That is, your new
 * partition procedure should partition the array into three parts:
 * < x part, = x part, and > x part.
 * 
 * @author sumitkumar
 * 
 */
public class Sorting {
	private static Random random = new Random();

	private static int[] partition3(int[] a, int l, int r) {

		int pivot = a[l];
		int i = l;
		while (i <= r) { // Compare the i pointer which would increase at each check with the r which is upper limit.
			// Since i is changed in each check, hence keep a condition of i<=r in each if statement to avoid AIOOB
			// Exception
			if (i <= r && a[i] < pivot) {
				swap(a, l++, i++);
			}

			if (i <= r && a[i] > pivot) {
				swap(a, r--, i);
			}

			if (i <= r && a[i] == pivot) {
				i++;
			}
		}

		int[] m = { l, i };
		return m;

	}

	static void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static int partition2(int[] a, int l, int r) {
		int x = a[l];
		int j = l;
		for (int i = l + 1; i <= r; i++) {
			if (a[i] <= x) {
				j++;
				swap(a, i, j);
			}
		}
		swap(a, l, j);
		return j;
	}

	private static void randomizedQuickSort(int[] a, int l, int r) {
		if (l >= r) {
			return;
		}

		int k = random.nextInt(r - l + 1) + l;
		swap(a, k, l);

		int[] m = partition3(a, l, r);
		randomizedQuickSort(a, l, m[0] - 1);
		randomizedQuickSort(a, m[1], r);
	}

	public static void main(String[] args) {
		FastScanner scanner = new FastScanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		randomizedQuickSort(a, 0, n - 1);
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
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
