package com.unibuc.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductDTO {
    private String name;
    private String id;
    private int price;
    private int rating;
}
