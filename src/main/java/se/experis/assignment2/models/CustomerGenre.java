package se.experis.assignment2.models;

import java.util.ArrayList;

public class CustomerGenre {
    private int customerId;
    private String firstName;
    private String lastName;
    private String country;
    private String postalCode;
    private String phoneNumber;
    private String mail;
    private ArrayList<String> genreName;

    public CustomerGenre() {
    }

    public CustomerGenre(int customerId, String firstName, String lastName, String country, String postalCode, String phoneNumber, String mail, ArrayList<String> genreName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.genreName = genreName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public ArrayList<String> getGenreName() {
        return genreName;
    }

    public void setGenreName(ArrayList<String> genreName) {
        this.genreName = genreName;
    }
}
