package com.sjsu.architects;

/**
 * Created by sneha on 7/31/17.
 */
public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(double amount, int billId) {
        System.out.println("Please pay " + amount+ "$ to the Hotel Staff");
    }
}
