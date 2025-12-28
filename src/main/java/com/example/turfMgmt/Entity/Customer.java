package com.example.turfMgmt.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;

@Entity
@Table(
        name="customers",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "mobile")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobile;

    private String address;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt = LocalDateTime.now();

}
