package com.sjsu.architects;

/**
 * Created by sneha on 7/31/17.
 */
public class CreditCardPaymentStrategy implements PaymentStrategy {

    private String name;
    private String cardNumber;
    private String cvv;
    private String dateOfExpiry;

    /**
     * Constructor
     * @param name
     * @param cardNumber
     * @param cvv
     * @param dateOfExpiry
     */
    public CreditCardPaymentStrategy(String name, String cardNumber, String cvv, String dateOfExpiry){
        this.name=name;
        this.cardNumber=cardNumber;
        this.cvv=cvv;
        this.dateOfExpiry=dateOfExpiry;
    }
    @Override
    public void processPayment(double amount, int billId) {
        System.out.println("Please swipe your credit card for " + amount+ "$ at the card reader");
    }
}