package com.ce.ds.week1.assignment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
	Bracket(char type, int position) {
		this.type = type;
		this.position = position;
	}

	boolean Match(char c) {
		if (this.type == '[' && c == ']')
			return true;
		if (this.type == '{' && c == '}')
			return true;
		if (this.type == '(' && c == ')')
			return true;
		return false;
	}

	char type;
	int position;

	@Override
	public String toString() {
		return " [ " + type + ":" + position + "]";
	}

}

class check_brackets {
	public static void main(String[] args) throws IOException {
		InputStreamReader input_stream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input_stream);
		String text = reader.readLine();
		boolean clear = false;
		Stack<Bracket> stack = new Stack<Bracket>();
		for (int position = 0; position < text.length(); ++position) {
			char next = text.charAt(position);

			if (next == '(' || next == '[' || next == '{') {
				stack.push(new Bracket(next, position + 1));
			}

			if (next == ')' || next == ']' || next == '}') {
				if (!stack.isEmpty() && stack.peek().Match(next)) {
					stack.pop();
				} else {
					// if a close bracket is not present than break the loop and print index.
					// Clear the stack as we have found the break point
					System.out.println(position + 1);
					stack.clear();
					clear = true;
					break;
				}
			}
		}

		// This will find any open bracket not matched
		if (!stack.isEmpty()) {
			System.out.println(stack.peek().position);
		} else {
			if (!clear)
				System.out.println("Success");
		}
	}
}
