package com.online.store.controller;

import com.online.store.model.User;
import com.online.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/admin/register")
    public String registerHandler(@RequestParam(name = "email", required = false) String email,
                                  @RequestParam(name = "password", required = false) String password,
                                  @RequestParam(name = "repeatPassword", required = false) String rPassword,
                                  @RequestParam(name = "role", required = false) String role,
                                  Model model) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (!userService.isParamEmpty(email, password, rPassword, role)) {
            model.addAttribute("isValid", "All fields cannot be empty!");
            model.addAttribute("checkEmail", email);
        } else {
            if (password.equals(rPassword)) {
                if (userService.inDatabase(email)) {
                    model.addAttribute("isValid", "User " + email + " already registered!");
                } else {
                    userService.addUser(email, password, role);
                    model.addAttribute("isValid", "User " + email + " registered!");
                }
            } else {
                model.addAttribute("checkEmail", email);
                model.addAttribute("isValid", "The password is not valid, try again");
            }
        }
        return "register";
    }

    @GetMapping("/admin/userEdit")
    public String userEditGetHandler(@RequestParam(name = "id") String id,
                                     Model model) {
        Long userId = Long.valueOf(id);
        model.addAttribute("email", userService.getUserById(userId).get().getEmail());
        return "userEdit";
    }

    @PostMapping("/admin/userEdit")
    public String userEditPostHandler(@RequestParam(name = "id", required = false) String id,
                                      @RequestParam(name = "email", required = false) String email,
                                      @RequestParam(name = "password", required = false) String password,
                                      @RequestParam(name = "repeatPassword", required = false) String rPassword,
                                      @RequestParam(name = "role", required = false) String role,
                                      @AuthenticationPrincipal User user,
                                      Model model) throws NoSuchProviderException, NoSuchAlgorithmException {
        if (!userService.isParamEmpty(email, password, rPassword, role)) {
            model.addAttribute("isValid", "All fields cannot be empty");
            model.addAttribute("email", email);
            return "userEdit";
        } else {
            if (password.equals(rPassword)) {
                userService.updateUser(email, password, role);
                return "redirect:/admin/users";
            } else {
                model.addAttribute("email", email);
                model.addAttribute("isValid", "The password is not valid, try again.");
                return "userEdit";
            }
        }
    }

    @PostMapping("/admin/userDelete")
    public String deletePostHandler(@RequestParam(name = "delete", required = false) String id,
                                    Model model) {
        Long userId = Long.valueOf(id);
        User userToDelete = userService.getUserById(userId).get();
        userService.removeUser(userToDelete);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users")
    public String usersList(Model model, @AuthenticationPrincipal User user) {
        List<User> allUsers = userService.getAll();
        model.addAttribute("allUsers", allUsers);
        return "users";
    }
}
