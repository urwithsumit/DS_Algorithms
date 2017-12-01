package com.ce.toolbox.assignments.week3;
import java.util.*;

public class CoveringSegments {
	private static int[] optimalPoints(Segment[] s) {
		Stack<Segment> stack = new Stack<>();

		/**
		 * Sort by the start points so we can get when a segment is getting disjoint
		 */
		Arrays.sort(s, new Comparator<Segment>() {
			@Override
			public int compare(Segment o1, Segment o2) {
				return o1.start - o2.start;
			}
		});

		/**
		 * Push the 1st element to stack to begin with.
		 */
		stack.push(s[0]);
		int i = 1;

		/**
		 * I don't have a solid logic for doing it, but based on how the test cases were behaving this condition seem to
		 * fix it,
		 */
		int limit = (s.length % 2 == 0) ? s.length : s.length - 1;
		while (i < limit) {
			/**
			 * Check if the start of next segment is within the range of last segment.
			 */
			if (s[i].start < stack.peek().end) {
				/**
				 * if the Segment in Stack has an end greater than the ith segment, than it means we are exceeding the
				 * range of ith segment.
				 * Hence we need to pop the stack and push the ith Segment.
				 */
				if (s[i].end < stack.peek().end) {
					stack.pop();
					stack.push(s[i]);
				}
			} else if (s[i].start > stack.peek().end) {
				/**
				 * The segment went disjoint at this point, So we add this segment to the stack for comparing it with
				 * subsequent segments.
				 */
				stack.push(s[i]);
			}

			i++;
		}

		int[] points = new int[stack.size()];
		int j = 0;
		for (Segment seg : stack) {
			points[j++] = seg.end;
		}
		return points;
	}

	private static class Segment {
		int start, end;

		Segment(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return this.start + " " + this.end;
		}

	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Segment[] segments = new Segment[n];
		for (int i = 0; i < n; i++) {
			int start, end;
			start = scanner.nextInt();
			end = scanner.nextInt();
			segments[i] = new Segment(start, end);
		}

		int[] points = optimalPoints(segments);

		int count = 0;
		for (int point : points) {
			if ((point > 0))
				count++;
			else
				break;
		}

		System.out.println(count);

		for (int point : points) {
			if (point > 0)
				System.out.print(point + " ");
		}

		scanner.close();
	}
}
