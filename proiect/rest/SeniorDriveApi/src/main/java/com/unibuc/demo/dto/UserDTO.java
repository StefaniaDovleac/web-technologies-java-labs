package com.unibuc.demo.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@PasswordMatches
public class UserDTO {
    private Integer id;
    @NotBlank
    private String userName;
    @NotBlank
    @Email
    private String email;
    private Boolean isAdmin;
    @NotBlank
    private String password;
}
