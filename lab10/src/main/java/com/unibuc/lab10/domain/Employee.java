package com.unibuc.lab10.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    private Long id;
    private String name;
    private Double salary;
    private Address address;
    private Boolean promoted;
}
