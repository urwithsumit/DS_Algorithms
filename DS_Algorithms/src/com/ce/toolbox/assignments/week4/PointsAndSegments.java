package com.ce.toolbox.assignments.week4;
import java.util.Arrays;
import java.util.Scanner;

public class PointsAndSegments {

	static class Segment implements Comparable<Segment> {
		int start;
		char type;
		int index;

		@Override
		public String toString() {
			return "[" + start + "," + type + "]";
		}

		public Segment(int start, char end, int index) {
			this.start = start;
			this.type = end;
			this.index = index;
		}

		@Override
		public int compareTo(Segment o2) {
			if (o2 != null) {
				if (this.start < o2.start)
					return -1;
				if (this.start > o2.start)
					return 1;
				if (this.type < o2.type)
					return -1;
				if (this.type > o2.type)
					return 1;
			}
			return 0;
		}
	}

	private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
		int[] cnt = new int[points.length];

		Segment[] segArr = new Segment[(2 * starts.length) + points.length];
		int k = -1;
		for (int i = 0; i < starts.length; i++) {
			segArr[++k] = new Segment(starts[i], 'l', i);
		}

		for (int i = 0; i < ends.length; i++) {
			segArr[++k] = new Segment(ends[i], 'r', i);
		}

		for (int i = 0; i < points.length; i++) {
			segArr[++k] = new Segment(points[i], 'p', i);
		}

		Arrays.sort(segArr);

		//System.out.println(Arrays.toString(segArr));
		int count = 0;
		for (int j = 0; j < segArr.length; j++) {
			if (segArr[j].type == 'l') {
				count++;
			} else if (segArr[j].type == 'r') {
				count--;
			} else if (segArr[j].type == 'p') {
				cnt[segArr[j].index] = count;
			}
		}

		return cnt;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m;
		n = scanner.nextInt();
		m = scanner.nextInt();
		int[] starts = new int[n];
		int[] ends = new int[n];
		int[] points = new int[m];
		for (int i = 0; i < n; i++) {
			starts[i] = scanner.nextInt();
			ends[i] = scanner.nextInt();
		}
		for (int i = 0; i < m; i++) {
			points[i] = scanner.nextInt();
		}
		// use fastCountSegments
		int[] cnt = fastCountSegments(starts, ends, points);
		for (int x : cnt) {
			System.out.print(x + " ");
		}
	}
}
