package com.mindlee.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wei
 * Date: 9/18/15 8:51 PM
 */
public class TrieNode {
    private List<Integer> keyList;
    private Boolean isLeaf;
    private Map<Character, TrieNode> children = null;

    public TrieNode() {
        keyList = new ArrayList<Integer>();
        isLeaf = false;
        children = new HashMap<Character, TrieNode>();
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public List getKeyList() {
        return keyList;
    }

    public void addKey(Integer key) {
        keyList.add(key);
    }

}
