package com.mindlee.trie;

/**
 * User: wei
 * Date: 9/18/15 8:35 PM
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    //插入字符串
    public void insert(String word, Integer key) {
        TrieNode node = root;
        word = word.trim();
        for (int i = 0; i < word.length(); i++) {
            if (!(node.getChildren().containsKey(word.charAt(i)))) {
                node.getChildren().put(word.charAt(i), new TrieNode());
            }
            node = node.getChildren().get(word.charAt(i));
            node.addKey(key);
        }
        node.setIsLeaf(true);
    }

    //查找某个字符串
    public TrieNode find(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            if (!(node.getChildren().containsKey(word.charAt(i)))) {
                return null;
            } else {
                node = node.getChildren().get(word.charAt(i));
            }
        }
        return node;
    }

}

