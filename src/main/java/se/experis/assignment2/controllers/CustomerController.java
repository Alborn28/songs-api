package se.experis.assignment2.controllers;

import se.experis.assignment2.models.Customer;
import se.experis.assignment2.models.CustomerCountry;
import se.experis.assignment2.data_access.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import se.experis.assignment2.models.CustomerGenre;
import se.experis.assignment2.models.CustomerSpender;

import java.util.ArrayList;

/**
 * Controller for REST endpoints of the API
 */
@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    /**
     * Maps all get requests to api/customers.
     * @return : ArrayList
     */
    @GetMapping("/api/customers")
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    /**
     * Maps all get requests to api/customer/ and takes ID as input.
     * @param Id : String
     * @return : Customer
     */
    @RequestMapping(value = "/api/customer/{Id}", method = RequestMethod.GET)
    public Customer getSpecificCustomer(@PathVariable String Id) {
        return customerRepository.getSpecificCustomer(Id);
    }

    /**
     * Maps all get requests to api/customer and takes a given String.
     * @param name : String
     * @return : ArrayList
     */
    @RequestMapping(value = "/api/customers/{name}", method = RequestMethod.GET)
    public ArrayList<Customer> getCustomerByName(@PathVariable String name) {
        return customerRepository.getCustomerByName(name);
    }

    /**
     * Maps all get requests to api/customers.
     * @param limit : Integer
     * @param offset : Integer
     * @return : ArrayList
     */
    @RequestMapping(value = "/api/customers/{limit}/{offset}", method = RequestMethod.GET)
    public ArrayList<Customer> getSubsetOfCustomers(@PathVariable int limit, @PathVariable int offset) {
        return customerRepository.getSubsetOfCustomers(limit, offset);
    }

    /**
     * Maps all post requests to api/customer.
     * @param customer : Customer
     * @return : boolean
     */
    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public Boolean addCustomer(@RequestBody Customer customer){
        return customerRepository.addCustomer(customer);
    }

    /**
     * Maps all put requests to api/customer.
     * @param id : Integer
     * @param customer : Customer
     * @return : boolean
     */
    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.PUT)
    public Boolean updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        if(id == customer.getId()) {
            return customerRepository.updateCustomer(customer);
        }
        return false;
    }

    /**
     * Maps all requests to api/customer/country
     * @return : ArrayList
     */
    @RequestMapping(value = "/api/customers/country", method = RequestMethod.GET)
    public ArrayList<CustomerCountry> getCustomersPerCountry() {
        return customerRepository.getCustomersPerCountry();
    }

    /**
     * Maps all requests to api/customers/spenders
     * @return : ArrayList
     */
    @RequestMapping(value = "/api/customers/spenders", method = RequestMethod.GET)
    public ArrayList<CustomerSpender> getHighestCustomersSpenders() {
        return customerRepository.getHighestCustomerSpenders();
    }

    /**
     * Maps all requests to api/customer/genre
     * @param id : Integer
     * @return : ArrayList
     */
    @RequestMapping(value = "api/customer/genre/{id}", method = RequestMethod.GET)
    public CustomerGenre getPopularGenreByCustomer(@PathVariable int id) {
        return customerRepository.getPopularGenreByCustomer(id);
    }
}