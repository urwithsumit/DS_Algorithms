package com.dsa.tries;

import java.util.HashMap;
import java.util.Map;

public class TrieNode implements ITrieNode {

	/**
	 * Maintain a map of childNodes at each Trie Node to traverse down the trie
	 */
	private Map<Character, TrieNode> children;

	/**
	 * Helps to determine when the Trie has reached the last element
	 */
	private boolean terminates = false;

	/**
	 * Payload of the current Trie Node.
	 */
	private char payLoad;

	/**
	 * Use this to Construct the Root of the Trie Node
	 */
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
	}

	/**
	 * Use this Constructor to create the Child Nodes.
	 * 
	 * @param character
	 */
	public TrieNode(char character) {
		this();
		this.payLoad = character;
	}

	public char getChar() {
		return payLoad;
	}

	/**
	 * Returns null if no child is found.
	 * @param character
	 * @return
	 */
	public TrieNode getChild(char character) {
		return children.get(character);
	}

	public void setTerminates(boolean flag) {
		this.terminates = flag;
	}

	public boolean isTerminates() {
		return terminates;
	}

	/**
	 * Add this word to the Trie and recursively create the Child Nodes
	 */
	public void addWord(String word) {

		if (word == null || word.isEmpty()) {
			return;
		}

		char firstChar = word.charAt(0);
		TrieNode childNode = getChild(firstChar);
		if (childNode == null) {
			childNode = new TrieNode(firstChar);
			children.put(firstChar, childNode);
		}

		if (word.length() > 1) {
			/**
			 * Recursive Call
			 */
			childNode.addWord(word.substring(1));
		} else {
			childNode.setTerminates(true);
		}

	}

}
