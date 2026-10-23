package com.example.crons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CronsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CronsApplication.class, args);
    }
}