package com.mindlee.cache;

/**
 * User: wei
 * Date: 9/18/15 9: 01 PM
 */
public class AddressBookBean {

    private String firstName = null;
    private String lastName = null;
    private String phoneNumber = null;
    private String companyName = null;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(firstName + ", " + lastName + ", " + phoneNumber + "," + companyName + "\n");
        return stringBuffer.toString();
    }
}
