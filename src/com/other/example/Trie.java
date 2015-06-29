package com.other.example;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Simple, efficient implementation of a trie that maps prefixes to objects of the generic type.
 *
 * Populate example:
 *
 * Trie<Integer> prefixToValueMap = Trie.newTrie();
 * // these configs are structured like: "prefix1a,prefix1b,prefix1c:value1;prefix2:value2;prefix3:value3"
 * String semicolonSeparated = Strings.nullToEmpty(configString);
 * for (String colonSeparated : Splitter.on(';').trimResults().omitEmptyStrings().split(semicolonSeparated)) {
 *     Iterator<String> keysAndValue = Splitter.on(':').trimResults().omitEmptyStrings().split(colonSeparated).iterator();
 *     String keys = keysAndValue.next();
 *     String value = keysAndValue.next();
 *     for (String key : Splitter.on(',').trimResults().omitEmptyStrings().split(keys)) {
 *         prefixToValueMap.put(key, value);
 *     }
 * }
 *
 * Usage example:
 * String result = prefixToValueMap.get(Strings.nullToEmpty(msisdn));
 * return result != null ? result : defaultResultIfNothingFound;
 *
 */
public class Trie<T> {

    private class TrieNode {
        T value;
        Map<Character, TrieNode> next = Maps.newHashMap();
    }

	private TrieNode root = new TrieNode();

	/**
	 * Private constructor
	 */
	private Trie() {}

	/**
     * Construct a new, empty Trie.
     * @return the new Trie
     */
    public static <T> Trie<T> newTrie() {
        return new Trie<T>();
    }

	/**
	 * Map the prefix to the given value.
	 * @param prefix
	 * @param value
	 */
	public void put(CharSequence prefix, T value) {
		TrieNode currentNode = root;
		for (Character ch : Lists.charactersOf(prefix)) {
			TrieNode nextNode = currentNode.next.get(ch);
			if (nextNode == null) {
				nextNode = new TrieNode();
				currentNode.next.put(ch, nextNode);
			}
			currentNode = nextNode;
		}
		currentNode.value = value;
	}

	/**
	 * Returns the value associated with the shortest known prefix of this charSequence, or
	 * null if no known prefix exists.
	 * @param charSequence
	 */
	public T get(CharSequence charSequence) {
		TrieNode currentNode = root;
		for (Character ch : Lists.charactersOf(charSequence)) {
			currentNode = currentNode.next.get(ch);
			if (currentNode == null) { // reached a leaf node in the trie
				return null;
			} else if (currentNode.value != null) {
				return currentNode.value;
			}
		}
		return null;
	}

	@Override
    public String toString() {
		return toStringRecursive(root, new StringBuilder(), MoreObjects.toStringHelper(this)).toString();
	}

	private ToStringHelper toStringRecursive(TrieNode trieNode, StringBuilder prefix, ToStringHelper toString) {

		if (trieNode.value != null) {
			toString.add(prefix.toString(), trieNode.value);
		}

		for (Entry<Character,TrieNode> entry : trieNode.next.entrySet()) {
			// reuse the stringbuilder if possible
			StringBuilder newPrefix = trieNode.next.size() == 1 ? prefix : new StringBuilder(prefix);
			toStringRecursive(entry.getValue(), newPrefix.append(entry.getKey()), toString);
		}

		return toString;
	}
}