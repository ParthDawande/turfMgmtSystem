package com.example.turfMgmt.Service.Impl;

import com.example.turfMgmt.Entity.Customer;
import com.example.turfMgmt.Exception.ResourceNotFoundException;
import com.example.turfMgmt.Repository.CustomerRepository;
import com.example.turfMgmt.Service.CustomerService;
import com.example.turfMgmt.util.SecurityUtil;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));
    }

    @Override
    public Customer getLoggedInCustomer() {
        Long userId = SecurityUtil.getCurrentUserId();

        return customerRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Logged-in customer not found"));
    }
}
