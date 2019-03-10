package com.gildedrose.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Inventory of Items");
        return "index";
    }

}
