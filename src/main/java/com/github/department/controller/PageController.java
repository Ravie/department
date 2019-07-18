package com.github.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @PostMapping("/goToPage")
    public String goToPage(@RequestParam Integer page,
                           @RequestParam Integer size,
                           @RequestParam String url,
                           Model model) {
        model.addAttribute("pageNumber", page);
        return String.format("redirect:%s?page=%s&size=%s", url, page - 1, size);
    }
}
