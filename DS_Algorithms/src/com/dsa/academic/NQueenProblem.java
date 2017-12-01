package com.dsa.academic;

import java.util.ArrayList;
import java.util.List;

public class NQueenProblem {

	static void generatePermutations(List<Integer> perm, int n) {
		if (perm.size() == n) {
			System.out.println(perm.toString());
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!perm.contains(i)) {
				perm.add(i);
				generatePermutations(perm, n);
				perm.remove(i);
			}
		}

	}

	public static void main(String[] args) {
		generatePermutations(new ArrayList<Integer>(), 4);
	}

}
