package com.codecool.cckk;

import com.codecool.cckk.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CCKKBackendApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CCKKBackendApplication.class);

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CCKKBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return (String... args) -> {

        };
    }

}
