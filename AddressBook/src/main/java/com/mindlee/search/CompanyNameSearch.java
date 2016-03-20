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
public class CompanyNameSearch extends BasicSearch{
    public List<AddressBookBean> companyNameSearch(String companyName, DataCache dataCache) {
        Trie trie = dataCache.getCompanyNameTrie();
        TrieNode trieNode = trie.find(companyName);

        return getAddressBookBeansByKeys(trieNode, dataCache);
    }
}
