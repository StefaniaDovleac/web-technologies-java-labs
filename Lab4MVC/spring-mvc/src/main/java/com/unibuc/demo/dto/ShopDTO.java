package com.unibuc.demo.dto;

import com.unibuc.demo.domain.Product;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopDTO {
    private String name;
    private String CUI;
    private List<ProductDTO> productList;
}
