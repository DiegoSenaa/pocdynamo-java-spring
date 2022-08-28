package com.dynamodb.pocdynamodb.controller;

import com.dynamodb.pocdynamodb.model.Address;
import com.dynamodb.pocdynamodb.model.Customer;
import com.dynamodb.pocdynamodb.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add/customer")
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/customer/{email}/{name}")
    public Customer getCustomerById(@PathVariable String email, @PathVariable String name) {
        return customerService.getCustomer(email, name);
    }

    @PutMapping ("/customer/{email}/{name}")
    public Customer putCustomer(@PathVariable String email,@PathVariable String name ,@RequestBody Address address) {
        return customerService.putCustomer(email, name, address);
    }

    @DeleteMapping("/customer/{email}/{name}")
    public void deleteCustomerById(@PathVariable String email, @PathVariable String name) {
       customerService.deleteCustomer(email, name);
    }

    @GetMapping("/customer/scan/{age}")
    public List<Customer> scanByAge(@PathVariable String age) {
        return customerService.scanByEmail(age);
    }

    @GetMapping("/customer/query/{email}/{partName}")
    public List<Customer> queryByAge(@PathVariable String email, @PathVariable String partName) {
        return customerService.queryByEmail(email, partName);
    }
}
