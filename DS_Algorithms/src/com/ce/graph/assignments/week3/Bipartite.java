
package com.ce.graph.assignments.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * A graph is bipartite if its vertices can be colored with two colors (say,
 * black and white) such that the end points of each edge have different colors.
 * 
 * @author sumitkumar
 *
 */
public class Bipartite {
	static char[] visit;
	static Queue<Integer> queue;
	static char BLACK = 'b';
	static char WHITE = 'w';

	private static int bipartite(ArrayList<Integer>[] adj) {

		if (visit[0] == 'u') {
			queue.add(0);
			visit[0] = BLACK; // Initialize with BLACK
			while (!queue.isEmpty()) {
				int u = queue.poll();
				List<Integer> neighbour = adj[u];
				for (Integer v : neighbour) {
					if (visit[v] == visit[u]) { // If both u and v have Same Color i.e. not bipartite
						return 0;
					}

					if (visit[v] == 'u') {
						queue.add(v);
						visit[v] = (visit[u] == BLACK) ? WHITE : BLACK; // Assign opposite color than u
					}
				}
			} // End of while
		} // End of if

		return 1;
	}

	static void bfs(ArrayList<Integer>[] adj) {
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		visit = new char[n];
		queue = new LinkedList<>();
		Arrays.fill(visit, 'u');
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
			adj[y - 1].add(x - 1);
		}

		scanner.close();
		System.out.println(bipartite(adj));
	}
}
