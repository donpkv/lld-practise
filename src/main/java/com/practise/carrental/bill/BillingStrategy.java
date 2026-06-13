package com.practise.carrental.bill;

import com.practise.carrental.reservation.Reservation;

public interface BillingStrategy {
    Bill generateBill(Reservation reservation);
}
