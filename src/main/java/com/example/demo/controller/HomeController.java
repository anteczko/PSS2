package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.DelegationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    public HomeController(UserService userService, DelegationService delegationService) {
        this.userService = userService;
        this.delegationService = delegationService;
    }
    UserService userService;
    DelegationService delegationService;


    @GetMapping("/")
    String index() {
        return "index";
    }


    @GetMapping("/login")
    String login(){
        return "login";
    }

    @GetMapping("/register")
    String register(){
        return "register";
    }

    @GetMapping("/panel")
    String panel(){
        return "panel";
    }

    @GetMapping("/userList")
    public String getUsers(Model model){
        List<User> users=userService.getAllUsers();
        model.addAttribute("userList",users);
        return "panel";
    }
}
