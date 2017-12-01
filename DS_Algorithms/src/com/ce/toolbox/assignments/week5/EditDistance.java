package com.ce.toolbox.assignments.week5;
import java.util.Scanner;

class EditDistance {
	public static int editDistance(String s, String t) {
		char[] a = s.toCharArray();
		char[] b = t.toCharArray();
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

				// Calculate all Costs, considering
				int dCost = sol[i][j - 1] + 1; // Deletion Cost, marked by Down Arrow
				int iCost = sol[i - 1][j] + 1; // Insertion Cost, marked by Horizontal Arrow
				int nCost = sol[i - 1][j - 1] + 1; // Mismatch Cost, marked by Diagonal Arrow
				if (a[i - 1] == b[j - 1]) {
					nCost = sol[i - 1][j - 1]; // If a match, reduce 1 as 0 cost for a match. It is also marked by the
												// diagonal arrow.
				}

				// Select the minimum Cost among the values calculated above.
				sol[i][j] = Math.min(dCost, Math.min(iCost, nCost));
			}
		}

		return sol[a.length][b.length];
	}

	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		String s = scan.next();
		String t = scan.next();

		System.out.println(editDistance(s, t));
	}

}
