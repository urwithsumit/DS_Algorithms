package com.ce.ds.week6.assignment;
import java.util.*;
import java.io.*;

/***
 * 
 * Order Traversal of a Tree.
 * 
 * key[] -> Contains the value of a Node.
 * left[] -> Contains the left Index for Node at ith index
 * right[] -> Contains the right Index for Node at ith Index.
 * 
 * @author sumitkumar
 *
 */
public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) {
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		// L, R, N
		void inOrder(int iRoot, List<Integer> result) {

			if (iRoot >= 0) {
				int iLeft = left[iRoot]; // 1, 3
				int iRight = right[iRoot]; // 2, 4

				// Explore Left Child, till hit the leaf node
				if (iLeft != -1) {
					inOrder(iLeft, result);
				}

				// Print the Leaf Node.
				result.add(key[iRoot]); // 4

				// Explore Right Child, till hit the leaf node
				if (iRight != -1) {
					inOrder(iRight, result);
				}
			}
		}

		// R, L, N
		void preOrder(int iRoot, List<Integer> result) {

			if (iRoot >= 0) {
				int iLeft = left[iRoot]; // 1, 3
				int iRight = right[iRoot]; // 2, 4

				// Print the Leaf Node.
				result.add(key[iRoot]); // 4

				// Explore Left Child, till hit the leaf node
				if (iLeft != -1) {
					preOrder(iLeft, result);
				}

				// Explore Right Child, till hit the leaf node
				if (iRight != -1) {
					preOrder(iRight, result);
				}
			}
		}

		// L, N, R
		void postOrder(int iRoot, List<Integer> result) {

			if (iRoot >= 0) {
				int iLeft = left[iRoot]; // 1, 3
				int iRight = right[iRoot]; // 2, 4

				// Explore Left Child, till hit the leaf node
				if (iLeft != -1) {
					postOrder(iLeft, result);
				}

				// Explore Right Child, till hit the leaf node
				if (iRight != -1) {
					postOrder(iRight, result);
				}

				// Print the Leaf Node.
				result.add(key[iRoot]); // 4
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			inOrder(0, result);
			return result;
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			preOrder(0, result);
			return result;
		}

		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
			postOrder(0, result);
			return result;
		}
	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_orders().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
