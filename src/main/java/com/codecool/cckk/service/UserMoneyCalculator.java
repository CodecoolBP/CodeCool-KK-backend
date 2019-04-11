package com.codecool.cckk.service;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.cards.PrePaidCard;
import com.codecool.cckk.repository.CardRepository;
import com.codecool.cckk.repository.TripRepository;
import com.codecool.cckk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class UserMoneyCalculator {

    UserRepository userRepository;
    TripRepository tripRepository;
    CardRepository cardRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTripRepository(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Autowired
    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public boolean checkIfUserCanTravel(CckkUser user, Long cardNumberUsed) {
        //TODO: user has purchased pass
        //TODO: user has traveled enough to acquire pass
        //TODO: user has money

        final int singleTripPrice = 350;

        PrePaidCard cardWasUsed = null;
        for(PrePaidCard thisCard : user.getCards()){
            if (thisCard.getCardNumber().equals(cardNumberUsed)) cardWasUsed = thisCard;
        }
        try {
            if (cardWasUsed.getBalance() >= singleTripPrice) {
                cardRepository.setNewBalance(cardWasUsed.getBalance()-singleTripPrice, cardWasUsed.getCardNumber());
                return true;
            }
        } catch (NullPointerException e) {
            System.err.println("invalid card number");
            return false;
        }

        return false;

    }


}
