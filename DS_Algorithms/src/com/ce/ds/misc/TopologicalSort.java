package com.ce.ds.misc;
import java.util.*;

public class TopologicalSort {

	private ArrayList<Integer>[] adjList;
	private char[] visited;
	private int vertices;
	private List<Integer> result;

	@SuppressWarnings("unchecked")
	public TopologicalSort(int vertices) {
		this.vertices = vertices;
		adjList = new ArrayList[vertices + 1];
		visited = new char[vertices + 1];
		result = new ArrayList<>();

		for (int i = 0; i <= vertices; i++) {
			adjList[i] = new ArrayList<Integer>();
			visited[i] = 'u';
		}
	}

	void addEdge(int start, int end) {
		adjList[start].add(end);
	}

	void visit(int i) throws Exception {
		if (visited[i] == 'p') {
			throw new Exception("Cycle Exist. TSort not possible");
		}

		if (visited[i] == 'u') {
			visited[i] = 'p';

			for (Integer j : adjList[i]) {
				visit(j);
			}

			visited[i] = 'v';
			result.add(i);

		}

	}

	public void tSort() throws Exception {
		for (int i = 1; i <= vertices; i++) {
			if (visited[i] == 'u') {
				visit(i);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		TopologicalSort sort = new TopologicalSort(6);
		sort.addEdge(6, 3);
		sort.addEdge(6, 1);
		sort.addEdge(5, 1);
		sort.addEdge(5, 2);
		sort.addEdge(3, 4);
		sort.addEdge(4, 2);

		sort.tSort();

		System.out.println("Result: " + sort.result.toString());

	}

}