package com.unibuc.demo.controller;

import com.unibuc.demo.dto.ShopDTO;
import com.unibuc.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<ShopDTO> shopDTOList = shopService.getAllShop();
        model.addAttribute("shopDTOList", shopDTOList);
        return "view-shops";
    }

}
