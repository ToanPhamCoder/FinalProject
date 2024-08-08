package com.example.EcommerceShop.response;

import com.example.EcommerceShop.entity.Customer;

public class RegistrationResult {
    private boolean isError;
    private String errorMessage;
    private Customer customer;

    public RegistrationResult(){

    }
    public RegistrationResult(boolean isError, String errorMessage, Customer customer) {
        this.isError = isError;
        this.errorMessage = errorMessage;
        this.customer = customer;
    }

    public boolean getIsError() {
        return isError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}