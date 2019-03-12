package com.codecool.cckk.service;

import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreator {
    public static User createUser() {
        return new User("John", "Doe", Discount.FULL_PRICE);
    }
}
