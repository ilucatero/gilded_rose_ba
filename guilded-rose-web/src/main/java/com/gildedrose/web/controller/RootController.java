package com.gildedrose.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class RootController {

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("message", "Inventory");
        return "index";
    }

}
