package com.github.department.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class PageController {
    @PostMapping("/goToPage")
    public String goToPage(@RequestParam(required = false, defaultValue = "") Integer pageNumber,
                           @RequestParam String uri) {
        String newUri = ServletUriComponentsBuilder.fromUriString(uri).replaceQueryParam("page", ((pageNumber != null) ? pageNumber : 1) - 1).toUriString();
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl(newUri);
        urlBuilder.scheme(null);

        return "redirect:/" + urlBuilder.toUriString();
    }
}
