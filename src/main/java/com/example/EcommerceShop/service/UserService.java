package com.example.EcommerceShop.service;

import com.example.EcommerceShop.entity.Customer;
import com.example.EcommerceShop.repositiory.UserRepository;
import com.example.EcommerceShop.response.RegistrationResult;
import com.example.EcommerceShop.exception.AppException;
import com.example.EcommerceShop.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        Optional<Customer> customerExist = customerRepository.findByEmail(customer.getEmail());
        if (customerExist.isPresent()) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        customer.setName(customer.getName());
        customer.setAddress(customer.getAddress());
        customer.setPhone(customer.getPhone());
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
            customer.setDateCreated(new java.util.Date()); // set the current date
            customerRepository.save(customer);
            registrationResult.setCustomer(customer);

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