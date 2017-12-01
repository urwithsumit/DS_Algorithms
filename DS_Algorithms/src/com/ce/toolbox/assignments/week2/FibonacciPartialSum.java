package com.ce.toolbox.assignments.week2;

import java.util.*;

/**
 * Given two non-negative integers m and n where m<= n
 * Output the last digit of F(m) + F(m+1)+....+F(n)
 * 
 * @author sumitkumar
 * 
 */
public class FibonacciPartialSum {
	private static long getFibonacciPartialSumNaive(long from, long to) {

		long sum = getFibonacciSumNaive(to) - getFibonacciSumNaive(from - 1);
		return (sum < 0) ? (10 + sum) : sum;

	}

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
		long from = scanner.nextLong();
		long to = scanner.nextLong();
		System.out.println(getFibonacciPartialSumNaive(from, to));
	}
}
