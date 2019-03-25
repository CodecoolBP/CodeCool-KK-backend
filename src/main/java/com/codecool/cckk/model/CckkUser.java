package com.codecool.cckk.model;

import com.codecool.cckk.model.cards.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@Entity
@AllArgsConstructor
public class CckkUser {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String email;
    private String hashedPassword;

//    private Discount discount;
//    private PassType passType;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
    private List<Trip> trips = new ArrayList<>();
//    private List<Card> myCards = new ArrayList<>();

    public CckkUser() {
    }

    public CckkUser(String firstName, String lastName, Discount discount) {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.discount = discount;
    }

    public CckkUser(String firstName, String lastName, String email, String hashedPassword, Discount discount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
//        this.discount = discount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

//    public Discount getDiscount() {
//        return discount;
//    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

//    public PassType getPassType() {
//        return passType;
//    }

//    public List<Trip> getTrips() {
//        return trips;
//    }

    @Override
    public String toString() {
        return "CckkUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
//                ", discount=" + discount +
//                ", passType=" + passType +
//                ", trips=" + trips +
//                ", myCards=" + myCards +
                '}';
    }
}
