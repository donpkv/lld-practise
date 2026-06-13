package com.practise.carparking.pricing;

import com.practise.carparking.Entity.Ticket;

public interface PricingStrategy {

    double calculate(Ticket ticket);
}
