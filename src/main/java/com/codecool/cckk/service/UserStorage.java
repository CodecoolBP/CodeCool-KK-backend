package com.codecool.cckk.service;

import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.ReturnMessage;
import com.codecool.cckk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStorage {

    private static List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void addPremadeUsers() {
        users.add(new User("John", "Doe", Discount.FULL_PRICE));
        users.add(new User("Jane", "Doe", Discount.RETIRED));
        users.add(new User("Junior", "Doe", Discount.STUDENT));
    }

    public ReturnMessage addUser(User user) {
        if (!isEmailAddressTaken(user)) {
            users.add(user);
            return new ReturnMessage(true, "Registration successful");
        } else if (isEmailAddressTaken(user)) {
            return new ReturnMessage(false, "Email address is already taken!");
        } else {
            //TODO errorhandling
            return new ReturnMessage(false, "general error");
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (User user :
                users) {
            result += user.toString() + " ";
        }
        return result;
    }

    public boolean isEmailAddressTaken(User user) {
        for (User checkedUser :
                users) {
            if (user.getEmail() != null && checkedUser.getEmail() != null)
                if (checkedUser.getEmail().equals(user.getEmail())) return true;
        }
        return false;
    }
}
