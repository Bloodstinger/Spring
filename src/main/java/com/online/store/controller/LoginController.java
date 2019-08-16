package com.online.store.controller;

import com.online.store.model.User;
import com.online.store.service.ItemService;
import com.online.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Controller
public class LoginController {

    private UserService userService;
    private ItemService itemService;

    @Autowired
    public LoginController(UserService userService,
                           ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String loginGetHandler() {
        return "index";
    }

    @PostMapping("/")
    public String loginPostHandler() {
        return "index";
    }

    @PostMapping("/roleRedirect")
    @GetMapping("/roleRedirect")
    public String loginPost(@AuthenticationPrincipal User user) {
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("role_admin")) {
                return "redirect:/admin/users";
            } else {
                return "redirect:/user/items";
            }
        } else {
            return "index";
        }
    }

    @PostConstruct
    public void init() throws NoSuchProviderException, NoSuchAlgorithmException {
        userService.addUser("root@localhost", "root", "ROLE_ADMIN");
        userService.addUser("test@localhost", "test", "ROLE_USER");
        itemService.addItem("Knife", "Sharp one.", 15);
        itemService.addItem("Form", "Sharp as well.", 73);
    }
}
