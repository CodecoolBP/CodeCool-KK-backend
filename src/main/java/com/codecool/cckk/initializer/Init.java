package com.codecool.cckk.initializer;

import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Init {
    @Autowired
    private List<User> users;

    public void run(){
            users.add(new User("John", "Doe", Discount.FULL_PRICE));
            users.add(new User("Jane", "Doe", Discount.FULL_PRICE));
            users.add(new User("Vazul", "Doe", Discount.RETIRED));
            users.add(new User("Quasimodo", "Doe", Discount.DISABLED));
            users.add(new User("Junior", "Doe", Discount.STUDENT));
    }
}
