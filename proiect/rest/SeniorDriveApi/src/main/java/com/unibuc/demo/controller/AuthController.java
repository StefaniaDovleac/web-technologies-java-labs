package com.unibuc.demo.controller;

import com.unibuc.demo.dto.LoginRequestDTO;
import com.unibuc.demo.dto.LoginResponseDTO;
import com.unibuc.demo.auth.MyUserDetailsService;
import com.unibuc.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUserName(), loginRequestDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorent username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDTO.getUserName());

        final String token = jwtUtil.generateToken(userDetails);
        LoginResponseDTO response = new LoginResponseDTO(token);
        response.setUserName(loginRequestDTO.getUserName());
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
