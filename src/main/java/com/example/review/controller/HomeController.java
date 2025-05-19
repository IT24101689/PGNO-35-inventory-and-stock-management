package com.example.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/reviews";
    }

    @GetMapping("/error")
    public String handleError() {
        return "error";
    }
}
