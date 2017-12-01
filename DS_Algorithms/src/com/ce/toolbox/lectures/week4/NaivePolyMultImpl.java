package com.ce.toolbox.lectures.week4;

import java.util.Arrays;

public class NaivePolyMultImpl {

	public static void main(String[] args) {

		int[] A = { 3, 2, 5 }; // 3*x^2 + 2*x + 5
		int[] B = { 5, 1, 2 }; // 5*x^2 + 1*x + 2
		int n = A.length; // No. Of ELements
		int[] Product = new int[2 * n - 1]; // The result of 2 polynomial of size n will result in a polynomial of size
											// 2n-1, hence keeping an array good enough to hold the result.

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Product[i + j] = Product[i + j] + A[i] * B[j];
			}

			System.out.println("Intermediate Result => " + Arrays.toString(Product));
		}

		System.out.println("Final Result => " + Arrays.toString(Product));
	}
}
