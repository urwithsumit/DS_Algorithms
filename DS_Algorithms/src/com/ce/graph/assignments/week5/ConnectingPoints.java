package com.ce.graph.assignments.week5;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Kruskal's Implementation for Shortest Path
 * 
 * @author sumitkumar
 *
 */
public class ConnectingPoints {

	static int[] X;
	static int[] Y;

	private static double minimumDistance(int[] x, int[] y) {
		double result = 0.000000000;
		DisjointSet ds = new DisjointSet(x.length);

		Queue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge e1, Edge e2) {
				return (e1.distance > e2.distance) ? 1 : -1;
			}
		});

		/**
		 * Prepare edges from one House(node) to Every other House(node) and Add it to
		 * the Priority Queue to sort it based on distance from shortest to longest
		 * order
		 */
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y.length; j++) {
				queue.add(new Edge(i, j));
			}
		}

		/**
		 * Make Set.
		 */
		for (int i = 0; i < x.length; i++) {
			ds.makeSet(i);
		}

		/**
		 * Poll Queue and see if the ends of Edge do not have the Same parent i.e do not
		 * end up in a Cycle. If not a cycle than union such edge and add its distance
		 * to the result
		 */
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			int u = ds.find(edge.u);
			int v = ds.find(edge.v);
			if (u != v) {
				ds.union(u, v);
				result += edge.distance;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		X = new int[n];
		Y = new int[n];
		for (int i = 0; i < n; i++) {
			X[i] = scanner.nextInt();
			Y[i] = scanner.nextInt();
		}

		scanner.close();
		System.out.println(minimumDistance(X, Y));
	}

	/**
	 * 
	 * @author sumitkumar
	 *
	 */
	static class Edge {
		int u;
		int v;
		double distance;

		public Edge(int u, int v) {
			this.u = u;
			this.v = v;
			this.distance = Math.sqrt(((X[u] - X[v]) * (X[u] - X[v])) + ((Y[u] - Y[v]) * (Y[u] - Y[v])));
		}
	}

	/**
	 * 
	 * @author sumitkumar
	 *
	 */
	static class DisjointSet {
		int[] array;
		int[] rank;
		int n;

		public DisjointSet(int n) {
			this.n = n;
			rank = new int[n];
			array = new int[n];
		}

		public void makeSet(int i) {
			array[i] = i;
			rank[i] = 1;
		}

		public int find(int i) {
			while (i != array[i]) {
				i = array[i];
			}

			return i;
		}

		public void union(int i, int j) {
			int u = find(i);
			int v = find(j);

			if (u == v)
				return;
			else {
				if (rank[u] > rank[v]) {
					array[v] = u;
				} else {
					array[u] = v;
					if (rank[u] == rank[v]) {
						rank[v] += 1;
					}
				}
			}
		}

	}

}
