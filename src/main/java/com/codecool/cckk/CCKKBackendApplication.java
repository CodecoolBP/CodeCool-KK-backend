package com.codecool.cckk;

import com.codecool.cckk.model.User;
import com.codecool.cckk.service.UserStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class CCKKBackendApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CCKKBackendApplication.class);

    @Autowired
    private UserStorage userStorage;

    public static void main(String[] args) {
        SpringApplication.run(CCKKBackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return (String... args) -> {
            UserStorage.addPremadeUsers();
        };
    }

    @PostConstruct
    public void afterInit() {
        List<User> users = userStorage.getUsers();
        for (User user :
                users) {
            LOGGER.info(user.toString());
        }
    }

}
