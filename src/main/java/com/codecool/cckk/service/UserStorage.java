package com.codecool.cckk.service;

import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStorage {
    private UserCreator userCreator;
    private static List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    @Autowired
    public UserStorage(UserCreator userCreator) {
        this.userCreator = userCreator;
    }

    public static void addPremadeUsers() {
        users.add(new User("John", "Doe", Discount.FULL_PRICE));
        users.add(new User("Jane", "Doe", Discount.RETIRED));
        users.add(new User("Junior", "Doe", Discount.STUDENT));
    }

}
