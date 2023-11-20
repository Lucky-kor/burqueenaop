package com.codestates.burgerqueenspring.discount.discountPolicy;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public class FixedRateDiscountPolicy implements DiscountPolicy {

    private int discountRate = 10;

    public int calculateDiscountedPrice(int price) {
        return price - (price * discountRate / 100);
    }
}
