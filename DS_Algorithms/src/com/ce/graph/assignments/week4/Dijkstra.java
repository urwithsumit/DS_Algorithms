package com.ce.graph.assignments.week4;

import java.util.*;

/**
 * Implement Dijkstra Algorithm
 * 
 * 5 9 1 2 4 1 3 2 2 3 2 3 2 1 2 4 2 3 5 4 5 4 1 2 5 3 3 4 4 1 5
 * 
 * 3 3 1 2 7 1 3 5 2 3 2 3 2
 * 
 * 
 * @author sumitkumar
 *
 */
public class Dijkstra {

	static int[] dist;
	static int[] prev;
	static Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
		@Override
		public int compare(Integer a, Integer b) {
			return (a > b) ? 1 : -1;
		}
	});

	private static int distance(ArrayList<Integer>[] adj, int[][] cost, int s, int t) {

		dist[s] = 0;
		queue.add(s);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (Integer v : adj[u]) {
				if (relaxVertex(u, v, cost[u][v]))
					queue.add(v);
			}
		}

		return dist[t] != Integer.MAX_VALUE ? dist[t] : -1;
	}

	/**
	 * Relax Vertex to have a value which is less than its current value.
	 * 
	 * @param u
	 * @param v
	 * @param w
	 */
	static boolean relaxVertex(int u, int v, int w) {
		if (dist[v] > (dist[u] + w)) {
			dist[v] = dist[u] + w;
			prev[v] = u;
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		int[][] cost = new int[n][n];

		dist = new int[n];
		prev = new int[n];

		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(prev, -1);

		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			int x, y, w;
			x = scanner.nextInt();
			y = scanner.nextInt();
			w = scanner.nextInt();
			adj[x - 1].add(y - 1);
			cost[x - 1][y - 1] = w;
		}
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;

		scanner.close();

		System.out.println(distance(adj, cost, x, y));
	}
}
