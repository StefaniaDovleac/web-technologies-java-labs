package com.unibuc.demo.dto;

import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    @NotNull
    @Size(min = 13, max = 13)
    @Pattern(regexp = "^[0-9]*$")
    private String CNP;
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    private String birthDate;
    @Email
    private String email;
}
