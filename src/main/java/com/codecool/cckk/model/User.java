package com.codecool.cckk.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Discount discount;
    private PassType passType;
    private List<Trip> trips = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String lastName, Discount discount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.discount = discount;
    }
}
