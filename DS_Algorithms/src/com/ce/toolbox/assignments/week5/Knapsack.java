package com.ce.toolbox.assignments.week5;
import java.util.Scanner;

public class Knapsack {
	static int optimalWeight(int W, int[] wt) {
		int wtCol = wt.length + 1;
		int knapRow = W + 1;

		int[][] K = new int[knapRow][wtCol];

		for (int j = 1; j < wtCol; j++) {
			for (int w = 1; w < knapRow; w++) {
				int k = j - 1;
				int wj = wt[k];
				if (wj > w) {
					K[w][j] = K[w][j - 1];
				} else {
					K[w][j] = Math.max(K[w][k], K[w - wj][k] + wt[k]);
				}
			}
		}

		//printArr(K, knapRow, wtCol);

		return K[W][wt.length];
	}

	public static void printArr(int[][] a, int row, int col) {
		for (int j = 0; j < row; j++) {
			for (int w = 0; w < col; w++) {
				System.out.print(a[j][w] + " ");
			}
			System.out.println(" ");
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int W, n;
		W = scanner.nextInt();
		n = scanner.nextInt();
		int[] w = new int[n];
		for (int i = 0; i < n; i++) {
			w[i] = scanner.nextInt();
		}
		System.out.println(optimalWeight(W, w));
	}
}
