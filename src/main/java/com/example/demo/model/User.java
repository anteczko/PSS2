package com.example.demo.model;

import com.example.demo.model.enums.Role;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull int userId;
    @NotNull String companyName;
    @NotNull String companyAddress;
    @NotNull String companyNip;
    @NotNull String name;
    @NotNull String lastName;
    @NotNull String email;
    @NotNull @Size(min = 6, max=20) String password;
    boolean status=true;
    LocalDateTime registrationDate=java.time.LocalDateTime.now();

    //Role role=Role.pracownik;
    //ArrayList<Role> role= new ArrayList<Role>();
    @ElementCollection(targetClass = Role.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    Collection<Role> role= new ArrayList<Role>();


    public User(int userId,String companyName,String companyAddress, String companyNip, String name, String lastName, String email, String password) {
        this.userId=userId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyNip = companyNip;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        role.add(Role.pracownik);
        role.add(Role.kierowca);
    }


    public User(String companyName,String companyAddress, String companyNip, String name, String lastName, String email, String password) {
        this.companyName=companyName;
        this.companyAddress=companyAddress;
        this.companyNip=companyNip;
        this.name=name;
        this.lastName=lastName;
        this.email=email;
        this.password=password;
        role.add(Role.pracownik);
        role.add(Role.kierowca);
    }
}
