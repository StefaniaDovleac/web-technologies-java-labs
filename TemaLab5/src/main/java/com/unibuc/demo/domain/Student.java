package com.unibuc.demo.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private String CNP;
    private String name;
    private String birthDate;
    private String email;
}
