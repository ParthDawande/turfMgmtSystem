package com.example.turfMgmt.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String mobile;

    private String address;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
