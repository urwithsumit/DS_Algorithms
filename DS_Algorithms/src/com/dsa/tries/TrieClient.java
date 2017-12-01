package com.dsa.tries;

public class TrieClient {

	public static void main(String[] args) {
		String[] list = { "sumit", "divit", "shikha" };

		Trie trie = new Trie(list);

		System.out.println(trie.contains("sumit"));
		System.out.println(trie.contains("it"));
		System.out.println(trie.contains("s"));
		System.out.println(trie.contains("sut"));
		System.out.println(trie.contains("sumi"));
		System.out.println(trie.contains("kumar"));
		System.out.println(trie.contains("Divit"));
		System.out.println(trie.contains("divit"));
		System.out.println(trie.contains("shik"));
		System.out.println(trie.contains("shikh"));

		System.out.println(trie.contains("sumit", true));
		System.out.println(trie.contains("it", true));
		System.out.println(trie.contains("s", true));
		System.out.println(trie.contains("sut", true));
		System.out.println(trie.contains("sumi", true));
		System.out.println(trie.contains("kumar", true));
		System.out.println(trie.contains("Divit", true));
		System.out.println(trie.contains("divit", true));
		System.out.println(trie.contains("shik", true));
		System.out.println(trie.contains("shikh", true));

	}

}
