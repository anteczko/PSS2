package com.example.demo.controller;

import com.example.demo.model.Delegation;
import com.example.demo.model.User;
import com.example.demo.service.DelegationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class SiteController {
    @Autowired
    public SiteController(UserService userService, DelegationService delegationService) {
        this.userService = userService;
        this.delegationService = delegationService;
    }
    UserService userService;
    DelegationService delegationService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/usersByRole")
    public List<User> getAllUserByRole(
            @RequestParam("role")String role
    ){
        return userService.getAllUsersByRoleName(role);
    }

    @PostMapping("/user/register")
    public User registerUser(
            @RequestParam("companyName")String companyName,
            @RequestParam("companyAddress")String companyAddress,
            @RequestParam("companyNip")String companyNip,
            @RequestParam("name")String name,
            @RequestParam("lastName")String lastName,
            @RequestParam("email")String email,
            @RequestParam("password")String password
    ){
        return userService.registerUser( new User(companyName,companyAddress,companyNip,name,lastName,email,password) );
    }

    @PutMapping("/user/password")
    public boolean changePassword(
            @RequestParam("userId")
                    int userId,
            @RequestParam("newPassword")
                    String newPassword
    ){
        return userService.changePassword(userId,newPassword);
    }
    @DeleteMapping("/user/delete")
    public boolean deleteUser(
            @RequestParam("userId")
                    int userId
    ){
        return userService.deleteUser(userId);
    }

    
    
   /////////////////////////////////////////


    @GetMapping("/delegations")
    public List<Delegation> getAllDelegations(){
        return delegationService.getAllDelegations();
    }

    @GetMapping("/delegationsOrderByDateDesc")
    public List<Delegation> getAllDelegationsOrderByDateStartDesc(){
        return delegationService.getAllDelegationsOrderByDateStartDesc();
    }

    @GetMapping("/delegationsByUserOrderByDateDesc")
    public List<Delegation> getAllDelegationsByUserOrderByDateStartDesc(
            @RequestParam("userId")int userId){
        return delegationService.getAllDelegationsByUserOrderByDateStartDesc(userId);
    }

    @DeleteMapping("/delegation/delete")
    public boolean deleteDelegation(
            @RequestParam("delegationId")
            int delegationId)
    {
        return delegationService.deleteDelegation(delegationId);
    }

    @PostMapping("/delegation")
    public Delegation addDelegation(
            @RequestParam("dateTimeStart")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime dateTimeStart,
            @RequestParam("dateTimeStop")
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    LocalDateTime dateTimeStop,
            @RequestParam("userId")
                    Optional<Integer> userId
    ){
        if(userId.isPresent()){
            Optional<User> Ouser=userService.getUserById(userId.get());
            return delegationService.addDelegation(new Delegation(dateTimeStart,dateTimeStop,Ouser.get()));
        }
        else return delegationService.addDelegation(new Delegation(dateTimeStart,dateTimeStop));
    }
}
