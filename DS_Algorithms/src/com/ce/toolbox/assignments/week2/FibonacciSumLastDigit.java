package com.ce.toolbox.assignments.week2;

import java.util.Scanner;

/**
 * Problem Description
 * Task. Given an integer 𝑛, find the last digit of the sum 𝐹0 +𝐹1 +···+𝐹𝑛. Input Format. The input consists of a
 * single integer 𝑛.
 * Constraints. 0 ≤ 𝑛 ≤ 1014.
 * Output Format. Output the last digit of 𝐹0 + 𝐹1 + · · · + 𝐹𝑛.
 * 
 * @author sumitkumar
 * 
 */
public class FibonacciSumLastDigit {
	private static long getFibonacciSumNaive(long n) {
		if (n <= 2)
			return n;

		long previous = 0;
		long current = 1;
		long sum = 0;
		long next = previous + current;

		long f = n % pisanoArr(n, 10);

		for (long i = 0; i < f; i++) {
			next = previous + current;
			previous = current;
			current = next;
			sum += previous;
		}

		return sum % 10;
	}

	public static long pisanoArr(long n, long m) {

		long i = 0;
		long a = 0;
		long b = 1;
		long c = a + b;

		while (true) {
			c = (a + b) % m;
			a = b;
			b = c;
			i++;
			if (a == 0 && b == 1) {
				break;
			}
		}

		return i;

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long n = scanner.nextLong();
		long s = getFibonacciSumNaive(n);
		System.out.println(s);

	}
}
