package com.ce.graph.assignments.week2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Toposort {
	static boolean flag = true;

	private static ArrayList<Integer> toposort(ArrayList<Integer>[] adj) {
		int used[] = new int[adj.length];

		ArrayList<Integer> order = new ArrayList<Integer>();

		for (int i = 0; i < used.length; i++) {
			if (flag) {
				if (used[i] == 0) {
					dfs(adj, used, order, i);
				}
			} else {
				order.clear();
				break;
			}
		}

		return order;
	}

	private static void dfs(ArrayList<Integer>[] adj, int[] used, ArrayList<Integer> order, int s) {

		if (used[s] == -1) {
			flag = false;
			return;
		}

		if (used[s] == 0) {
			used[s] = -1;
			for (Integer i : adj[s]) {
				dfs(adj, used, order, i);
			}

			used[s] = 1;
			order.add(s);
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
		}

		ArrayList<Integer> order = toposort(adj);
		Collections.reverse(order);

		for (int x : order) {
			System.out.print((x + 1) + " ");
		}

		scanner.close();
	}
}
