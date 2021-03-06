package com.online.store.controller;

import com.online.store.model.Item;
import com.online.store.model.User;
import com.online.store.service.ItemService;
import com.online.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public AdminController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/users")
    public String usersList(Model model, @AuthenticationPrincipal User user) {
        List<User> allUsers = userService.getAll();
        model.addAttribute("allUsers", allUsers);
        return "users";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
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

    @GetMapping("/userEdit")
    public String userEditGetHandler(@RequestParam(name = "id") String id,
                                     Model model) {
        Long userId = Long.valueOf(id);
        model.addAttribute("email", userService.getUserById(userId).get().getEmail());
        return "userEdit";
    }

    @PostMapping("/userEdit")
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

    @PostMapping("/userDelete")
    public String deletePostHandler(@RequestParam(name = "delete", required = false) String id,
                                    Model model) {
        Long userId = Long.valueOf(id);
        User userToDelete = userService.getUserById(userId).get();
        userService.removeUser(userToDelete);
        return "redirect:/admin/users";
    }

    @GetMapping("/itemEdit")
    public String itemEditGetHandler(@RequestParam(name = "id") String id,
                                     Model model) {
        Long itemId = Long.valueOf(id);
        Item item = itemService.getItem(itemId);
        model.addAttribute("id", id);
        model.addAttribute("name", item.getName());
        model.addAttribute("description", item.getDescription());
        model.addAttribute("price", item.getPrice());
        return "itemEdit";
    }

    @PostMapping("/itemEdit")
    public String itemEditPostHandler(@RequestParam(name = "id") String id,
                                      @RequestParam(name = "name") String name,
                                      @RequestParam(name = "description") String description,
                                      @RequestParam(name = "price") String price) {
        Long itemId = Long.valueOf(id);
        Double itemPrice = Double.valueOf(price);
        Item itemToEdit = itemService.getItem(itemId);
        itemToEdit.setName(name);
        itemToEdit.setDescription(description);
        itemToEdit.setPrice(itemPrice);
        itemService.update(itemToEdit);
        return "redirect:/admin/items";
    }

    @PostMapping("/itemDelete")
    public String itemDeletePostHandler(@RequestParam(name = "delete") String id) {
        Long itemId = Long.valueOf(id);
        Item itemToDelete = itemService.getItem(itemId);
        itemService.removeItem(itemToDelete);
        return "redirect:/admin/items";
    }

    @GetMapping("/additem")
    public String addItemGetHandler() {
        return "additem";
    }

    @PostMapping("/additem")
    public String addItemPostHandler(@RequestParam(name = "name", required = false) String name,
                                     @RequestParam(name = "description", required = false) String description,
                                     @RequestParam(name = "price", required = false) String price,
                                     Model model) {
        double itemPrice = 0;
        if (!price.isEmpty()) {
            itemPrice = Double.parseDouble(price);
        }
        if (name.isEmpty() || description.isEmpty() || price.isEmpty() || itemPrice < 0) {
            model.addAttribute("isValid", "All fields must be present and price must be greater " +
                    "than 0.");
            return "additem";
        } else {
            itemService.addItem(name, description, itemPrice);
            return "redirect:additem";
        }
    }

    @GetMapping("/items")
    @PostMapping("/items")
    public String itemsGetController(Model model) {
        List<Item> allItems = itemService.getAll();
        model.addAttribute("allItems", allItems);
        return "admin_items";
    }

}
