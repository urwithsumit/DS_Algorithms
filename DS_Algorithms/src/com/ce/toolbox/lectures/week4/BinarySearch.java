package com.ce.toolbox.lectures.week4;

import java.util.Arrays;

/**
 * Binary Search Example using Iterative and Recursive Approaches
 * 
 * @author sumitkumar
 * 
 */
public class BinarySearch {

	public static void main(String[] args) {
		int[] arr = { 1, 45, 2, 78, 56, 99, 200, 11, 452, 66, 2, 33, 4 };
		Arrays.sort(arr);
		System.out.println("Input : " + Arrays.toString(arr));
		System.out.println(searchIterative(0, arr));
		System.out.println(searchIterative(-1, arr));
		System.out.println(searchIterative(22, arr));
		System.out.println(searchIterative(453, arr));
		System.out.println(searchIterative(33, arr));

		System.out.println("Recusrve Search Answer::::");
		System.out.println(searchRecursive(33, 0, arr.length - 1, arr));

	}

	/**
	 * 
	 * @param key
	 * @param arr
	 * @return
	 */
	static int searchIterative(int key, int[] arr) {

		if (arr.length == 0)
			return -1;

		int low = 0;
		int high = arr.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] == key) {
				return mid;
			} else if (arr[mid] > key) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return -1;
	}

	/**
	 * 
	 * @param key
	 * @param low
	 * @param high
	 * @param arr
	 * @return
	 */
	static int searchRecursive(int key, int low, int high, int[] arr) {

		if (arr.length == 0)
			return -1;

		int mid = (low + high) / 2;

		if (arr[mid] == key) {
			return mid;
		} else if (arr[mid] > key) {
			return searchRecursive(key, low, mid - 1, arr);
		} else {
			return searchRecursive(key, mid + 1, high, arr);
		}
	}

}
