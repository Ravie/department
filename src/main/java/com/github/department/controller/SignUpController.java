package com.github.department.controller;

import com.github.department.entity.User;
import com.github.department.entity.dto.CaptchaResponseDTO;
import com.github.department.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class SignUpController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/register")
    public String welcomePage() {
        return "register";
    }

    @PostMapping("/register")
    public String signUp(@RequestParam("passwordConfirm") String passwordConfirm,
                         @RequestParam("g-recaptcha-response") String captchaResponse,
                         @Valid User user,
                         BindingResult bindingResult,
                         Model model) {
        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDTO response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);

        if (response != null && !response.isSuccess()) {
            model.addAttribute("captchaError", "Пройдите проверку");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);

        if (isConfirmEmpty) {
            model.addAttribute("passwordConfirmError", "Подтвердите пароль");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("user", user);
            model.addAttribute("passwordError", "Пароли не совпадают!");

            return "register";
        }

        if (isConfirmEmpty || bindingResult.hasErrors()) {
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
