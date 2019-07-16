package com.github.department.controller;

import com.github.department.entity.User;
import com.github.department.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String welcomePage() {
        return "register";
    }

    @PostMapping("/register")
    public String signUp(User user, Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("message", "Такой аккаунт уже зарегистрирован!");
            return "register";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activateAccount(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("success", "Аккаунт успешно активирован!");
        } else {
            model.addAttribute("failure", "Аккаунт уже активирован!");
        }

        return "login";
    }
}
