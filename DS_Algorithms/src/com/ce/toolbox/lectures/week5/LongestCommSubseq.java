package com.ce.toolbox.lectures.week5;

/**
 * Longest Common Subsequence
 * 
 * @author sumitkumar
 * 
 */
public class LongestCommSubseq {
	static StringBuilder build1 = new StringBuilder();
	static StringBuilder build2 = new StringBuilder();

	static int editDistance(char[] a, char[] b) {

		int[][] sol = new int[a.length + 1][b.length + 1];

		// Base Case
		for (int i = 0; i <= a.length; i++) {
			sol[i][0] = i;
		}

		// Base Case
		for (int i = 0; i <= b.length; i++) {
			sol[0][i] = i;
		}

		for (int i = 1; i <= a.length; i++) {
			for (int j = 1; j <= b.length; j++) {

				int dCost = sol[i][j - 1] + 1; // Deletion Cost, marked by Down Arrow
				int iCost = sol[i - 1][j] + 1; // Insertion Cost, marked by Horizontal Arrow
				int nCost = sol[i - 1][j - 1] + 1; // Mismatch Cost, marked by Diagonal Arrow
				if (a[i - 1] == b[j - 1]) {
					nCost = sol[i - 1][j - 1]; // If a match, reduce 1 as 0 cost for a match. It is also marked by the
												// diagonal arrow.
				}

				sol[i][j] = Math.min(dCost, Math.min(iCost, nCost));

			}
		}

		print2DArray(a, b, sol);

		backTrackToGetString(sol, a, b, a.length - 1, b.length - 1);

		return sol[a.length][b.length];

	}

	private static void backTrackToGetString(int[][] sol, char[] a, char[] b, int i, int j) {

		if (i == 0 && j == 0)
			return;

		if (i > 0 && sol[i][j] == sol[i - 1][j] + 1) {
			backTrackToGetString(sol, a, b, i - 1, j);
			build1.append(a[i]);
			build2.append("-");
		} else if (j > 0 && sol[i][j] == sol[i][j - 1] + 1) {
			backTrackToGetString(sol, a, b, i, j - 1);
			build1.append("-");
			build2.append(b[j]);
		} else {
			backTrackToGetString(sol, a, b, i - 1, j - 1);
			build1.append(a[i]);
			build2.append(b[j]);
		}

	}

	private static void print2DArray(char[] a, char[] b, int[][] sol) {
		for (int i = 0; i <= a.length; i++) {
			for (int j = 0; j <= b.length; j++) {
				System.out.print(sol[i][j] + "  ");
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {

		String a = "editing";
		String b = "distance";
		System.out.println(" \nBest Possible Edit Distance for " + a + " and " + b + " is : " + editDistance(a.toCharArray(), b.toCharArray()));

		System.out.println(build1.toString());
		System.out.println(build2.toString());
	}

}
