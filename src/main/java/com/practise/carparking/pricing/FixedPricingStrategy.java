package com.practise.carparking.pricing;

import com.practise.carparking.Entity.Ticket;

public class FixedPricingStrategy implements PricingStrategy {

    @Override
    public double calculate(Ticket ticket){
        return 100;
    }
}
