package com.ce.ds.week3.assignment;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Job implements Comparable<Job> {
	long startTime;
	int threadName;
	long busyTime;
	long nextAvlTime;

	@Override
	public String toString() {
		return "Job [startTime=" + startTime + ", threadName=" + threadName + ", busyTime=" + busyTime + ", nextAvlTime=" + nextAvlTime + "]";
	}

	public Job(int name, long startTime, long busyTime) {
		this.threadName = name;
		this.startTime = startTime;
		this.busyTime = busyTime;
		this.nextAvlTime = this.startTime + this.busyTime;
	}

	public int compareTo(Job job) {

		if (job == null)
			return -1;

		if ((job.nextAvlTime) == (this.nextAvlTime)) {
			if (job.threadName < this.threadName) {
				return 1;
			}
		} else if (job.nextAvlTime < this.nextAvlTime) {
			return 1;
		}

		return -1;
	}
}

public class JobQueue {
	private int numWorkers;
	private int[] jobs;
	private Job[] schduledJobs;
	private Queue<Job> pQueue = new PriorityQueue<Job>();

	private FastScanner in;
	private PrintWriter out;

	public static void main(String[] args) throws IOException {
		new JobQueue().solve();
	}

	private void readData() throws IOException {
		numWorkers = in.nextInt();
		int m = in.nextInt();
		jobs = new int[m];
		for (int i = 0; i < m; ++i) {
			jobs[i] = in.nextInt();
		}
	}

	private void writeResponse() {
		for (Job job : schduledJobs) {
			System.out.println(job.threadName + " " + job.nextAvlTime);
		}
	}

	private void assignJobs() {
		schduledJobs = new Job[jobs.length];

		// Initialize Queue with Workers
		for (int j = 0; j < numWorkers; j++) {
			pQueue.add(new Job(j, 0L, 0L));
		}

		for (int i = 0; i < jobs.length; i++) {
			Job job = pQueue.poll();
			pQueue.add(new Job(job.threadName, job.nextAvlTime, jobs[i]));
			schduledJobs[i] = job;
		}

	}

	public void solve() throws IOException {
		in = new FastScanner();
		out = new PrintWriter(new BufferedOutputStream(System.out));
		readData();
		assignJobs();
		writeResponse();
		out.close();
	}

	static class FastScanner {
		private BufferedReader reader;
		private StringTokenizer tokenizer;

		public FastScanner() {
			reader = new BufferedReader(new InputStreamReader(System.in));
			tokenizer = null;
		}

		public String next() throws IOException {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			return tokenizer.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
}
