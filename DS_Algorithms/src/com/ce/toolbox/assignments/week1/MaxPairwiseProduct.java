package com.ce.toolbox.assignments.week1;
import java.util.Scanner;

/**
 * Problem
 * 
 * Given a sequence of non-negative integers a0,…,an−1, find the maximum
 * pairwise product, that is, the largest integer that can be obtained by
 * multiplying two different elements from the sequence (or, more formally,
 * max0≤i≠j≤n−1aiaj). Different elements here mean ai and aj with i≠j (it can be
 * the case that ai=aj).
 * 
 * input 
 * > 3 
 * > 5 2 4
 * 
 * Output 20
 * 
 * @author sumitkumar
 * 
 */
public class MaxPairwiseProduct {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int limit = s.nextInt();

		if (limit > 1) {
			Long max1 = -1L;
			Long max2 = -1L;
			Long temp = -1L;
			for (int i = 0; i < limit; i++) {
				temp = Long.valueOf(s.nextLong());
				if (temp > max1) {
					max2 = max1;
					max1 = temp;
				} else if (temp > max2) {
					max2 = temp;
				}
			}

			System.out.println(max1 * max2);
		}

	}

}
