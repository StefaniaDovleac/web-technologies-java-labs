package com.unibuc.demo.repository;

import com.unibuc.demo.domain.Product;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements InitializingBean {
    private final List<Product> productList = new ArrayList<>();
    @Value("${products.number}")
    private int numberOfProducts;
    @Value("${products.prefixId}")
    private String prefixId;

    public List<Product> getAllProducts(){
        return productList;
    }

    public Optional<Product> getProductById(String id) {
        return productList.stream().filter(product -> product.getId().equals(id)).findFirst();
    }

    private void createAndSave(String name, String id, int price, int rating){
        Product newProduct = Product.builder()
                .name(name)
                .id(id)
                .price(price)
                .rating(rating)
                .build();
        productList.add(newProduct);
    }

    private void setContextForProductRepository(){
        for(int i= 0; i< numberOfProducts; i++){
        createAndSave("Name" + i, prefixId + i, (int) (Math.random() * 100), i);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setContextForProductRepository();
    }
}
