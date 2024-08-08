package com.example.EcommerceShop.service;

import com.example.EcommerceShop.entity.Customer;
import com.example.EcommerceShop.repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository customerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean register(Customer customer) {
        StringBuilder errorMessage = new StringBuilder();
        boolean isError = false;
        Optional<Customer> customerExist = customerRepository.findByEmail(customer.getEmail());

        if (customerExist.isPresent()) {
            return false;
        }
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        customer.setDateCreated(new java.util.Date()); // set the current date
        customerRepository.save(customer);
        return true;
    }

    public Optional<Customer> login(String username, String password) {
        Optional<Customer> userOpt = customerRepository.findByEmail(username);
        if (userOpt.isPresent() && bCryptPasswordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
}