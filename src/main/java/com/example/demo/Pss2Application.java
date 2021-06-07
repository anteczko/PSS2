package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class Pss2Application {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(Pss2Application.class, args);

	}
	@EventListener(ApplicationReadyEvent.class)
	public void afterStartup(){
		System.out.println("[!] Hello world!!!");
		//userRepository.save(new User("test","test","test","test","test","test","test"));
	}
}
