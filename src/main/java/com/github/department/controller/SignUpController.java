package com.github.department.controller;

import com.github.department.entity.User;
import com.github.department.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String welcomePage() {
        return "register";
    }

    @PostMapping("/register")
    public String signUp(@Valid User user, BindingResult bindingResult, Model model) {
        if (user.getPassword() != null && user.getPasswordConfirm() != null &&
                !user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("user", user);
            model.addAttribute("passwordError", "Пароли не совпадают!");

            return "register";
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.addAttribute("user", user);
            model.mergeAttributes(errors);

            return "register";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("user", null);
            model.addAttribute("accountError", "Данный логин занят!");

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
