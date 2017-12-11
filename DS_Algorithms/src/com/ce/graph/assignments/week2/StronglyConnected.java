package com.ce.graph.assignments.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Input: 4 4 1 2 4 1 2 3 3 1
 * 
 * Compute the Strongly Connected Component in a Directed Acyclic Graph
 * 
 * @author sumitkumar
 *
 */
public class StronglyConnected {

	static Queue<PostOrder> postOrderQ;
	static boolean[] visited;
	static int clock = 0;
	static int SCC = 0;
	static List<String> metaGraphNode = new ArrayList<>();

	/**
	 * Find Strongly Connected Component in a Directed Acyclic Graph.
	 * 
	 * @param graph
	 * @param graphRev
	 * @return
	 */
	private static int findSCNodes(ArrayList<Integer>[] graph, ArrayList<Integer>[] graphRev) {

		// Pass 1 - Run DFS on Reverse Graph and calculate all PostOrders of nodes. The
		// Sink Nodes will have the highest Post orders.
		for (int i = 0; i < graphRev.length; i++) {
			if (!visited[i]) {
				DFS(graphRev, i, true, null);
			}
		}

		// To reuse visited - Reset the Visited.
		Arrays.fill(visited, false);

		// Pass 2 - Run DFS on Graph by choosing the node which are having the highest
		// Post
		// order. This node is going to be a Sink Component in the Reverse Graph.
		while (!postOrderQ.isEmpty()) {
			PostOrder order = postOrderQ.poll();

			if (!visited[order.node]) {
				StringBuilder connectedComp = new StringBuilder();
				DFS(graph, order.node, false, connectedComp);
				++SCC;
				metaGraphNode.add(connectedComp.toString());
			}
		}

		return SCC;
	}

	static void DFS(ArrayList<Integer>[] graph, int root, boolean flag, StringBuilder ccBuilder) {

		if (!visited[root]) {
			visited[root] = true;
			List<Integer> adj = graph[root];
			for (Integer i : adj) {
				DFS(graph, i, flag, ccBuilder);
			}

			if (flag) {
				// Calculate Post Order only For Reverse Graph - Pass 1
				postOrderQ.add(new PostOrder(root, ++clock));
			} else {
				// Append Strongly Connected Component - Pass 2
				ccBuilder.append(root + 1).append(" ");
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int vertices = scanner.nextInt();
		int queries = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[vertices];
		ArrayList<Integer>[] adjRev = (ArrayList<Integer>[]) new ArrayList[vertices];
		postOrderQ = new PriorityQueue<PostOrder>(vertices, new Comparator<PostOrder>() {

			@Override
			public int compare(PostOrder o1, PostOrder o2) {
				return o1.postOrder > o2.postOrder ? -1 : 1;
			}

		});

		visited = new boolean[vertices];

		for (int i = 0; i < vertices; i++) {
			adj[i] = new ArrayList<Integer>();
			adjRev[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < queries; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			adj[x - 1].add(y - 1);
			adjRev[y - 1].add(x - 1);
		}

		scanner.close();

		System.out.println("Total Strongly Component:  " + findSCNodes(adj, adjRev));

		System.out.println("Strongly Connected Components are as below: ");
		for (String metaGraph : metaGraphNode) {
			System.out.println(metaGraph);
		}

	}

	static class PostOrder {
		int node;
		int postOrder;

		public PostOrder(int node, int post) {
			this.node = node;
			this.postOrder = post;
		}

		@Override
		public String toString() {
			return "[ node: " + node + ", postOrder:" + postOrder + " ]";
		}

	}
}
