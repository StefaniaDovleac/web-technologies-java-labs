package com.unibuc.demo.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {
    private Long id;
    private String title;
    private Integer size;
}
