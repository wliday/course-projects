package com.mindlee;

import com.mindlee.cache.AddressBookBean;
import com.mindlee.cache.DataCache;
import com.mindlee.search.CompanyNameSearch;
import com.mindlee.search.FirstNameSearch;
import com.mindlee.search.LastNameSearch;
import com.mindlee.search.PhoneNumberSearch;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String FIRST_NAME = "F";
    public static final String LAST_NAME = "L";
    public static final String PHONE_NUMBER = "P";
    public static final String COMPANY_NAME = "C";
    public static final String SMALL_ADDRESS_BOOK_INSTRUCTION = "1";
    public static final String MEDIUM_ADDRESS_BOOK_INSTRUCTION = "2";
    public static final String LARGE_ADDRESS_BOOK_INSTRUCTION = "3";
    public static final String HUGE_ADDRESS_BOOK_INSTRUCTION = "4";

    public static void main(String[] args) {

        CompanyNameSearch companyNameSearch = new CompanyNameSearch();
        FirstNameSearch firstNameSearch = new FirstNameSearch();
        LastNameSearch lastNameSearch = new LastNameSearch();
        PhoneNumberSearch phoneNumberSearch = new PhoneNumberSearch();
        boolean isContinueSearch = true;

        DataCache dataCache = new DataCache();

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Addressbook, initialized!");
        System.out.println("Which database would you want to choose. Please enter the instruction number as following:");
        System.out.println("1. Small AddressBook");
        System.out.println("2. Medium AddressBook");
        System.out.println("3. Large AddressBook");
        System.out.println("4. Huge AddressBook");
        String input = in.nextLine();

        long startTime = System.currentTimeMillis();
        if (SMALL_ADDRESS_BOOK_INSTRUCTION.equals(input)) {
            dataCache.loadSmallAddressBook(); //36ms
        } else if (MEDIUM_ADDRESS_BOOK_INSTRUCTION.equals(input)) {
            dataCache.loadMediumAddressBook(); //132ms
        } else if (LARGE_ADDRESS_BOOK_INSTRUCTION.equals(input)) {
            dataCache.loadLargeAddressBook(); //667ms
        } else {
            dataCache.loadHugeAddressBook();// 5180ms
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Data load consume " + (endTime - startTime) + " ms.");

        while (isContinueSearch) {

            try {
                System.out.println("What would you like to search on ?(F,L,P,C)");
                List<AddressBookBean> addressBookBeans = null;
                input = in.nextLine();
                if (FIRST_NAME.equals(input)) {
                    System.out.println("Enter the first name:");
                    String firstName = in.nextLine();
                    addressBookBeans = firstNameSearch.firstNameSearch(firstName, dataCache);

                } else if (LAST_NAME.equals(input)) {
                    System.out.println("Enter the last name:");
                    String lastName = in.nextLine();
                    addressBookBeans = lastNameSearch.lastNameSearch(lastName, dataCache);

                } else if (PHONE_NUMBER.equals(input)) {
                    System.out.println("Enter the phone number:");
                    String phoneNumber = in.nextLine();
                    addressBookBeans = phoneNumberSearch.phoneNumberSearch(phoneNumber, dataCache);

                } else if (COMPANY_NAME.equals(input)) {
                    System.out.println("Enter the company name:");
                    String companyName = in.nextLine();
                    addressBookBeans = companyNameSearch.companyNameSearch(companyName, dataCache);
                }

                printSearchResult(addressBookBeans);
                System.out.println("Another Search?(Y, N)");
                String input2 = in.nextLine();

                isContinueSearch = getServiceInstruction(input2);
            } catch (Exception e) {
                isContinueSearch = false;
                System.out.println("Input incorrect!");
            }
        }

        in.close();
        System.out.println("Bye");
    }

    private static void printSearchResult(List<AddressBookBean> addressBookBeans) {
        if (addressBookBeans == null || addressBookBeans.size() == 0) {
            System.out.println("can't find, please try again!");
            return;
        }
        System.out.println("in total " + addressBookBeans.size() + " records.");
        for (AddressBookBean addressBookBean : addressBookBeans) {
            System.out.println(addressBookBean);
        }
        System.out.println(addressBookBeans.size() + " records load complete.");
    }

    private static Boolean getServiceInstruction(String input) {
        if ("Y".equals(input)) {
            return true;
        }
        return false;
    }
}
