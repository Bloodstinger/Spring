package com.online.store.controller;

import com.online.store.model.Item;
import com.online.store.model.Order;
import com.online.store.model.ShoppingCart;
import com.online.store.model.User;
import com.online.store.service.ConfirmationCodeService;
import com.online.store.service.OrderService;
import com.online.store.service.ShoppingCartService;
import com.online.store.service.UserService;
import com.online.store.utils.ConfirmCodeUtil;
import com.online.store.utils.SendEmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes({"codeGen", "code"})
public class OrderController {

    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final ConfirmationCodeService confirmationCodeService;
    private final UserService userService;

    @Autowired
    public OrderController(ShoppingCartService shoppingCartService, OrderService orderService,
                           ConfirmationCodeService confirmationCodeService, UserService userService) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.confirmationCodeService = confirmationCodeService;
        this.userService = userService;
    }

    @GetMapping("/user/checkout")
    public String checkoutGetHandler(@AuthenticationPrincipal User user, Model model) {
        List<Item> items = shoppingCartService.getAll(user);
        double totalPrice = shoppingCartService.totalPriceCount(items);
        model.addAttribute("allItems", items);
        model.addAttribute("totalPrice", totalPrice);
        return "user_checkout";
    }

    @PostMapping("/user/checkout")
    public String checkoutPostHandler(@AuthenticationPrincipal User user, Model model) {
        List<Item> items = shoppingCartService.getAll(user);
        double totalPrice = shoppingCartService.totalPriceCount(items);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("email", user.getEmail());
        return "user_confirmation";
    }

    @GetMapping("/user/confirmation")
    public String confirmationGetHandler(@RequestParam(name = "email") String email,
                                         @RequestParam(name = "address") String address,
                                         @RequestParam(name = "totalPrice") String totalPrice,
                                         @AuthenticationPrincipal User user,
                                         Model model) {
        String confirmCode = ConfirmCodeUtil.code();
        Double price = Double.parseDouble(totalPrice);
        new Thread(() -> SendEmailUtil.sendCode(email, confirmCode, price)).start();
        confirmationCodeService.addCode(user, confirmCode);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        model.addAttribute("totalPrice", totalPrice);
        return "user_confirmation";
    }

    @PostMapping("/user/confirmation")
    public String confirmationPostHandler(@RequestParam(name = "email", required = false) String email,
                                          @RequestParam(name = "address", required = false) String address,
                                          @RequestParam(name = "code", required = false) String codeInput,
                                          @AuthenticationPrincipal User user,
                                          Model model) {
        String confirmCode = confirmationCodeService.getCode(user);
        model.addAttribute("email", email);
        model.addAttribute("address", address);
        if (confirmCode.equals(codeInput)) {
            model.addAttribute("success", "Code correct!");
            User persistUser = userService.getUserByEmail(user.getEmail()).get();
            ShoppingCart shoppingCart = shoppingCartService.getCartByUser(persistUser);
            Order order = new Order(user, shoppingCart);
            orderService.addOrder(user, shoppingCart);
            return "user_confirmation";
        } else {
            model.addAttribute("success", "Invalid code. Try again");
            return "user_confirmation";
        }
    }
}
