package com.example.EcommerceShop.service;

import com.example.EcommerceShop.entity.Customer;
import com.example.EcommerceShop.repositiory.UserRepository;
import com.example.EcommerceShop.response.RegistrationResult;
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

    public RegistrationResult register(Customer customer) {
        RegistrationResult registrationResult = new RegistrationResult();
        StringBuilder errorMessage = new StringBuilder();
        boolean isError = false;
        if (customer.getName() == null || customer.getName().isEmpty()) {
            errorMessage.append("Name is missing. ");
            isError = true;
        }
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            errorMessage.append("Email is missing. ");
            isError = true;
        }
        if (customer.getPassword() == null || customer.getPassword().isEmpty()) {
            errorMessage.append("Password is missing. ");
            isError = true;
        }
        if (customer.getAddress() == null || customer.getAddress().isEmpty()) {
            errorMessage.append("Address is missing. ");
            isError = true;
        }
        if (customer.getPhone() == null || customer.getPhone().isEmpty()) {
            errorMessage.append("Phone is missing. ");
            isError = true;
        }


        Optional<Customer> customerExist = customerRepository.findByEmail(customer.getEmail());
        if (customerExist.isPresent()) {
            errorMessage.append("Email already exists ");
            isError = true;

        }

        registrationResult.setError(isError);
        registrationResult.setErrorMessage(errorMessage.toString());
        if(!isError){
            customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
            customer.setDateCreated(new java.util.Date()); // set the current date
            customerRepository.save(customer);
            registrationResult.setCustomer(customer);
        }
        return registrationResult;
    }

    public Optional<Customer> login(String username, String password) {
        Optional<Customer> userOpt = customerRepository.findByEmail(username);
        if (userOpt.isPresent() && bCryptPasswordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }
}