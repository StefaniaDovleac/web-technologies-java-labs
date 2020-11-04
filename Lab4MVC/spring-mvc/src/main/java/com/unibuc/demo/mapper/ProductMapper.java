package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Product;
import com.unibuc.demo.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product convertProductFrom(ProductDTO productDTO){
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .rating(productDTO.getRating())
                .build();
    }

    public ProductDTO convertProductDTOFrom(Product product){
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .rating(product.getRating())
                .build();
    }
}
