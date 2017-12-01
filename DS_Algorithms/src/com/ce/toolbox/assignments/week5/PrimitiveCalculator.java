package com.ce.toolbox.assignments.week5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * You are given a primitive calculator that can perform the following three operations with the current number
 * x : multiply x by 2, multiply x by 3, or add 1 to x. Your goal is given a positive integer n , find the
 * minimum number of operations needed to obtain the number n starting from the number 1
 * 
 * @author sumitkumar
 * 
 */

public class PrimitiveCalculator {

	/**
	 * @param n
	 * @return
	 */
	/**
	 * @param n
	 * @return
	 */
	private static List<Integer> optimal_sequence(int num) {
		List<Integer> sequence = null;
		if (num == 1) {
			sequence = new ArrayList<Integer>();
			sequence.add(1);
			return sequence;
		}

		int[] resultsRefArray = new int[num + 1];

		// Find count for all number in the range of 1 to num
		for (int k = 1; k <= num; k++) {
			int n = k;
			sequence = new ArrayList<Integer>();

			while (n >= 1) {
				sequence.add(n);
				if (n % 3 == 0 && n % 2 == 0) {
					n /= 3;
				} else if (n % 3 == 0) {
					n /= 3;
				} else if (n % 2 == 0 && (n - 1) % 3 == 0) {
					// Check in how many step the subsequent resulting number was solved previously
					// Dynamic Programming Comes into Action here!!
					if (resultsRefArray[n / 2] > resultsRefArray[(n - 1) / 3]) {
						n -= 1;
					} else {
						n /= 2;
					}
				} else if (n % 2 == 0) {
					n /= 2;
				} else {
					n -= 1;
				}
			}

			// Store the result of each num in range of 1 to num in array for lookup.
			resultsRefArray[k] = sequence.size() - 1;
		}

		Collections.sort(sequence);
		return sequence;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();

		List<Integer> sequence = optimal_sequence(num);
		System.out.println(sequence.size() - 1);
		for (Integer x : sequence) {
			System.out.print(x + " ");
		}
	}
}
