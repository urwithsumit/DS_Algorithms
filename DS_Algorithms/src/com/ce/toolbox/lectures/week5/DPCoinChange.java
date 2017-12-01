package com.ce.toolbox.lectures.week5;

import java.util.Arrays;
//COINS = 1, 4, 8
// MONEY = 24
//Ans - 3 as 8 *3 = 24

public class DPCoinChange {
	private static int INFINITY = 99999;

	private static int minNumCoins(int money, int[] coins) {

		int[] minNumCoins = new int[money + 1];
		Arrays.fill(minNumCoins, 99999);

		// there is only 1 way when money is 0
		minNumCoins[0] = 0;

		// Calculate coin count for all money from 1 to money
		for (int m = 1; m <= money; m++) {
			minNumCoins[m] = INFINITY;
			for (int i = 0; i < coins.length; i++) {
				if (m >= coins[i]) { // avoid negative index access
					// Index m is the actual coin needed to be exchanged with smaller denomination of
					// coin
					// System.out.println(coins[i]);
					int numCoins = minNumCoins[m - coins[i]] + 1;
					// System.out.println("For Money = " + m + ", numCoins =" + numCoins);
					if (numCoins < minNumCoins[m])
						minNumCoins[m] = numCoins;
				}
			}
		}

		System.out.println(Arrays.toString(minNumCoins));
		return minNumCoins[money];
	}

	public static void main(String[] args) {
		int[] a = { 8, 20 };

		int n = minNumCoins(24, a);
		if (n == INFINITY) {
			System.out.println("Cannot Change!!");
		} else {
			System.out.println(n);
		}

	}

}
