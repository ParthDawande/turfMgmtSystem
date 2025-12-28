package com.example.turfMgmt.Service;

import com.example.turfMgmt.DTOs.LoginRequestDTO;
import com.example.turfMgmt.DTOs.SignupRequestDTO;

public interface AuthService {

    void signup(SignupRequestDTO request);
    String login(LoginRequestDTO login);

}
