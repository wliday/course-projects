package com.mindlee.search;

import com.mindlee.cache.AddressBookBean;
import com.mindlee.cache.DataCache;
import com.mindlee.trie.Trie;
import com.mindlee.trie.TrieNode;

import java.util.List;

/**
 * User: wei
 * Date: 9/18/15 8:08 PM
 */
public class PhoneNumberSearch extends BasicSearch{
    public List<AddressBookBean> phoneNumberSearch(String phoneNumber, DataCache dataCache) {
        Trie trie = dataCache.getPhoneNumberTrie();
        TrieNode trieNode = trie.find(phoneNumber);

        return getAddressBookBeansByKeys(trieNode, dataCache);
    }
}
