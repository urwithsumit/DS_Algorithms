package com.ce.ds.week3.assignment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
	private int[] data;
	private List<Swap> swaps;

	private FastScanner in;
	private PrintWriter out;
	int n;

	public static void main(String[] args) throws IOException {
		new BuildHeap().solve();
	}

	private void readData() throws IOException {
		n = in.nextInt();
		data = new int[n];
		for (int i = 0; i < n; ++i) {
			data[i] = in.nextInt();
		}
	}

	private void writeResponse() {
		out.println(swaps.size());
		for (Swap swap : swaps) {
			out.println(swap.index1 + " " + swap.index2);
		}
	}

	private void generateSwaps() {
		swaps = new ArrayList<Swap>();
		int size = n;
		for (int i = size / 2; i >= 0; i--) {
			minHeap(i);
		}
	}

	private void minHeap(int i) {
		if (i > data.length) {
			return;
		}

		int left = 2 * i + 1;
		int right = 2 * i + 2;

		if (left >= data.length) {
			return;
		}

		int small = left;
		if (right < data.length && data[left] > data[right]) {
			small = right;
		}

		if (data[i] > data[small]) {
			doSwaps(i, small);
			minHeap(small);
		}
	}

	private void doSwaps(int i, int j) {
		swaps.add(new Swap(i, j));
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

	public void solve() throws IOException {
		in = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		readData();
		generateSwaps();
		writeResponse();
		out.close();
	}

	static class Swap {
		int index1;
		int index2;

		public Swap(int index1, int index2) {
			this.index1 = index1;
			this.index2 = index2;
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
