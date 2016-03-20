package com.mindlee.search;

import com.mindlee.cache.AddressBookBean;
import com.mindlee.cache.DataCache;
import com.mindlee.trie.Trie;
import com.mindlee.trie.TrieNode;

import java.util.List;

/**
 * User: wei
 * Date: 9/18/15 8:07 PM
 */
public class LastNameSearch extends BasicSearch{
    public List<AddressBookBean> lastNameSearch (String lastName, DataCache dataCache) {
        Trie trie = dataCache.getLastNameTrie();
        TrieNode trieNode = trie.find(lastName);

        return getAddressBookBeansByKeys(trieNode, dataCache);
    }
}
