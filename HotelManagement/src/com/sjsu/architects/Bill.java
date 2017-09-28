package com.sjsu.architects;

import java.util.*;

/**
 * Created by sneha on 7/30/17.
 */
public class Bill {

    private int billID;
    private static int identity=1;
    ArrayList<Booking> bookingArrayList = new ArrayList<Booking>();
    PaymentStrategy payment;
    boolean isPaid=false;
    private Promotion promotion=null;

    public Bill(){
        billID=identity++;
    }

    public int getBillID() {
        return billID;
    }

    /**
     * Get Total Cost of Booking
     * @return
     */
    public double getTotalCost(){
        double result=0;
        for (Booking booking:bookingArrayList){
            result+=booking.cost();
        }

        if(promotion!=null){
            double discount=promotion.getDiscountRatio();
            result=result*(1-discount);
        }

        return result;
    }

    /**
     * Print Bill
     */
    public void printBillDetail(){
        double result=0;
        System.out.println("Bill No: "+billID);
        System.out.println("====================================================");
        for (Booking booking:bookingArrayList){
            double bookingCost=booking.cost();
            String desc=booking.getDescription();
            String formatter="%-40s%2f%n";
            if(desc.length()>40){
                formatter="%-"+(desc.length()+7)+"s%2f%n";
            }
            System.out.printf(formatter,booking.getDescription(),bookingCost);
            result+=bookingCost;
        }
        System.out.println("====================================================");
        double discount=0.0;
        if(promotion!=null){
            discount=promotion.getDiscountRatio();
            System.out.printf("%-40s%2f%n","Discount",discount);
        }

        System.out.printf("%-40s%2f%n","Total",result*(1-discount));
    }

    /**
     * Pay Bill
     * @param paymentStrategy
     */
    public void pay(PaymentStrategy paymentStrategy){
        this.payment=paymentStrategy;
        isPaid=true;
        for (Booking booking:bookingArrayList){
            booking.paid();
        }
        paymentStrategy.processPayment(getTotalCost(),billID);
    }

    public void addBooking(Booking booking){
        bookingArrayList.add(booking);
    }
    public void addBooking(ArrayList<Booking> booking){
        bookingArrayList.addAll(booking);
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
