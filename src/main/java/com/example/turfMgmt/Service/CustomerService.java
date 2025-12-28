package com.example.turfMgmt.Service;

import com.example.turfMgmt.Entity.Customer;

public interface CustomerService {

    Customer getCustomerById(Long id);
    Customer getLoggedInCustomer();
}
