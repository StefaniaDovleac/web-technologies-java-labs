package com.unibuc.demo.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {
    @NotNull
    private Long id;
    @Size(min=1)
    private String title;
    private Integer size;
}
