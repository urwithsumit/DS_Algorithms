package com.ce.graph.assignments.week5;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Task. Given n points on a plane and an integer k, compute the largest
 * possible value of d such that the given points can be partitioned into k
 * non-empty subsets in such a way that the distance between any two points from
 * different subsets is at least d.
 * 
 * 
 * 
 * 
 * 8 3 1 1 2 4 6 9 8 9 9 8 9 3 11 4 12 4
 * 
 * @author sumitkumar
 *
 */
public class Clustering {
	static int[] X;
	static int[] Y;

	private static double clustering(int[] x, int[] y, int k_Cluster) {
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
		int solutionEdgeCounter = 0;
		int vertices = x.length;
		Edge edge = null;
		while (!queue.isEmpty()) {
			edge = queue.poll();
			int u = ds.find(edge.u);
			int v = ds.find(edge.v);
			if (u != v) {
				// System.out.println("vertices = " + solutionEdgeCounter + " => |V| -
				// solutionEdgeCounter = "
				// + (vertices - solutionEdgeCounter));
				if ((vertices - solutionEdgeCounter++) == k_Cluster) {
					// System.out.println("BREAK");
					break;
				}
				ds.union(u, v);
				result += edge.distance;
			}
		}

		return edge != null ? edge.distance : -1;
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

		int k = scanner.nextInt();
		scanner.close();

		System.out.println(clustering(X, Y, k));
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
