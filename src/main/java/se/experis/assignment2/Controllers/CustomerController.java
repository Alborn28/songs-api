package se.experis.assignment2.Controllers;

import se.experis.assignment2.Models.Customer;
import se.experis.assignment2.Models.CustomerCountry;
import se.experis.assignment2.Models.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CustomerController {
    CustomerRepository customerRepository = new CustomerRepository();

    /**
     * Query 1
     * @return : ArrayList
     */
    @GetMapping("/query1")
    public ArrayList<Customer> getAllCustomersController() {
        return customerRepository.getAllCustomers();
    }

    @RequestMapping(value = "/query2", method = RequestMethod.GET)
    public Customer getSpecificCustomerController(@RequestParam("Id") String customerId) {
        return customerRepository.getSpecificCustomer(customerId);
    }

    @RequestMapping(value = "/query3", method = RequestMethod.GET)
    public ArrayList<Customer> getCustomerByNameController(@RequestParam("name") String name) {
        return customerRepository.getCustomerByName(name);
    }

    @RequestMapping(value = "/query4", method = RequestMethod.GET)
    public ArrayList<Customer> getSubsetOfCustomersController(@RequestParam("limit") int limit, @RequestParam("offset") int offset) {
        return customerRepository.getSubsetOfCustomers(limit, offset);
    }

    @RequestMapping(value = "/query5", method = RequestMethod.POST)
    public Boolean addCustomerController(@RequestBody Customer customer){
        return customerRepository.addCustomer(customer);
    }

    @RequestMapping(value = "query6", method = RequestMethod.PUT)
    public Boolean updateCustomerController(@RequestParam("id") int id, @RequestBody Customer customer) {
        if(id == customer.getId()) {
            return customerRepository.updateCustomer(customer);
        }

        return false;
    }

    @RequestMapping(value = "query7", method = RequestMethod.GET)
    public ArrayList<CustomerCountry> getCustomersPerCountryController() {
        return customerRepository.getCustomersPerCountry();
    }

    @GetMapping("/hello")
    public String greetGuest() {
        return "Hello, guest!";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String simpleSearch(@RequestParam("query") String terms) {
        return "Search results for " + terms;
    }

    @RequestMapping(value = "/{resource_type}/{resource_name}", method = RequestMethod.GET)
    public String simpleSearch(
            @PathVariable String resource_type, // pokemon
            @PathVariable String resource_name  // pikachu
    )
    {
        return "Data for " + resource_type + " with name " + resource_name;
    }
}