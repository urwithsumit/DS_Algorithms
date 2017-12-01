package com.ce.ds.week6.assignment;

import java.util.*;

import java.io.*;

public class is_bst_hard {
	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}

		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class IsBST {
		class Node {
			int key;
			int left;
			int right;

			Node(int key, int left, int right) {
				this.left = left;
				this.right = right;
				this.key = key;
			}
		}

		int nodes;
		Node[] tree;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			nodes = in.nextInt();
			tree = new Node[nodes];
			for (int i = 0; i < nodes; i++) {
				tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
			}
		}

		/**
		 * 1
		 * 1 3
		 * 
		 * 
		 * 
		 * @param root
		 * @param min
		 * @param max
		 * @return
		 */

		// PostOrder Traversal
		private boolean isBST(int root, long min, long max) {

			if (root == -1) {
				return true;
			}

			Node node = tree[root];
			// System.out.println("min < Key < max " + min + " < " + node.key + " < " + max);
			// System.out.println("node.key < min : " + (node.key < min));
			// System.out.println("!(node.key <= max) : " + !(node.key <= max));

			if ((node.key < min || node.key >= max)) {
				return false;
			}

			return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
		}

		boolean isBinarySearchTree() {
			if (tree.length > 1)
				return isBST(0, Long.MIN_VALUE, Long.MAX_VALUE);

			return true;
		}
	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new is_bst_hard().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

	public void run() throws IOException {
		IsBST tree = new IsBST();
		tree.read();
		if (tree.isBinarySearchTree()) {
			System.out.println("CORRECT");
		} else {
			System.out.println("INCORRECT");
		}
	}
}
