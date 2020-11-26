package com.unibuc.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
    private Long id;
    private String title;
    private Integer size;
}
