package com.codecool.cckk.model;

import com.codecool.cckk.model.cards.PrePaidCard;
import com.codecool.cckk.model.trips.Trip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class CckkUser {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true)
    private Set<Trip> trips;

    @OneToMany(cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<PrePaidCard> cards;


    public CckkUser(String firstName, String lastName, Discount discount) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "CckkUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
