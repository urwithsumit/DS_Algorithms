package com.ce.toolbox.lectures.week5;

import java.util.Arrays;

/**
 * Knapsack with Repeats allowed using DP
 * 
 * Approach is same as DP Coin Change, instead of minimizing we are going to maximize the solution
 * 
 * @author sumitkumar
 * 
 */
public class KnapsackWRepeat {

	private static int Knapsack(int W, int[] itemWt, int[] itemVal) {
		int[] result = new int[W + 1];
		for (int i = 1; i <= W; i++) {
			result[i] = 0;
			for (int j = 0; j < itemWt.length; j++) {
				if (itemWt[j] <= i) {
					int optWt = result[i - itemWt[j]] + itemVal[j];
					if (optWt > result[i]) {
						result[i] = optWt;
					}
				}
			}
		}

		System.out.println(Arrays.toString(result));
		return result[W];
	}

	public static void main(String[] args) {

		int[] itemWt = { 6, 3, 4, 2 };
		int[] itemVal = { 30, 14, 16, 9 };

		System.out.println(Knapsack(10, itemWt, itemVal));

	}
}
