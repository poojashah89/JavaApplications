package com.sjsu.architects;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.text.*;

public class PromotionController {

    private static PromotionController instance = null;

    ArrayList<IPromotionObserver> observers = new ArrayList<IPromotionObserver>();
    HashMap<String,Promotion> promotionHashMap = new HashMap<>();

    int internalState;

    protected PromotionController (){

    }

    public static PromotionController getInstance(){
        if(instance==null)
            instance= new PromotionController();
        return instance;
    }

    /**
     * Generate Promotion
     */
    public void createPromotion(){

        Promotion promotion = new Promotion();

        System.out.println("Enter Promotion Code");
        Scanner scan = new Scanner(System.in);
        promotion.setPromotionID(scan.nextLine());

        System.out.print("Enter Promotion Start Date in MM/dd/yyyy format: ");
        String startDate = scan.nextLine();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date startPromotion = null;
        try {

            startPromotion=df.parse(startDate);

        }
        catch(ParseException e) {
            System.out.println("Unable to parse(Incorrect date entered) " + startDate);}

        promotion.setPromotionStartDate(startPromotion);

        System.out.print("Enter Promotion End Date in MM/dd/yyyy format: ");
        String endDate = scan.nextLine();
        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
        Date endPromotion = null;
        try {

            endPromotion=df2.parse(endDate);

        }
        catch(ParseException e) {
            System.out.println("Unable to parse(Incorrect date entered) " + endDate);}

        promotion.setPromotionEndDate(endPromotion);

        System.out.println("Is Promotion Valid (true/false)");
        promotion.setPromotionValidity(scan.nextBoolean());

        System.out.println("Set Discount Amount for promotion code ");
        promotion.setDiscountRatio(scan.nextInt());
        System.out.println("Sending promotion code to members.. ");
        notifyAllObservers();

        promotionHashMap.put(promotion.getPromotionID(),promotion);
    }

    /**
     * Subscribe a member using observer design
     * @param observer
     */
    public void subscribe(Member observer)
    {
        observers.add(observer);
    }


    /**
     * Notify a member using observer design
     */
    public void notifyAllObservers(){

        for(IPromotionObserver observer: observers)
        {
            observer.update();
        }
    }

    public void addPromotion(Promotion promotion){
        promotionHashMap.put(promotion.getPromotionID(),promotion);
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Promotion End Date in MM/dd/yyyy format: ");
        String endDate = scan.nextLine();
        DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy"); // format in 12/31/2017
        Date endPromotion = null;
        try {

            endPromotion=df2.parse(endDate);
            System.out.println(endPromotion);
        }
        catch(ParseException e) {
            System.out.println("Unable to parse(Incorrect date entered) " + endPromotion);}
    }

    public Promotion getPromotionByCode(String promotionCode){
        return promotionHashMap.get(promotionCode);
    }
}