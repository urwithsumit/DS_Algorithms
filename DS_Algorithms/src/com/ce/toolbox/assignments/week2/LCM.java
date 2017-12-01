package com.ce.toolbox.assignments.week2;
import java.util.*;

public class LCM {
	private static long lcm_naive(long a, long b) {
		return (b * a) / gcd(a, b);
	}

	public static long gcd(long a, long b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();

		System.out.println(lcm_naive(a, b));
		scanner.close();
	}
}
