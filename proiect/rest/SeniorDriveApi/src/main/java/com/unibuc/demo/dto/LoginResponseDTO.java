package com.unibuc.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO {
    private final String token;

    private String userName;

    public LoginResponseDTO(String token) {
        this.token = token;
    }
}
