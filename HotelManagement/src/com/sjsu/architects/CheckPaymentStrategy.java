package com.sjsu.architects;

/**
 * Created by sneha on 7/31/17.
 */
public class CheckPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(double amount, int billId) {

        System.out.println("Please deposit check for  " + amount+ "$ at the Hotel dropbox ");
    }
}
