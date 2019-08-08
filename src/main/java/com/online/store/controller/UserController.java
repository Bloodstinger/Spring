package com.online.store.controller;

import com.online.store.model.Item;
import com.online.store.model.User;
import com.online.store.service.ItemService;
import com.online.store.service.ShoppingCartService;
import com.online.store.service.UserService;
import com.online.store.utils.ConfirmCode;
import com.online.store.utils.PriceCount;
import com.online.store.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private ItemService itemService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public UserController(UserService userService, ItemService itemService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.itemService = itemService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/buy")
    public String buyPostHandler(@RequestParam(name = "id") String id,
                                 Model model) {
        User user = userService.getUserByEmail("test@localhost").get();
        if (shoppingCartService.getCartByUser(user) == null) {
            shoppingCartService.createShoppingCart(user);
        }
        Long itemId = Long.parseLong(id);
        Item itemToAdd = itemService.getItem(itemId);
        shoppingCartService.addItem(user, itemToAdd);
        model.addAttribute("count", shoppingCartService.getSize());
        return "redirect:/user/items";
    }

    @GetMapping("/checkout")
    public String checkoutGetHandler(Model model) {
        List<Item> items = shoppingCartService.getAll();
        double totalPrice = PriceCount.getPrice(items);
        model.addAttribute("allItems", items);
        model.addAttribute("totalPrice", totalPrice);
        return "user_checkout";
    }

    @PostMapping("/checkout")
    public String checkoutPostHandler(Model model) {
        User user = userService.getUserByEmail("test@localhost").get();
        List<Item> items = shoppingCartService.getAll();
        double totalPrice = PriceCount.getPrice(items);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("email", user.getEmail());
        return "user_confirmation";
    }

    @GetMapping("/confirmation")
    public String confirmationGetHandler(@RequestParam(name = "email") String email,
                                         @RequestParam(name = "address") String address,
                                         @RequestParam(name = "totalPrice") String totalPrice,
                                         Model model) {
        String confirmCode = ConfirmCode.code();
        Double price = Double.parseDouble(totalPrice);
        new Thread(() -> SendEmail.sendCode(email, confirmCode, price)).start();
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("codeGen", confirmCode);
        return "user_confirmation";
    }

    @PostMapping("/confirmation")
    public String confirmationPostHandler(@RequestParam(name = "email", required = false) String email,
                                          @RequestParam(name = "address", required = false) String address,
                                          @RequestParam(name = "codeGen") String confirmCode,
                                          @RequestParam(name = "code") String codeInput,
                                          Model model) {
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        if (confirmCode.equals(codeInput)) {
            model.addAttribute("success", "Code correct!");
            return "user_confirmation";
        } else {
            model.addAttribute("success", "Invalid code. Try again");
            return "user_confirmation";
        }
    }

    @GetMapping("/items")
    @PostMapping("/items")
    public String itemsHandler(Model model) {
        List<Item> items = itemService.getAll();
        model.addAttribute("allItems", items);
        return "user_items";
    }
}
