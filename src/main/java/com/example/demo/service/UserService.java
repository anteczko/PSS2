package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public User registerUser(User user){ return userRepository.save(user); }

    public User registerUser(UserDto userDto){
        User user = new User(
                userDto.getCompanyName(),
                userDto.getCompanyAddress(),
                userDto.getCompanyNip(),
                userDto.getName(),
                userDto.getLastName(),
                userDto.getEmail(),
                userDto.getPassword()
        );

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){ return userRepository.findAll(); }

    public Optional<User> getUserById(int userId){ return userRepository.findById(userId); }

    public List<User> getAllUsersByRoleName(String rola){
       return userRepository.findAll().stream().filter(a->a.getRole().contains(Role.valueOf(rola))==true).collect(Collectors.toList());
    }

    public boolean changePassword(int userId,String newPassword){
        Optional<User> Ouser=userRepository.findById(userId);
        if(Ouser.isPresent()) {
            User user=Ouser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }else return false;
    }

    public boolean deleteUser(int userId){
        Optional<User> Ouser=userRepository.findById(userId);
        if(Ouser.isPresent()){
            userRepository.delete(Ouser.get());
            return true;
        }
        return false;
    }
}
