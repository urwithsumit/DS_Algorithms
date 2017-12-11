package com.ce.graph.assignments.week3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
	static int[] dist;
	static int[] prev;
	static Queue<Integer> queue;

	static boolean isVisited(int node) throws IllegalArgumentException {

		if (node > dist.length) {
			throw new IllegalArgumentException("Not a valid node.");
		}

		return dist[node] != Integer.MAX_VALUE;
	}

	/**
	 * Get the shortest distance between 2 Node. Run BFS with Source index. Check if
	 * the distance of destination is not INFITIY than there is a path between
	 * Source and Destination
	 * 
	 * @param adj
	 * @param s
	 * @param t
	 * @return
	 */
	private static int distance(ArrayList<Integer>[] adj, int s, int t) {
		bfs(s, adj);
		return (dist[t] != Integer.MAX_VALUE) ? dist[t] : -1;
	}

	/**
	 * Construct the Shortest Path between 2 nodes
	 * 
	 * @param s
	 * @param u
	 * @return
	 */
	private static String reconstructPath(int s, int u) {
		StringBuilder revPath = new StringBuilder();

		while (u != s) {
			revPath.append(u + 1).append(" ");
			u = prev[u];

			if (u == s) {
				revPath.append(u + 1).append(" ");
			}
		}

		return revPath.reverse().toString();
	}

	static void bfs(int node, ArrayList<Integer>[] adj) {

		if (!isVisited(node)) {
			queue.add(node);
			dist[node] = 0;
			prev[node] = node;

			while (!queue.isEmpty()) {
				int u = queue.poll();
				List<Integer> neighbor = adj[u];
				for (Integer v : neighbor) {
					if (!isVisited(v)) {
						queue.add(v);
						dist[v] = dist[u] + 1;
						prev[v] = u;
					}
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		dist = new int[n];
		prev = new int[n];
		queue = new LinkedList<>();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
			dist[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
			adj[y - 1].add(x - 1);
		}
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		scanner.close();
		System.out.println(distance(adj, x, y));
		System.out.println("Shortest Path Construction: " + (x + 1) + " & " + (y + 1) + " = " + reconstructPath(x, y));
	}
}
