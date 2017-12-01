package com.dsa.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author sumitkumar
 * 
 */
public class GraphOperationsAdjList {

	private LinkedList<Integer>[] graph;
	private boolean[] visited;
	private Queue<Integer> queue;

	@SuppressWarnings("unchecked")
	public GraphOperationsAdjList(int n) {
		graph = new LinkedList[n + 1];
		visited = new boolean[n + 1];
		queue = new PriorityQueue<Integer>();
		for (int i = 0; i <= n; i++) {
			graph[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int start, int end) {
		graph[start].add(end);
	}

	public void dfs(int start) {
		visited[start] = true;
		System.out.print(start + " ");

		for (int i = 0; i < graph[start].size(); i++) {
			if (visited[graph[start].get(i)] == false) {
				dfs(graph[start].get(i));
			}
		}
	}

	public void bfs(int start) {
		System.out.println(" ");
		queue.add(start);
		System.out.print("BFS:  ");
		while (!queue.isEmpty()) {
			int node = queue.poll();
			if (visited[node] == false) {
				visited[node] = true;
				System.out.print(node + " ");
				for (Integer u : graph[node]) {
					queue.add(u);
				}
			}
		}
	}

	public static void main(String[] args) {
		GraphOperationsAdjList g = new GraphOperationsAdjList(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		g.dfs(2);

		GraphOperationsAdjList g2 = new GraphOperationsAdjList(4);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(1, 2);
		g2.addEdge(2, 0);
		g2.addEdge(2, 3);
		g2.addEdge(3, 3);
		g2.bfs(2);

	}

}
