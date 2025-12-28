package com.example.turfMgmt.Repository;

import com.example.turfMgmt.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByUsernameOrEmail(String username,String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByMobile(String mobile);
}
