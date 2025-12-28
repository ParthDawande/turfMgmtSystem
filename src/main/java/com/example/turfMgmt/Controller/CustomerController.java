package com.example.turfMgmt.Controller;

import com.example.turfMgmt.Config.SecurityConfig;
import com.example.turfMgmt.Entity.Customer;
import com.example.turfMgmt.Service.CustomerService;
import com.example.turfMgmt.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/me")
    public ResponseEntity<Customer> getMyProfile() {
        return ResponseEntity.ok(customerService.getLoggedInCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
}
