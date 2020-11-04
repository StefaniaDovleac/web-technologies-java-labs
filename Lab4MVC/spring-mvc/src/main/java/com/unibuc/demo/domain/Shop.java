package com.unibuc.demo.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shop {
    private String name;
    private String CUI;
    private List<Product> productList;
}
