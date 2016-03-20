package com.mindlee.search;

import com.mindlee.cache.AddressBookBean;
import com.mindlee.cache.DataCache;
import com.mindlee.trie.Trie;
import com.mindlee.trie.TrieNode;

import java.util.List;

/**
 * User: wei
 * Date: 9/18/15 10:04 PM
 */
public class FirstNameSearch extends BasicSearch {

    public List<AddressBookBean> firstNameSearch(String firstName, DataCache dataCache) {
        Trie trie = dataCache.getFirstNameTrie();
        TrieNode trieNode = trie.find(firstName);

        return getAddressBookBeansByKeys(trieNode, dataCache);
    }
}
