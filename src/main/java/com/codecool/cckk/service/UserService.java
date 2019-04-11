package com.codecool.cckk.service;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean emailIsExists(CckkUser incomingUser) {
        List<CckkUser> users = userRepository.findAll();
        for (CckkUser user : users) {
            if (user.getEmail().equals(incomingUser.getEmail())){
                return true;
            }
        }
        return false;
    }

    public boolean passwordIsMatches(CckkUser incomingUser) {
        List<CckkUser> users = userRepository.findAll();
        for (CckkUser user : users) {
            if (user.getEmail().equals(incomingUser.getEmail())) {
                if (user.getPassword().equals(incomingUser.getPassword())) {
                    return true;
                }
            }
        }
        return false;
    }
}
