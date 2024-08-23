package com.example.EcommerceShop.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Date;
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId")

    private int customerId;

    @NotBlank(message = "Phải nhập tên") // Dùng để kiểm tra nếu người dùng không nhập báo lỗi
    private String name;

    private String email;

    private String password;

    @NotBlank(message = "Phải nhập địa chỉ")
    private String address;

    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại không hợp lệ")
    @NotBlank(message = "Phải nhập số điện thoại")
    private String phone;

    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    // Default constructor
    public Customer() {
    }

    // Constructor with parameters
    public Customer(String name, String email, String password, String address, String phone, Date dateCreated) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.dateCreated = dateCreated;
    }

    // Getters and setters


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
