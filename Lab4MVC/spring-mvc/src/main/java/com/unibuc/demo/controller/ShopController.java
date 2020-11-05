package com.unibuc.demo.controller;

import com.unibuc.demo.dto.ShopDTO;
import com.unibuc.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{CUI}")
    public String getShopByCUI(@PathVariable("CUI") String CUI, Model model) {
        ShopDTO shopDTO = this.shopService.getShopByCUI(CUI);
        model.addAttribute("shopDTO", shopDTO);
        return "view-shop";
    }

    @GetMapping("/add")
    public String addShop(ShopDTO shopDTO) {
        return "add-shop";
    }

    @PostMapping("/create")
    public String createShop(ShopDTO shopDTO, Model model) {
        this.shopService.createShop(shopDTO);
        model.addAttribute("shopDTOList", shopService.getAllShop());
        return "view-shops";
    }

//    @PutMapping("/update")
//    public String updateShop (ShopDto shopDto, Model model){
//
//    }

}
