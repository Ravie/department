package com.github.department.controller;

import com.github.department.entity.Role;
import com.github.department.entity.User;
import com.github.department.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class SignUpController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/register")
    public String welcomePage() {
        return "register";
    }

    @PostMapping("/register")
    public String signUp(User user, Model model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User Exists!");
            return "register";
        }

        user.setActive(true);
        user.setAccessLevel(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
