package com.ce.ds.week1.assignment;

import java.util.*;
import java.io.*;

public class tree_height {
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

	class Node {
		int value;
		List<Node> children;

		Node(int val) {
			this.value = val;
			children = new LinkedList<Node>();
		}

		void addChild(Node child) {
			children.add(child);
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", children=" + children + "]";
		}
	}

	public class TreeHeight {
		int n;
		Node[] nodes;
		Node root;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			nodes = new Node[n];
			for (int i = 0; i < n; i++) {
				nodes[i] = new Node(in.nextInt());
			}

			// index is the child of root give by array index value
			for (int i = 0; i < n; i++) {
				if (nodes[i].value == -1) {
					root = nodes[i];
					root.value = i;
				} else {
					nodes[nodes[i].value].addChild(new Node(i));
				}
			}

		}

		int height(int index) {
			if (nodes[index] == null) {
				return 0;
			}

			else if (nodes[index].children.size() == 0) {
				return 1;
			} else {
				int ht = 0;
				for (Node n : nodes[index].children) {
					int cht = height(n.value);
					if (cht > ht) {
						ht = cht;
					}
				}
				return ht + 1;
			}
		}
	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_height().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}

	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.height(tree.root.value));
	}
}
