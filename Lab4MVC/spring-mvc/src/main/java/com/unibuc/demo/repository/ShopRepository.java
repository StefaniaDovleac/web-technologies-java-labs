package com.unibuc.demo.repository;


import com.unibuc.demo.domain.Product;
import com.unibuc.demo.domain.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class ShopRepository {

    private final ProductRepository productRepository;
    private final List<Shop> shopList = new ArrayList<>();

    @Value("#100")
    private String idPrefix;

    @Value("${shop.one.start.range}")
    private int shopOneStartRange;
    @Value("${shop.one.end.range}")
    private int shopOneEndRange;
    @Value("${shop.two.start.range}")
    private int shopTwoStartRange;
    @Value("${shop.two.end.range}")
    private int shopTwoEndRange;

    @Autowired
    public ShopRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Shop> getAll() {
        return shopList;
    }

    public Optional<Shop> getByCUI(String CUI) {
        return shopList.stream().filter(shop -> shop.getCUI().equals(CUI)).findFirst();
    }

    private List<Product> retrieveProductsFromProductRepository(int startRange, int endRange) {
        List<Product> products = new ArrayList<>();
        for (int i = startRange; i < endRange; i++) {
            Optional<Product> productById = productRepository.getProductById(idPrefix + i);
            productById.ifPresent(products::add);
        }
        return products;
    }

    private void createAndSave(String name, String CUI, List<Product> products) {
        Shop shop = Shop.builder()
                .name(name)
                .CUI(CUI)
                .productList(products)
                .build();

        shopList.add(shop);
    }

    private void setUpContextForShopRepository() {
        createAndSave("Lidl", "RO123", retrieveProductsFromProductRepository(shopOneStartRange, shopOneEndRange));
        createAndSave("Kaufland", "RO456", retrieveProductsFromProductRepository(shopTwoStartRange, shopTwoEndRange));
    }

    @PostConstruct
    public void afterPropertiesSettings() {
        setUpContextForShopRepository();
    }


}
