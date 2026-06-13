package com.practise.carrental.payment;

import com.practise.carrental.bill.Bill;

public interface PaymentStrategy {
    Payment processPayment(Bill bill, double paymentAmount);
}
