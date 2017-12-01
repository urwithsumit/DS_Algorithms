package com.ce.ds.misc;
import java.util.Stack;

/**
 * Given a string (understood to be a sentence), reverse the order of the words. "Hello world" becomes "world Hello"
 * 
 * @author sumitkumar
 * 
 */
public class ReverseWords {

	static String reverseWord(String s) {
		final Stack<Character> stack = new Stack<>();
		final StringBuilder build = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) != ' ') {
				stack.push(s.charAt(i));
			} else {
				while (!stack.isEmpty()) {
					build.append(stack.pop());
				}

				build.append(' ');
			}
		}

		while (!stack.isEmpty()) {
			build.append(stack.pop());
		}

		return build.toString().trim();
	}

	public static void main(String[] args) {

		String s = "Hello World";

		assertEquals(reverseWord(s), "World Hello");
	}

	private static boolean assertEquals(String reverseWord, String string) {
		if (reverseWord == null) {
			throw new AssertionError("Null");
		}

		if (reverseWord.equals(string)) {
			System.out.println("Pass!!");
			return true;
		} else {
			throw new AssertionError("Fail to Assert: Expected: " + string + ". Actual: " + reverseWord);
		}
	}

}
