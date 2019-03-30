package com.gildedrose.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RootController {

    // Note: at the moment there is no other page to go, but in case any the root path should forward to main page
    // TODO: implement security, authentication (OAUTH jtw/sessions) & redirection to login page

    @GetMapping("/")
    public String index(Model model) {
        // TODO: to implement internationalization, get messages and texts from properties files (e.g. messages-fr.properties)

        model.addAttribute("message", "Inventory of Items");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(value = "error", required = false) String isError) {
        model.addAttribute("error", isError != null ? isError  : false);
        return "login";
    }

    @PostMapping("/perform_login")
    public RedirectView performLogin(Model model) {
        return new RedirectView("/");
    }

}
