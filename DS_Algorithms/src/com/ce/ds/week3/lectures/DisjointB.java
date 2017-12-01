package com.ce.ds.week3.lectures;

import java.util.Arrays;

public class DisjointB {
	int n;
	static int[] array;
	static int[] rank;

	public DisjointB(int i) {
		this.n = i;
		array = new int[n];
		rank = new int[n];
	}

	public static void main(String[] args) {

		System.out.println("quizQ1: ");
		quizQ1();

		System.out.println("\nquizQ2: ");
		quizQ2();

		System.out.println("\nquizQ3: ");
		quizQ3();

		System.out.println("\nquizQ4: ");
		quizQ4();

	}

	private void union(int i, int j) {
		int p1 = find(i);
		int p2 = find(j);

		if (p1 == p2) {
			return;
		}

		if (rank[p1] > rank[p2]) {
			array[p2] = p1;
		} else {
			array[p1] = p2;
			if (rank[p1] == rank[p2]) {
				rank[p2] += 1;
			}
		}

	}

	private int find(int i) {
		while (i != array[i]) {
			i = array[i];
		}
		return i;
	}

	private void makeSet(int i) {
		array[i] = i;
	}

	private int smallestSetElement(int parent) {
		for (int i = 0; i < n; i++) {
			if (array[i] == parent) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Assume that the disjoint sets data structure is implemented as an array 𝚜𝚖𝚊𝚕𝚕𝚎𝚜𝚝[1…12]:
	 * 𝚜𝚖𝚊𝚕𝚕𝚎𝚜𝚝[i] is equal to the smallest element in the set containing i.
	 * 
	 * What is the output of the following program.
	 */
	static void quizQ1() {

		DisjointB ds = new DisjointB(13);

		for (int i = 1; i < 13; i++) {
			ds.makeSet(i);
		}

		ds.union(array[2], array[10]);
		ds.union(array[7], array[5]);
		ds.union(array[6], array[1]);
		ds.union(array[3], array[4]);
		ds.union(array[5], array[11]);
		ds.union(array[7], array[8]);
		ds.union(array[7], array[3]);
		ds.union(array[12], array[2]);
		ds.union(array[9], array[6]);

		System.out.print(ds.smallestSetElement(ds.find(6)));
		System.out.print(" ");
		System.out.print(ds.smallestSetElement(ds.find(3)));
		System.out.print(" ");
		System.out.print(ds.smallestSetElement(ds.find(11)));
		System.out.print(" ");
		System.out.print(ds.smallestSetElement(ds.find(9)));
		System.out.println("");
		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(rank));

	}

	/**
	 * Assume that the disjoint sets data structure is implemented as disjoint trees with union by rank heuristic.
	 * 
	 * Compute the product of the heights of the resulting trees after executing the code
	 */
	static void quizQ2() {

		DisjointB ds = new DisjointB(13);

		for (int i = 1; i < 13; i++) {
			ds.makeSet(i);
		}

		ds.union(array[2], array[10]);
		ds.union(array[7], array[5]);
		ds.union(array[6], array[1]);
		ds.union(array[3], array[4]);
		ds.union(array[5], array[11]);
		ds.union(array[7], array[8]);
		ds.union(array[7], array[3]);
		ds.union(array[12], array[2]);
		ds.union(array[9], array[6]);

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(rank));

	}

	/**
	 * Assume that the disjoint sets data structure is implemented as disjoint trees with union by rank heuristic.
	 * 
	 * What is the number of trees in the forest and the maximum height of a tree in this forest after executing this
	 * code? (Recall that the height of a tree is the number of edges on a longest path from the root to a leaf. In
	 * particular, the height of a tree consisting of just one node is equal to 0.)
	 */
	static void quizQ3() {

		DisjointB ds = new DisjointB(13);

		for (int i = 1; i < 13; i++) {
			ds.makeSet(i);
		}

		for (int i = 1; i < 12; i++) {
			ds.union(i, i + 1);
		}

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(rank));
	}

	/**
	 * Assume that the disjoint sets data structure is implemented as disjoint trees with union by rank heuristic and
	 * with path compression heuristic.
	 * 
	 * Compute the maximum height of a tree in the resulting forest.
	 */
	static void quizQ4() {

		DisjointB ds = new DisjointB(61);

		for (int i = 1; i <= 60; i++) {
			ds.makeSet(i);
		}

		for (int i = 1; i <= 30; i++) {
			ds.union(i, 2 * i);
		}

		for (int i = 1; i <= 20; i++) {
			ds.union(i, 3 * i);
		}

		for (int i = 1; i <= 12; i++) {
			ds.union(i, 5 * i);
		}

		for (int i = 1; i <= 60; i++) {
			ds.find(i);
		}

		System.out.println(Arrays.toString(array));
		System.out.println(Arrays.toString(rank));

	}

}
