package com.example.demo.controller;

import com.example.demo.model.Delegation;
import com.example.demo.model.User;
import com.example.demo.service.DelegationService;
import com.example.demo.service.UserService;
import com.example.demo.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/changePassword")
    String changePassword(Model model){
        //User user =new User();
        UserDto user =new UserDto();
        model.addAttribute("user",user);
        return "changePassword";
    }

    @PostMapping("/changePassword")
    String changePassword(
            @ModelAttribute("user")UserDto userDto
    ){
            User user=userService.getAllUsers().stream().filter(x->{
                return x.getName().equals(userDto.getName());
            }).findFirst().get();

            userService.changePassword(user.getUserId(),userDto.getPassword());
        return "changePassword";
    }

    @GetMapping("/register")
    String register(Model model){
        //User user =new User();
        UserDto user =new UserDto();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    String registerFeedback(
            @ModelAttribute("user")UserDto userDto
    ){
        userService.registerUser(userDto);
        return "register";
    }

    @GetMapping("/panel")
    String panel(Model model){
        List<User> user=userService.getAllUsers();
        model.addAttribute("user",user);

        return "panel";
    }

    @GetMapping("/delegationsList")
    String delegations(Model model){
        List<Delegation> delegations=delegationService.getAllDelegations();
        model.addAttribute("delegations",delegations);
        return "/delegationList";
    }

    @GetMapping("/userList")
    public String getUsers(Model model){
        List<User> users=userService.getAllUsers();
        model.addAttribute("userList",users);
        return "panel";
    }
}
