package com.online.store.controller;

import com.online.store.model.User;
import com.online.store.service.ItemService;
import com.online.store.service.ShoppingCartService;
import com.online.store.service.UserService;
import com.online.store.utils.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Optional;

@Controller("/")
public class LoginController {

    private UserService userService;
    private ItemService itemService;
    private ShoppingCartService shoppingCartService;

    @Autowired
    public LoginController(UserService userService,
                           ItemService itemService,
                           ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.itemService = itemService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String login(Model model) {
        return "index";
    }

    @PostMapping
    public String login(@RequestParam(name = "email") String email,
                        @RequestParam(name = "password") String password,
                        Model model) {
        Optional<User> optUser = userService.getUserByEmail(email);
        if (optUser.isPresent()) {
            User user = optUser.get();
            byte[] salt = user.getSalt();
            String passwordForCheck = HashUtil.getSecurePassword(password, salt);
            if (user.getPassword().equals(passwordForCheck)) {
                if (user.getRole().equals("admin")) {
                    return "redirect:/admin/users";
                } else {
                    return "redirect:/user/items";
                }
            } else {
                model.addAttribute("email", email);
                model.addAttribute("isValid", "Username or password are not equal");
                return "index";
            }
        }
        model.addAttribute("email", email);
        model.addAttribute("isValid", "Username or password are not equal");
        return "index";
    }

    @GetMapping("/logout")
    @PostMapping("/logout")
    public String logoutHandler() {
        return "index";
    }

    @PostConstruct
    public void init() throws NoSuchProviderException, NoSuchAlgorithmException {
        byte[] salt1 = HashUtil.getSalt();
        byte[] salt2 = HashUtil.getSalt();
        userService.addUser("root@localhost", HashUtil.getSecurePassword("root", salt1),
                "admin", salt1);
        userService.addUser("test@localhost", HashUtil.getSecurePassword("test", salt2),
                "user", salt2);
        itemService.addItem("Knife", "Sharp one.", 15.5);
        itemService.addItem("Form", "Sharp as well.", 73.2);
    }
}
