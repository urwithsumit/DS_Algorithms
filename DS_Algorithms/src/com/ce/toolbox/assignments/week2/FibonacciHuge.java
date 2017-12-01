package com.ce.toolbox.assignments.week2;

import java.util.*;

/**
 * Task. Given two integers 𝑛 and 𝑚, output 𝐹𝑛 mod 𝑚 (that is, the remainder of 𝐹𝑛 when divided by 𝑚).
 * Input Format. The input consists of two integers 𝑛 and 𝑚 given on the same line (separated by a space).
 * Constraints 1≤𝑛≤1018,2≤𝑚≤105.
 * Output Format. Output 𝐹𝑛 mod 𝑚.
 * 
 * @author sumitkumar
 * 
 */
public class FibonacciHuge {

	private static long getFibonacciHugeNaive(long n, long m) {
		if (n <= 1)
			return n;

		long previous = 0;
		long current = 1;
		long tmp_previous = 0;

		for (long i = 0; i < n - 1; ++i) {
			tmp_previous = previous;
			previous = current;
			current = (tmp_previous + current) % m;
		}

		return current;
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
		long m = scanner.nextLong();
		long p = pisanoArr(n, m);
		System.out.println(getFibonacciHugeNaive(n % p, m));
		scanner.close();
	}
}
