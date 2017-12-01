package com.ce.graph.assignments.week1;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ConnectedComponents {
	static boolean[] visited;
	static Stack<Integer> stack = new Stack<>();
	static int[] CCNum;
	static int cc = 0;

	public static void explore(ArrayList<Integer>[] adj, int e) {
		visited[e] = true;
		CCNum[e] = cc;
		for (Integer i : adj[e]) {
			if (visited[i] == false) {
				explore(adj, i);
			}
		}
	}

	private static int numberOfComponents(ArrayList<Integer>[] adj) {
		for (int k = 0; k < visited.length; k++) {
			if (visited[k] == false) {
				explore(adj, k);
				cc++;
			}
		}

		return cc;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		visited = new boolean[n];
		CCNum = new int[n];

		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
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
		System.out.println(numberOfComponents(adj));
	}
}
