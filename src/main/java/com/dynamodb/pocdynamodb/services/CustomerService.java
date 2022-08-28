package com.dynamodb.pocdynamodb.services;

import com.dynamodb.pocdynamodb.model.Address;
import com.dynamodb.pocdynamodb.model.Customer;
import com.dynamodb.pocdynamodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    public Customer getCustomer(String email, String name) {
        return customerRepository.getCustomerByEmailAndName(email, name);
    }

    public Customer putCustomer(String email, String name, Address address) {

        Customer customerByEmailAndName = customerRepository.getCustomerByEmailAndName(email, name);

        customerByEmailAndName.setAddress(address);

        customerRepository.saveCustomer(customerByEmailAndName);

        return customerByEmailAndName;
    }

    public void deleteCustomer(String email, String name) {

        Customer customerByEmailAndName = customerRepository.getCustomerByEmailAndName(email, name);

        customerRepository.deleteCustomer(customerByEmailAndName);

    }


    public List<Customer> scanByEmail(String age) {

        return customerRepository.scanCustomerByEmail(age);

    }

    public List<Customer> queryByEmail(String email, String partName) {

        return customerRepository.queryCustomerByEmail(email, partName);

    }
}
