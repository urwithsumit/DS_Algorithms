package com.ce.toolbox.assignments.week3;
import java.util.Scanner;

public class Change {
	private static int getChange(int m) {
		if (m < 1)
			return 0;
		int x = 0;
		if (m >= 10) {
			x += m / 10;
			m = m % 10;
		}

		if (m >= 5) {
			x += m / 5;
			m = m % 5;
		}
		
		if(m >= 1) {
    		x += m;
    	}

		return x;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt();
		System.out.println(getChange(m));

	}
}
