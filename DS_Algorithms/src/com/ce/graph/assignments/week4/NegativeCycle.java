package com.ce.graph.assignments.week4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Program to detect Cycle with -ve weight edge to return 1 else 0. Cycle with
 * +ve weight edge will return 0.
 * 
 * @author sumitkumar
 *
 */
public class NegativeCycle {
	static Node[] nodes;
	static Queue<Integer> queue;
	static int n;

	private static int negativeCycle(ArrayList<Integer>[] adj, int[][] cost) {
		boolean cycle = false;
		/**
		 * Explore each node which is not visited. When we run BFS, we visit all the
		 * nodes that are reachable from the source. Hence if a graph is connected than
		 * all the nodes will be visited as part of a BFS. On the other hand, if a graph
		 * is disconnected, than we will end up running BFS for each such disconnected
		 * node.
		 */
		for (int i = 0; i < adj.length - 1; i++) {
			if (cycle) {
				/**
				 * If 1 cycle is detected than it is sufficient to break as per the problem
				 * statement
				 * 
				 */
				break;
			}

			/**
			 * Multiple disconnected nodes will get encountered here and than will be tested
			 * for a negative cycle.
			 */
			if (!nodes[i].visited) {

				/**
				 * Explore an unvisited Source node. This will get all the nodes reachable from
				 * this source node.
				 */
				int last = explore(i, adj, cost);

				/**
				 * Get all the nodes that got explored as part of BFS from the source.
				 */
				List<Integer> path = reconstructPath(i, last);

				/**
				 * This is for sure that one of the node will be in the negative cycle, and as
				 * such run an alternate BFS for each node,
				 */
				for (int k = 0; k < path.size(); k++) {
					cycle = findCycle(k, adj, cost);
					if (cycle) {
						/**
						 * Break as soon as one node with negative cycle is found.
						 */
						break;
					}
				}
			}
		}

		return cycle ? 1 : 0;
	}

	/**
	 * Returns all nodes discovered between Source and terminations node during BFS
	 * from Source. The nodes are returned in the reverse order, i.e. in the final
	 * result, the termination node appear at index 1 and source at the last index.
	 * 
	 * @param s
	 * @param u
	 * @return
	 */
	private static List<Integer> reconstructPath(int s, int u) {
		List<Integer> list = new ArrayList<>();

		/**
		 * if Source and termination node are same.
		 */
		if (u == s) {
			list.add(u + 1);
		} else {
			/**
			 * Construct the path in reverse order.
			 */
			while (u != s) {
				list.add(u + 1);
				u = nodes[u].previous;
				if (u == s) {
					list.add(u + 1);
				}
			}
		}

		return list;
	}

	/**
	 * Find Cycle runs a BFS and during visiting a node if the node relaxation is
	 * happening than it means a negative cycle exist and hence terminates with a
	 * value of true.
	 * 
	 * @param last
	 * @param adj
	 * @param cost
	 * @return
	 */
	public static boolean findCycle(int last, ArrayList<Integer>[] adj, int[][] cost) {
		boolean[] visit = new boolean[n];
		Queue<Integer> q = new LinkedList<>();
		q.add(last);

		while (!q.isEmpty()) {
			int u = q.poll();
			for (Integer v : adj[u]) {

				if (relaxVertex(u, v, cost[u][v])) {
					/**
					 * Only a negative Cycle result a true
					 */
					return true;
				} else {
					if (visit[v] == false) {
						q.add(v);
					}
				}
			}

			visit[u] = true;
		}
		return false;
	}

	/**
	 * Explore a node would return the last node in the path that is explored.
	 * 
	 * This is necessary since we can get disconnected graphs and all such graphs
	 * will have different source and end point.
	 *
	 * Returning the last node helps to reconstruct the path. All these nodes in the
	 * path can be part of a negative edge cycle.
	 * 
	 * @param node
	 * @param adj
	 * @param cost
	 * @return
	 */
	static int explore(int node, ArrayList<Integer>[] adj, int[][] cost) {
		int lastNode = node;
		nodes[node].distance = 0;
		nodes[node].previous = node;
		queue.add(node);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (Integer v : adj[u]) {
				if (!nodes[v].visited) {
					relaxVertex(u, v, cost[u][v]);
					queue.add(v);
					lastNode = v;
				}
			}
			nodes[u].visited = true;
		}
		return lastNode;
	}

	/**
	 * Updates the destination node distance with a lower value. If destination is
	 * updated than the parent is also updated.
	 * 
	 * It means, there is a path which an reach the destination vertex with a lower
	 * cost.
	 * 
	 * @param u
	 * @param v
	 * @param w
	 * @return
	 */
	static boolean relaxVertex(int u, int v, int w) {
		if (nodes[v].distance > (nodes[u].distance + w)) {
			nodes[v].distance = (nodes[u].distance + w);
			nodes[v].previous = u;
			return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		int m = scanner.nextInt();
		ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
		int[][] cost = new int[n][n];

		nodes = new Node[n];
		queue = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
			nodes[i] = new Node(false, Double.POSITIVE_INFINITY, i);
		}

		for (int i = 0; i < m; i++) {
			int x, y, w;
			x = scanner.nextInt();
			y = scanner.nextInt();
			w = scanner.nextInt();
			adj[x - 1].add(y - 1);
			cost[x - 1][y - 1] = w;
		}

		System.out.println(negativeCycle(adj, cost));
		scanner.close();
	}

	/**
	 * This class helps me avoid managing 3 different arrays for Visited, distance
	 * and previous node for path re-constructions.
	 * 
	 * @author sumitkumar
	 *
	 */
	static class Node {

		@Override
		public String toString() {
			return "Node [visited=" + visited + ", distance=" + distance + ", previous=" + previous + "]";
		}

		boolean visited;
		double distance;
		int previous;

		Node(boolean visit, double dist, int prev) {
			this.visited = visit;
			this.distance = dist;
			this.previous = prev;
		}

		Node(boolean visit, int dist) {
			this.visited = visit;
			this.distance = dist;
		}

		Node(boolean visit) {
			this.visited = visit;
		}
	}
}
