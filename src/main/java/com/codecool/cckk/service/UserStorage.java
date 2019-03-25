package com.codecool.cckk.service;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.ReturnMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserStorage {

    private static List<CckkUser> cckkUsers = new ArrayList<>();

    public List<CckkUser> getUsers() {
        return cckkUsers;
    }

    public void addPremadeUsers() {
        cckkUsers.add(new CckkUser("John", "Doe", Discount.FULL_PRICE));
        cckkUsers.add(new CckkUser("Jane", "Doe", Discount.RETIRED));
        cckkUsers.add(new CckkUser("Junior", "Doe", Discount.STUDENT));
    }

    public ReturnMessage addUser(CckkUser cckkUser) {
        if (!isEmailAddressTaken(cckkUser)) {
            cckkUsers.add(cckkUser);
            return new ReturnMessage(true, "Registration successful");
        } else if (isEmailAddressTaken(cckkUser)) {
            return new ReturnMessage(false, "Email address is already taken!");
        } else {
            //TODO errorhandling
            return new ReturnMessage(false, "general error");
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (CckkUser cckkUser :
                cckkUsers) {
            result += cckkUser.toString() + " ";
        }
        return result;
    }

    public boolean isEmailAddressTaken(CckkUser cckkUser) {
        for (CckkUser checkedCckkUser :
                cckkUsers) {
            if (cckkUser.getEmail() != null && checkedCckkUser.getEmail() != null)
                if (checkedCckkUser.getEmail().equals(cckkUser.getEmail())) return true;
        }
        return false;
    }
}
