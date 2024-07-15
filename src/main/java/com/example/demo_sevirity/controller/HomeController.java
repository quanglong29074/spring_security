package com.example.demo_sevirity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/dashboard")
    public String Dashboard(){
        return "dashboard";
    }
    @GetMapping("/home")
    public String Home(){
        return "home";
    }
}
