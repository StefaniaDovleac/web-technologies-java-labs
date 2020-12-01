package com.unibuc.demo.mapper;

import com.unibuc.demo.domain.Product;
import com.unibuc.demo.domain.Shop;
import com.unibuc.demo.dto.ProductDTO;
import com.unibuc.demo.dto.ShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShopMapper {
    @Autowired
    private ProductMapper productMapper;

    public Shop convertShopFrom(ShopDTO shopDTO) {
        List<Product> productListConverted = new ArrayList<>();
        if (shopDTO.getProductList() != null) {
            shopDTO.getProductList().forEach(productDTO -> productListConverted.add(productMapper.convertProductFrom(productDTO)));
        }
        return Shop.builder()
                .CUI(shopDTO.getCUI())
                .name(shopDTO.getName())
                .productList(productListConverted)
                .build();
    }

    public ShopDTO convertShopDTOFrom(Shop shop) {
        List<ProductDTO> productListDTOConverted = new ArrayList<>();
        shop.getProductList().forEach(product -> productListDTOConverted.add(productMapper.convertProductDTOFrom(product)));

        return ShopDTO.builder()
                .CUI(shop.getCUI())
                .name(shop.getName())
                .productList(productListDTOConverted)
                .build();
    }
}
