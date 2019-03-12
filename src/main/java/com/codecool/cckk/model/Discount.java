package com.codecool.cckk.model;

public enum Discount {
    FULL_PRICE(0),
    STUDENT(50),
    RETIRED(100),
    DISABLED(90);

    private final int discountPrice;

    Discount(int percentDiscount) {
        discountPrice = percentDiscount;
    }

    public double getDiscount(){
        return discountPrice/100;
    }
}
