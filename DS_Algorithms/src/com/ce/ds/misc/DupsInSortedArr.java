package com.ce.ds.misc;
import java.util.Arrays;
import java.util.Stack;

public class DupsInSortedArr {

	static int[] removeDups(int[] a) {
		if (a == null || a.length == 1) {
			return a;
		}

		// Not using Set to preserve ordering
		Stack<Integer> stack = new Stack<>();
		stack.push(a[a.length - 1]);
		for (int i = a.length - 2; i >= 0; i--) {
			if (!(stack.peek() == a[i])) {
				stack.push(a[i]);
			}
		}

		Arrays.fill(a, -1);

		for (int i = 0; i < a.length; i++) {
			if (!stack.isEmpty()) {
				a[i] = stack.pop();
			} else {
				break;
			}
		}

		return a;
	}

	public static void main(String[] args) {
		int[] a = { 2, 2, 3, 3, 4, 4 };
		int[] expected = { 2, 3, 4, -1, -1, -1 };

		assertEqual(Arrays.toString(removeDups(a)), Arrays.toString(expected));
	}

	public static void assertEqual(String actual, String expected) {

		if (actual == null) {
			throw new AssertionError("Null ");
		}

		if (actual.equals(expected)) {
			System.out.println("Pass with Expected: " + expected);
		} else {
			throw new AssertionError("Test Case Failure. Expected: " + expected + ". Actual: " + actual);
		}

	}

}
