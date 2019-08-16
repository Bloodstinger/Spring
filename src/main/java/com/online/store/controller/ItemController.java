package com.online.store.controller;

import com.online.store.model.Item;
import com.online.store.service.ItemService;
import com.online.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/admin/itemEdit")
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

    @PostMapping("/admin/itemEdit")
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

    @PostMapping("/admin/itemDelete")
    public String itemDeletePostHandler(@RequestParam(name = "delete") String id) {
        Long itemId = Long.valueOf(id);
        Item itemToDelete = itemService.getItem(itemId);
        itemService.removeItem(itemToDelete);
        return "redirect:/admin/items";
    }

    @GetMapping("/admin/additem")
    public String addItemGetHandler() {
        return "additem";
    }

    @PostMapping("/admin/additem")
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

    @GetMapping("/admin/items")
    @PostMapping("/admin/items")
    public String itemsGetController(Model model) {
        List<Item> allItems = itemService.getAll();
        model.addAttribute("allItems", allItems);
        return "admin_items";
    }

    @GetMapping("/user/items")
    @PostMapping("/user/items")
    public String itemsHandler(@RequestParam(name = "count", required = false,
            defaultValue = "0") String itemCount,
                               Model model) {
        List<Item> items = itemService.getAll();
        model.addAttribute("count", itemCount);
        model.addAttribute("allItems", items);
        return "user_items";
    }
}
