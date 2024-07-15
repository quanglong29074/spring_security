package com.example.demo_sevirity.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class LoginController {
  @GetMapping("/loginPage")
    public String login(){
      return "login";
  }

  @GetMapping("/logout")
  public String logout(HttpServletRequest request) throws ServletException {
    request.logout();
    return "redirect:/loginPage";
  }
}
