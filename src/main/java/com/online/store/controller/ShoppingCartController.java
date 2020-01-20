package com.online.store.controller;

import com.online.store.model.Item;
import com.online.store.model.User;
import com.online.store.service.ItemService;
import com.online.store.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ItemService itemService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ItemService itemService) {
        this.shoppingCartService = shoppingCartService;
        this.itemService = itemService;
    }

    @PostMapping("/user/buy")
    public String buyPostHandler(@RequestParam(name = "id") String id,
                                 @AuthenticationPrincipal User user,
                                 Model model) {
        if (shoppingCartService.getCartByUser(user) == null) {
            shoppingCartService.createShoppingCart(user);
        }
        Long itemId = Long.parseLong(id);
        Item itemToAdd = itemService.getItem(itemId);
        shoppingCartService.addItem(user, itemToAdd);
        model.addAttribute("count", shoppingCartService.getSize());
        return "redirect:/user/items";
    }
}
