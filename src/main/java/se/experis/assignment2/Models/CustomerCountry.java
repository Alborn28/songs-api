package se.experis.assignment2.Models;

public class CustomerCountry {
    private String country;
    private int numberOfCustomers;

    public CustomerCountry() {
    }

    public CustomerCountry(String country, int numberOfCustomers) {
        this.country = country;
        this.numberOfCustomers = numberOfCustomers;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }
}
