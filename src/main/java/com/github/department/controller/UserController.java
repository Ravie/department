package com.github.department.controller;

import com.github.department.entity.Role;
import com.github.department.entity.User;
import com.github.department.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.loadAllUsers());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("{user}")
    public String userEditorForm(@PathVariable User user, Model model, @RequestParam(required = false) Boolean success) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        if (success != null && success) {
            model.addAttribute("success", "Данные успешно обновлены!");
        }
        return "userEditor";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("{userId}")
    public String saveChanges(@AuthenticationPrincipal User curUser,
                              @PathVariable("userId") User user,
                              @RequestParam String username,
                              @RequestParam Map<String, String> form
    ) throws ServletException {
        userService.updateUserSettings(curUser, user, username, form);

        return "redirect:/user/{userId}?success=true";
    }

    @GetMapping("profile")
    public String userProfile(Model model,
                              @AuthenticationPrincipal User user,
                              @RequestParam(required = false) Boolean success) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        if (success != null && success) {
            model.addAttribute("success", "Данные успешно обновлены!");
        }

        return "profile";
    }

    @PostMapping("profile")
    public String updateUserProfile(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String email
    ) {
        userService.updateProfile(user, password, email);

        return "redirect:/user/profile?success=true";
    }
}
