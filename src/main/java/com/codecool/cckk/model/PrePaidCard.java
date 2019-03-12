package com.codecool.cckk.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrePaidCard extends Card {

    private double balance;
    private Map<Date, PassType> passes = new HashMap<>();
}
