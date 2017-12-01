package com.ce.toolbox.assignments.week2;
import java.util.Scanner;

public class GCD {
	private static int gcd_naive(int a, int b) {
		if (b == 0)
			return a;

		return gcd_naive(b, a % b);
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		System.out.println(gcd_naive(a, b));
		scanner.close();
	}
}
