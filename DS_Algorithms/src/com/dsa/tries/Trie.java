package com.dsa.tries;

import java.util.List;

public class Trie {
	private TrieNode root;

	public Trie(List<String> list) {
		root = new TrieNode();
		for (String s : list) {
			root.addWord(s);
		}
	}

	public Trie(String[] list) {
		root = new TrieNode();
		for (String s : list) {
			root.addWord(s);
		}
	}

	public boolean contains(String prefix, boolean exact) {
		TrieNode lastNode = root;
		for (int i = 0; i < prefix.length(); i++) {
			lastNode = lastNode.getChild(prefix.charAt(i));
			if (lastNode == null) {
				return false;
			}
		}

		return !exact || lastNode.isTerminates();
	}

	public boolean contains(String prefix) {
		return contains(prefix, false);
	}

	public TrieNode getRoot() {
		return root;
	}
}
