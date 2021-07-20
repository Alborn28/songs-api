package se.experis.assignment2.controllers;

import se.experis.assignment2.models.Customer;
import se.experis.assignment2.models.CustomerCountry;
import se.experis.assignment2.data_access.CustomerRepository;
import org.springframework.web.bind.annotation.*;
import se.experis.assignment2.models.CustomerGenre;
import se.experis.assignment2.models.CustomerSpender;

import java.util.ArrayList;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    /**
     * Query 1
     * @return : ArrayList
     */
    @GetMapping("/api/customers")
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @RequestMapping(value = "/api/customer/{Id}", method = RequestMethod.GET)
    public Customer getSpecificCustomer(@PathVariable String Id) {
        return customerRepository.getSpecificCustomer(Id);
    }

    @RequestMapping(value = "/api/customers/{name}", method = RequestMethod.GET)
    public ArrayList<Customer> getCustomerByName(@PathVariable String name) {
        return customerRepository.getCustomerByName(name);
    }

    @RequestMapping(value = "/api/customers/{limit}/{offset}", method = RequestMethod.GET)
    public ArrayList<Customer> getSubsetOfCustomers(@PathVariable int limit, @PathVariable int offset) {
        return customerRepository.getSubsetOfCustomers(limit, offset);
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.POST)
    public Boolean addCustomer(@RequestBody Customer customer){
        return customerRepository.addCustomer(customer);
    }

    @RequestMapping(value = "/api/customer/{id}", method = RequestMethod.PUT)
    public Boolean updateCustomer(@PathVariable int id, @RequestBody Customer customer) {
        if(id == customer.getId()) {
            return customerRepository.updateCustomer(customer);
        }

        return false;
    }

    @RequestMapping(value = "/api/customers/country", method = RequestMethod.GET)
    public ArrayList<CustomerCountry> getCustomersPerCountry() {
        return customerRepository.getCustomersPerCountry();
    }

    @RequestMapping(value = "/api/customers/spenders", method = RequestMethod.GET)
    public ArrayList<CustomerSpender> getHighestCustomersSpenders() {
        return customerRepository.getHighestCustomerSpenders();
    }

    @RequestMapping(value = "api/customer/genre/{id}", method = RequestMethod.GET)
    public ArrayList<CustomerGenre> getPopularGenreByCustomer(@PathVariable int id) {
        return customerRepository.getPopularGenreByCustomer(id);
    }
}