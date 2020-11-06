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
        ShopDTO savedShopDTO = this.shopService.getShopByCUI(shopDTO.getCUI());
        model.addAttribute("shopDTO", savedShopDTO);
        return "view-shop";
    }

    @GetMapping("/update/{CUI}")
    public String updateShop(@PathVariable("CUI") String CUI, ShopDTO shopDTOEdited, Model model) {
//        this.shopService.updateShop(CUI, shopDTO);
        ShopDTO shopDTO = this.shopService.getShopByCUI(CUI);
        model.addAttribute("shopDTO", shopDTO);
        System.out.println(CUI);
        return "edit-shop";
    }

    @GetMapping("/update")
    public String update(ShopDTO shopDTOEdited, Model model) {
//        this.shopService.updateShop(CUI, shopDTO);
//        ShopDTO shopDTO = this.shopService.getShopByCUI(CUI);
//        model.addAttribute("shopDTO", shopDTO);
//        System.out.println(CUI);
        return "";
    }

    @RequestMapping(value = "add-product/{CUI}")
    public void addProduct(@PathVariable("CUI") String CUI) {
        System.out.println("add");
        this.shopService.addNewProduct(CUI);
    }


    //Tema:
    //1.Implement the createShop method and the add-shop view.
    //2.Create an end-point to update(put) the shop (including adding new products) .

}
