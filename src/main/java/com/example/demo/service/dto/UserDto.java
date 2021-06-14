package com.example.demo.service.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotNull String email;
    @NotNull String password;
    @NotNull String repeatPassword;

    public UserDto(String email, String password, String repeatPassword) {
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String companyName;
    String companyAddress;
    String companyNip;
    String name;
    String lastName;
}
