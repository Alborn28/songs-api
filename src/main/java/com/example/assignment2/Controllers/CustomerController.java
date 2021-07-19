package com.example.assignment2.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @GetMapping("/")
    public String index() {
        return "This is the root page";
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