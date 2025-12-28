package com.example.turfMgmt.Controller;

import com.example.turfMgmt.DTOs.AuthResponseDTO;
import com.example.turfMgmt.DTOs.LoginRequestDTO;
import com.example.turfMgmt.DTOs.SignupRequestDTO;
import com.example.turfMgmt.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> signup(@Valid @RequestBody SignupRequestDTO request){

        authService.signup(request);

        return new ResponseEntity<>(new AuthResponseDTO("SignUp Successful"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){

        String token = authService.login(request);

        return new ResponseEntity<>(new AuthResponseDTO(token),HttpStatus.OK);
    }


}
