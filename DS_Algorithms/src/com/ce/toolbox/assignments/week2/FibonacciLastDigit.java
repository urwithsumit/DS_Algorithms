package com.ce.toolbox.assignments.week2;
import java.util.Scanner;

/**
 * Calculate the Last digit of fib number.
 * 
 * @author sumitkumar
 * 
 */
public class FibonacciLastDigit {
	private static int getFibonacciLastDigitNaive(int n) {
		if (n <= 1)
			return n;

		int previous = 0;
		int current = 1;
		int tmp_previous = 0;

		for (int i = 0; i < n - 1; ++i) {
			/**
			 * Not store the calculated fib number.
			 * Instead the operation will be done at the unit number only
			 */
			tmp_previous = previous % 10;
			previous = current % 10;
			current = tmp_previous + current;
		}

		return current % 10;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int c = getFibonacciLastDigitNaive(n);
		System.out.println(c);
		scanner.close();
	}
}
