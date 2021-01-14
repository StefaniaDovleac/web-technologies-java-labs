package com.unibuc.demo.dto;

import com.unibuc.demo.validators.PasswordMatches;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@PasswordMatches
public class UserDTO {
    private Integer id;
    @NotNull
    private String userName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    private String matchingPassword;
    private Boolean isAdmin; 
}
