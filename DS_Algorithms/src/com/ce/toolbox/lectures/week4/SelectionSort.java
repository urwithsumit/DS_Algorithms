package com.ce.toolbox.lectures.week4;

import java.util.Arrays;

/**
 * Selection Sort in O(n2)
 * 
 * @author sumitkumar
 * 
 */
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = { 1, 4, 2, 6, 33, 22, 11, 9 };

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int tmp = arr[j];
					arr[j] = arr[i];
					arr[i] = tmp;
				}
			}
		}

		System.out.println(Arrays.toString(arr));
	}

}
