package com.ce.toolbox.assignments.week3;
import java.util.Scanner;

public class FractionalKnapsack {
	private static double getOptimalValue(int capacity, int[] values, int[] weights) {

		double value = 0.0000;

		double[] fract = new double[values.length];
		for (int i = 0; i < values.length; i++) {
			fract[i] = (double) values[i] / weights[i];
		}

		while (capacity > 0) {

			double maxFract = 0.0000;
			int indx = 0;
			// Find the next biggest fraction value
			for (int i = 0; i < values.length; i++) {
				if (fract[i] > maxFract) {
					maxFract = fract[i];
					indx = i;
				}
			}

			int avlUnits = weights[indx];
			if (avlUnits <= capacity) {
				value += avlUnits * maxFract;
				fract[indx] = 0.0000; // So we do not consider this in next iteration
				capacity -= avlUnits;
			} else {
				// the knapsack can be filled for remaining capacity.
				value += capacity * maxFract;
				capacity = 0;
			}
		}

		return Math.round(value * 10000) / 10000.0d;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int capacity = scanner.nextInt();
		int[] values = new int[n];
		int[] weights = new int[n];
		for (int i = 0; i < n; i++) {
			values[i] = scanner.nextInt();
			weights[i] = scanner.nextInt();
		}
		System.out.println(getOptimalValue(capacity, values, weights));
	}
}
