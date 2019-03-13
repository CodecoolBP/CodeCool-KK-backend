package com.codecool.cckk.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class User {
    private UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;
    private Discount discount;
    private PassType passType;
    private List<Trip> trips = new ArrayList<>();
    private List<Card> myCards;

    public User() {
    }

    public User(String firstName, String lastName, Discount discount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.discount = discount;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Discount getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", discount=" + discount +
                '}';
    }
}
