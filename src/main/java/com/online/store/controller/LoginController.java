package com.online.store.controller;

import com.online.store.model.User;
import com.online.store.service.ItemService;
import com.online.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Controller
public class LoginController {

    private UserService userService;
    private ItemService itemService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    public LoginController(UserService userService,
                           ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String loginGetHandler(Model model, @AuthenticationPrincipal User user) {
        return "index";
    }

    @PostMapping("/")
    public ModelAndView loginPostHandler(@SessionAttribute User user) {
        ModelAndView model = new ModelAndView();
        if (user.getRole().equalsIgnoreCase("admin")) {
            model.setViewName("users");
        } else {
            model.setViewName("user_items");
        }
        return model;
    }

    @PostMapping("/login")
    @GetMapping("/login")
    public String logingPost(@AuthenticationPrincipal User user) {
        if (user != null) {
            if (user.getRole().equalsIgnoreCase("admin")) {
                return "users";
            } else {
                return "user_items";
            }
        } else {
            return "index";
        }
    }

    @PostConstruct
    public void init() throws NoSuchProviderException, NoSuchAlgorithmException {
        userService.addUser("root@localhost", "root", "ROLE_ADMIN");
        userService.addUser("test@localhost", "test", "ROLE_USER");
        itemService.addItem("Knife", "Sharp one.", 15.5);
        itemService.addItem("Form", "Sharp as well.", 73.2);
    }
}
