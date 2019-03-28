package com.codecool.cckk.model;

import com.codecool.cckk.model.cards.PrePaidCard;
import com.codecool.cckk.model.trips.Trip;

import lombok.*;

import javax.persistence.*;
import javax.smartcardio.Card;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CckkUser {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String email;
    @EqualsAndHashCode.Exclude
    private String password;

    @Enumerated(EnumType.STRING)
    private Discount discount;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true)
    private Set<Trip> trips;

    @Singular
    @OneToMany(cascade = CascadeType.ALL,
    orphanRemoval = true)
    private Set<PrePaidCard> cards;

    @ElementCollection
    public List<HashMap<String, LocalDate>> passes;



    public CckkUser(String firstName, String lastName, Discount discount, PrePaidCard card) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.discount = discount;
        this.cards.add(card);
    }

    public CckkUser(String email, String password) {
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "CckkUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", passes='" + passes + '\'' +
                '}';
    }
}
