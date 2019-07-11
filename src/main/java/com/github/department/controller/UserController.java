package com.github.department.controller;

import com.github.department.entity.Role;
import com.github.department.entity.User;
import com.github.department.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class UserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditorForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEditor";
    }

    @PostMapping
    public String saveChanges(@RequestParam("userId") User user,
                              @RequestParam String username,
                              @RequestParam Map<String, String> form
    ) {
        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getAccessLevel().clear();

        for(String key : form.keySet()) {
            if(roles.contains(key)) {
                user.getAccessLevel().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);

        return "redirect:/user";
    }
}