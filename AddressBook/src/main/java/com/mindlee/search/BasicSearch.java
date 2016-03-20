package com.mindlee.search;

import com.mindlee.cache.AddressBookBean;
import com.mindlee.cache.DataCache;
import com.mindlee.trie.TrieNode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: wei
 * Date: 9/19/15 12:22 AM
 */
public class BasicSearch {

    public List<AddressBookBean> getAddressBookBeansByKeys(TrieNode trieNode, DataCache dataCache) {
        if (trieNode == null) {
            return new ArrayList<AddressBookBean>();
        }
        List<Integer> keyList = trieNode.getKeyList();
        List<AddressBookBean> addressBookBeans = new ArrayList<AddressBookBean>();

        for (Integer key : keyList) {
            AddressBookBean addressBookBean =  dataCache.getAddressData().get(key);
            addressBookBeans.add(addressBookBean);
        }


        return addressBookBeans;
    }
}
