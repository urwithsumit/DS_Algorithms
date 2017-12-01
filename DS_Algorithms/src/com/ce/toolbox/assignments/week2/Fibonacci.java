package com.ce.toolbox.assignments.week2;

import java.util.Scanner;

/**
 * Programming Toolbox
 * Week 1 Assignment - Problem 1
 * 
 * Task. Given an integer 𝑛, nd the 𝑛th Fibonacci number 𝐹𝑛.
 * Input Format. The input consists of a single integer 𝑛.
 * Constraints. 0 ≤ 𝑛 ≤ 45.
 * Output Format. Output 𝐹𝑛.
 * 
 * @author sumitkumar
 * 
 */
public class Fibonacci {
	private static long calc_fib(int n) {
		// Boundary Conditions,
		if (n <= 1)
			return n;

		// Fib Starts at 0, so in order to compute fib
		// for digit 10 we want to hold an extra space in array i.e n+1
		Long[] result = new Long[n + 1];
		result[0] = 0L;
		result[1] = 1L;

		// We want to run it inclusive of n
		for (int i = 2; i <= n; i++) {
			result[i] = result[i - 1] + result[i - 2];
		}

		return result[n];
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		System.out.println(calc_fib(n));
	}
}
