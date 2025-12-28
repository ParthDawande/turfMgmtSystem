package com.example.turfMgmt.Service.Impl;

import com.example.turfMgmt.DTOs.LoginRequestDTO;
import com.example.turfMgmt.DTOs.SignupRequestDTO;
import com.example.turfMgmt.Entity.Customer;
import com.example.turfMgmt.Entity.Role;
import com.example.turfMgmt.Exception.BadRequestException;
import com.example.turfMgmt.Repository.CustomerRepository;
import com.example.turfMgmt.Service.AuthService;
import com.example.turfMgmt.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(CustomerRepository customerRepository,JwtUtil jwtUtil){
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void signup(SignupRequestDTO request) {
        if(customerRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException("Email already exists");
        }

        if(customerRepository.existsByUsername(request.getUsername())){
            throw new BadRequestException("Username already exists");
        }

        if(customerRepository.existsByMobile(request.getMobile())){
            throw new BadRequestException("Mobile already exists");
        }

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setMobile(request.getMobile());
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        customer.setUsername(request.getUsername());
        customer.setPasswordHash(encoder.encode(request.getPassword()));
        customer.setRole(Role.ROLE_USER);

        customerRepository.save(customer);
    }

    @Override
    public String login(LoginRequestDTO login) {

        Customer customer = customerRepository.findByUsernameOrEmail(
                login.getUsernameOrEmail(),
                login.getUsernameOrEmail()
        ).orElseThrow(()-> new RuntimeException(("Invalid username/email")));

        if(!encoder.matches(login.getPassword(), customer.getPasswordHash())){
            throw new BadRequestException("Invalid Password");
        }

        return jwtUtil.generateToken(customer.getId(),customer.getUsername(),customer.getRole().name());
    }
}
