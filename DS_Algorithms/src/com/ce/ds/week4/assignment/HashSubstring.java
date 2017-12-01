package com.ce.ds.week4.assignment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Input:
 * Test
 * testTesttesT
 *
 * Output - 4
 * 
 * @author sumitkumar
 *
 */
public class HashSubstring {

	private static FastScanner in;
	private static PrintWriter out;
	private static int p = 1000000007;
	private static int x = 256; // Length of Latin Character Set
	static long[] H;
	static List<Integer> occurrences = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		in = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		getOccurrences(readInput());
		printOccurrences(occurrences);
		out.close();
	}

	private static Data readInput() throws IOException {
		String P = in.next();
		String T = in.next();

		/**
		 * We initialize our answer, big H, as an array of length, length of text minus length of pattern plus one.
		 * Which is the number of substrings of the text with length equal to the length of the pattern.
		 */
		int hSize = T.length() - P.length() + 1;
		H = new long[hSize];

		/**
		 * initialize S by the last substring of the text with a length equal to the length of the pattern.
		 */
		String S = T.substring((T.length() - P.length()), T.length());

		/**
		 * And you compute the hash value for this last substring directly by calling our implementation of polynomial
		 * hash with the substring prime number P and integer x.
		 */
		H[hSize - 1] = hashFunc(S);

		/**
		 * Then we also need to precompute the value of x to the power of length of the pattern and store it in the
		 * variable y. To do that we need initialize it with 1 and then multiply it length of P times by x and take this
		 * module of p.
		 */
		long y = 1;
		for (int i = 1; i <= P.length(); i++) {
			y = (y * x) % p;
		}

		/**
		 * And then the main for loop, the second for loop goes from right to left and computes the hash values for all
		 * the substrings of the text, but for the last one for which we already know the answer. So to compute H[i]
		 * given H[i + 1], we multiply it by x. Then we add T[i] and we subtract y, which is x to the power of length of
		 * P, by T[i + length of the pattern]. And we take the expression module of p.
		 * 
		 * 
		 * Beware of taking negative numbers (mod 𝑝). In many programming languages, (−2)%5 ̸= 3%5. Thus you can
		 * compute the same hash values for two strings, but when you compare them, they appear to be different. To
		 * avoid
		 * this issue, you can use such construct in the code: 𝑥 ← ((𝑎%𝑝) + 𝑝)%𝑝 instead of just 𝑥 ← 𝑎%𝑝.
		 */
		for (int j = hSize - 2; j >= 0; j--) {
			H[j] = ((((x * H[j + 1]) + T.charAt(j) - (y * T.charAt(j + P.length()))) % p) + p) % p;
		}

		return new Data(P, T);
	}

	private static void printOccurrences(List<Integer> ans) throws IOException {
		for (Integer cur : ans) {
			out.print(cur);
			out.print(" ");
		}
	}

	private static long hashFunc(String s) {
		// Initialize hash with zero is very important
		long hash = 0;
		for (int i = s.length() - 1; i >= 0; --i) {
			hash = (hash * x + s.charAt(i)) % p;
		}

		return hash;
	}

	private static List<Integer> getOccurrences(Data input) {
		String p = input.pattern;
		String t = input.text;
		long hashP = hashFunc(p);
		int m = p.length();
		int n = t.length();

		for (int i = 0; i + m <= n; ++i) {
			boolean equal = true;
			if (H[i] == hashP) {
				for (int j = 0; j < m; ++j) {
					if (p.charAt(j) != t.charAt(i + j)) {
						equal = false;
						break;
					}
				}
				if (equal) {
					occurrences.add(i);
				}
			}
		}
		return occurrences;
	}

	static class Data {
		String pattern;
		String text;

		public Data(String pattern, String text) {
			this.pattern = pattern;
			this.text = text;
		}
	}

	static class FastScanner {
		private BufferedReader reader;
		private StringTokenizer tokenizer;

		public FastScanner() {
			reader = new BufferedReader(new InputStreamReader(System.in));
			tokenizer = null;
		}

		public String next() throws IOException {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
}
