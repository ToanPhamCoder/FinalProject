package com.example.EcommerceShop.repositiory;

import com.example.EcommerceShop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
}