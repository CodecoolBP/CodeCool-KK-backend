package com.codecool.cckk.service;

import com.codecool.cckk.model.CckkUser;
import com.codecool.cckk.model.Discount;
import com.codecool.cckk.model.cards.PrePaidCard;
import com.codecool.cckk.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMoneyCalculatorTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMoneyCalculator userMoneyCalculator;

    @Test
    public void testIsIfUserHasMoneyTheyCanTravel() {
        CckkUser zsoltika = createZsoltika();
        PrePaidCard moneyCard = createMoneyCard();
        zsoltika.setCards(Collections.singleton(moneyCard));
        userRepository.save(zsoltika);
        boolean canUserTravel = userMoneyCalculator.checkIfUserCanTravel(zsoltika, moneyCard.getCardNumber());
        Assertions.assertTrue(canUserTravel);
    }

    @Test
    public void testIsIfUserDoesntHaveMoneyTheyCantTravel() {
        CckkUser zsoltika = createZsoltika();
        PrePaidCard moneyCard = createPennilessCard();
        zsoltika.setCards(Collections.singleton(moneyCard));
        userRepository.save(zsoltika);
        boolean canUserTravel = userMoneyCalculator.checkIfUserCanTravel(zsoltika, moneyCard.getCardNumber());
        Assertions.assertFalse(canUserTravel);
    }



    private CckkUser createZsoltika() {
        return CckkUser.builder()
                .firstName("Zsoltika")
                .lastName("Kovacs")
                .password("alma")
                .email("zsoltika.k@ema.il")
                .discount(Discount.STUDENT)
                .build();
    }

    private PrePaidCard createMoneyCard() {
        return PrePaidCard.builder()
                .cardNumber(4324878918186423L)
                .balance(5000)
                .build();
    }

    private PrePaidCard createPennilessCard() {
        return PrePaidCard.builder()
                .cardNumber(8237787477736271L)
                .balance(100)
                .build();
    }

}