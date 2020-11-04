package com.unibuc.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Product {
    private String name;
    private String id;
    private int price;
    private int rating;
}
