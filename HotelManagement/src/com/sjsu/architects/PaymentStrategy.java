package com.sjsu.architects;

/**
 * Created by sneha on 7/31/17.
 */
public interface PaymentStrategy {

    public void processPayment(double amount, int billId);
}
