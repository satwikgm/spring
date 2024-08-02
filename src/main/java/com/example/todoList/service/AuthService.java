package com.example.todoList.service;

import com.example.todoList.entity.Customer;
import com.example.todoList.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private CustomerRepository customerRepository;

    public boolean register(String username, String password) {
        if(customerRepository.findByUsername(username).isPresent()) {
            return false; // username already exists
        }
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword(password);
        customerRepository.save(customer);
        return true;
    }

    public boolean login(String username, String password) {
        return customerRepository.findByUsername(username)
                .map(customer -> customer.getPassword().equals(password))
                .orElse(false);
    }
}
