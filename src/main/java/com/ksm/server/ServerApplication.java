package com.ksm.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        System.out.println("Application is runnig...");
        String password = "loisceka";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Pass asli : " + password);
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println("bCrypt :  : " + hashedPassword);
        Boolean right = passwordEncoder.matches("loisceka", hashedPassword);
        System.out.println(right);
    }

}
