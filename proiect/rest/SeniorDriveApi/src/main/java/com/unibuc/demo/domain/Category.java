package com.unibuc.demo.domain;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    private Integer id;
    @NotNull
    @Size(max = 45)
    private String name;
    @Size(max = 255)
    private String description;
}
