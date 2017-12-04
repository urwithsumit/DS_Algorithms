package com.ce.graph.assignments.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Determine Cycles in a graph
 * 
 * @author sumitkumar
 *
 */
public class Acyclicity {
	private static char[] visited;
	private static boolean flag = true;

	/**
	 * Run a DFS for each Vertex in the Graph that has not been visited yet.
	 * 
	 * @param adj
	 * @return
	 */
	static int acyclicity(ArrayList<Integer>[] adj) {
		for (int i = 0; i < adj.length; i++) {
			if (flag) {
				if (visited[i] != 'v')
					visit(i, adj);
			} else {
				return 1;
			}
		}

		return 0;

	}

	/**
	 * Maintain 3 processing Status : u = unvisited, v = visited, p = processing
	 * 
	 * During Visit operation, if we get a node that has a status of p, than it
	 * means we have a cycle.
	 * 
	 * @param root
	 * @param adj
	 */
	static void visit(int root, ArrayList<Integer>[] adj) {
		if (visited[root] == 'p') {
			flag = false;
			return;
		}

		if (visited[root] == 'u') {
			visited[root] = 'p';

			for (Integer item : adj[root]) {
				visit(item, adj);
			}

			visited[root] = 'v';
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();

		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		visited = new char[n];
		Arrays.fill(visited, 'u');

		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
		}

		System.out.println(acyclicity(adj));

		scanner.close();
	}
}
