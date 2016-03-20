package com.mindlee.cache;

import com.mindlee.trie.Trie;
import com.mindlee.util.FileUtils;
import com.mindlee.util.StackTraceUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wei
 * Date: 9/18/15 8:59 PM
 */
public class DataCache {
    public static int addressKey = 0;

    private static Map<Integer, AddressBookBean> addressData = new HashMap<Integer, AddressBookBean>();

    private static Trie firstNameTrie = new Trie();
    private static Trie lastNameTrie = new Trie();
    private static Trie phoneNumberTrie = new Trie();
    private static Trie companyNameTrie = new Trie();

    public static final String HUGE_ADDRESS_BOOK_FILE_NAME = "HugeAddressBook.csv";
    public static final String LARGE_ADDRESS_BOOK_FILE_NAME = "LargeAddressBook.csv";
    public static final String MEDIUM_ADDRESS_BOOK_FILE_NAME = "MediumAddressBook.csv";
    public static final String SMALL_ADDRESS_BOOK_FILE_NAME = "SmallAddressBook.csv";


    public void loadHugeAddressBook() {
        System.out.println("System is Loading Huge AddressBook, estimate 5 ~ 6s.");
        loadData(HUGE_ADDRESS_BOOK_FILE_NAME);
    }

    public void loadLargeAddressBook() {
        System.out.println("System is Loading Large AddressBook, estimate 600 ~ 800 ms.");
        loadData(LARGE_ADDRESS_BOOK_FILE_NAME);
    }

    public void loadMediumAddressBook() {
        System.out.println("System is Loading Medium AddressBook, estimate 100 ~ 200ms.");
        loadData(MEDIUM_ADDRESS_BOOK_FILE_NAME);
    }

    public void loadSmallAddressBook() {
        System.out.println("System is Loading Small AddressBook, estimate 30 ~ 50ms.");
        loadData(SMALL_ADDRESS_BOOK_FILE_NAME);
    }


    private void loadData(String fileName) {
        try {
            File res = new File(DataCache.class.getResource("/" + fileName).getFile());
            List<String> recordList = FileUtils.read(res);
            for (String record: recordList) {
                try {
                    String addressRecord[] = record.split(",");
                    AddressBookBean addressBookBean = createNewAddressBean(addressRecord);

                    int key = getAddressKey();
                    addressData.put(key, addressBookBean);
                    insertTrieNode(addressBookBean, key);
                } catch (Exception e) {
                    System.out.println("read record error");
                    System.out.println(StackTraceUtil.getStackTrace(e));
                }
            }
        } catch (Exception e) {
            System.out.println("Read file error.");
            System.out.println(StackTraceUtil.getStackTrace(e));
        }
    }

    public AddressBookBean createNewAddressBean(String[] addressRecord) {
        AddressBookBean addressBookBean = new AddressBookBean();
        addressBookBean.setFirstName(addressRecord[0].trim());
        addressBookBean.setLastName(addressRecord[1].trim());
        addressBookBean.setPhoneNumber(addressRecord[2].trim());
        addressBookBean.setCompanyName(addressRecord[3].trim());
        return addressBookBean;
    }

    public void insertTrieNode(AddressBookBean addressBookBean, Integer key) {
        firstNameTrie.insert(addressBookBean.getFirstName(), key);
        lastNameTrie.insert(addressBookBean.getLastName(), key);
        phoneNumberTrie.insert(addressBookBean.getPhoneNumber(), key);
        companyNameTrie.insert(addressBookBean.getCompanyName(), key);
    }

    public static int getAddressKey() {
        return addressKey++;
    }

    public Map<Integer, AddressBookBean> getAddressData() {
        return addressData;
    }

    public Trie getPhoneNumberTrie() {
        return phoneNumberTrie;
    }

    public Trie getLastNameTrie() {
        return lastNameTrie;
    }

    public Trie getFirstNameTrie() {
        return firstNameTrie;
    }

    public Trie getCompanyNameTrie() {
        return companyNameTrie;
    }

    public static void main(String[] args) {
        DataCache dataCache = new DataCache();
        System.out.println("hello world");
    }
}