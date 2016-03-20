# AddressBook

*AddressBook* is one project in my algorithm class, The purpose of this project is to search out all contacts information from address book when I just type first few letters. I use Trie data structure to solve this problem. And eliminate the initial loading time, the time complexity to search one word is O(len(type-letters)).

### Problem
Create an address book that can provide search functionality on multiple fields.  The structure is very simple – first name, last name, phone number and city name.  You can search on ANY of these fields, and can search partially using “begins with” syntax. For example, you can search for – first name starts with “Ron…”.  Another search can be – phone number starts with “202…”.

Obviously, binary search will play a role here.  So, you can try to keep the list sorted.  But one interesting aspect to consider is that we can search on ANY of those attributes.  That is, the user will give it a few searches (some on first name, some on last name, some on telephone number, some on city).

### Solution
This project uses simple Trie implementation to solve this problem. 

### Preliminary Work：
Since the source file,「HugeAddressBook.csv」, needs enough memory in JVM. So I need to supplement “**-Xms2000m -Xmx2000m**” in the VM options.


### Project Structure:
| package  | details|
| ------------- | ------------- |
| com.mindlee.cache  | storage data;  |
| com.mindlee.search | search engine |
| com.mindlee.trie| trie algorithms |
| com.mindlee.util| utility class|


### Storage Structure:
1. Build four Tries, FirstNameTrie, LastNameTrie, PhoneNumberTrie, CompanyNameTrie.
2. All the records storage in a HashMap, <Key, AddressRecordBean>
3. Each TrieNode store one key of records, and then get AddressRecordBean from the map.

### Search Process:
The primary process is, search engine get key in Trie, and then get address record from the Map<Key, AddressRecordBean>

### Other
When I use JDK 1.6, the load time of huge time is about 9~10s; and when I update to JDK 1.8, the load time reduces to 5s.

### User Interface
Welcome to Addressbook, initialized!

Which database would you want to choose? Please enter the instruction number as following:

>
>1. Small AddressBook
>2. Medium AddressBook
>3. Large AddressBook
>4. Huge AddressBook

>4

>System is Loading Huge AddressBook, estimate 5 ~ 6s.

>F

>Data load consume 5368 ms.

>What would you like to search on ?(F,L,P,C)

>Enter the first name:

>Es

>in total 1278 records.

>Esperanza, Mildon, 810-664-8413,Weiss Berzowski Brady

>Essie, Cambi, 908-526-4077,Hyde Bernard T

>...

>1278 records load complete.

>Another Search?(Y, N)