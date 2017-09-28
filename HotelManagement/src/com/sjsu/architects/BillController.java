package com.sjsu.architects;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tranpham on 8/1/17.
 */
public class BillController {

    static ArrayList<Bill> billArrayList = new ArrayList<Bill>();

    /**
     * Create Bill
     * @param member
     * @return Bill
     */
    public Bill createBill(Member member){
        Bill bill = new Bill();

        for(RoomBooking booking:RoomBooking.getUnpaidBookingOfMember(member.getId())){
            bill.addBooking(booking);
        }

        for(FoodBooking booking:FoodBooking.getUnpaidBookingOfMember(member.getId())){
            bill.addBooking(booking);
        }

        for(SpaBooking booking:SpaBooking.getUnpaidBookingOfMember(member.getId())){
            bill.addBooking(booking);
        }

        billArrayList.add(bill);

        return bill;
    }

    /**
     * View unpaid booking of member
     * @param member
     */
    public void viewUnpaidBookingOfMember(Member member){

        double totalCost=0;
        System.out.println("Unpaid Booking for Member : "+member.name);
        System.out.println("====================================================");

        for(Booking booking:RoomBooking.getUnpaidBookingOfMember(member.getId())){
            double bookingCost=booking.cost();
            String desc=booking.getDescription();
            String formatter="%-40s%2f%n";
            if(desc.length()>40){
                formatter="%-"+(desc.length()+7)+"s%2f%n";
            }
            System.out.printf(formatter,booking.getDescription(),bookingCost);
            totalCost+=bookingCost;
        }

        for(FoodBooking booking:FoodBooking.getUnpaidBookingOfMember(member.getId())){
            double bookingCost=booking.cost();
            System.out.printf("%-40s%2f%n",booking.getDescription(),bookingCost);
            totalCost+=bookingCost;
        }

        for(SpaBooking booking:SpaBooking.getUnpaidBookingOfMember(member.getId())){
            double bookingCost=booking.cost();
            System.out.printf("%-40s%2f%n",booking.getDescription(),bookingCost);
            totalCost+=bookingCost;
        }

        System.out.println("====================================================");
        System.out.printf("%-40s%2f%n","Total",totalCost);
    }

    /**
     * Pay Bill
     * @param billId
     */
    public void payBill(int billId){

        Bill bill = getBillById(billId);
        if(bill!=null){

            bill.pay(new CashPaymentStrategy());
        }
        else
            System.out.println("Bill not found!");
    }

    /**
     * Generate Bill
     */
    public void run(){
        boolean exit=false;
        do {


            System.out.println("Bill & Payment ");
            System.out.println("1. View unpaid booking | 2. Generate Bill | 3. Pay Bill | 0. Exit");

            int iOperation = Bootstrap.handleUserInput();

            switch (iOperation){
                case 1:
                    System.out.println("Enter Member ID: ");

                    int userID = Bootstrap.handleUserInput();
                    IdentityController accountList = IdentityController.getInstance();
                    Member member = (Member) accountList.getAccountById(userID);
                    if(member!=null){
                        viewUnpaidBookingOfMember(member);
                    } else {
                        System.out.println("Member not found");
                    }
                    break;
                case 2:
                    System.out.println("Enter Member ID: ");

                    userID = Bootstrap.handleUserInput();
                    member = (Member) IdentityController.getInstance().getAccountById(userID);
                    if(member!=null){
                        Bill bill=createBill(member);

                        System.out.println("Enter Promotion Code or Enter to skip: ");
                        Scanner scan = new Scanner(System.in);
                        String promotionCode = scan.nextLine();

                        double discountRate=0.0;
                        Promotion promotion =PromotionController.getInstance().getPromotionByCode(promotionCode);
                        if(promotion!=null&&promotion.getPromotionValidity()){
                            discountRate=promotion.getDiscountRatio();
                            System.out.println("Promotion code accepted! Total amount will be discounted by "+discountRate);
                            bill.setPromotion(promotion);
                        }
                        else
                            System.out.println("Invalid Promotion Code!");

                        bill.printBillDetail();
                    } else {
                        System.out.println("Member not found");
                    }
                    break;

                case 3:
                    System.out.println("Enter Bill No: ");
                    int billId = Bootstrap.handleUserInput();
                    Bill bill = getBillById(billId);
                    if(bill!=null){

                        bill.printBillDetail();
                        System.out.println("Enter Payment Option: ");
                        System.out.println("1. Cash | 2. Check | 3. Credit Card | 0. Exit");

                        int iPaymentOption = Bootstrap.handleUserInput();
                        switch (iPaymentOption){
                            case 1:
                                bill.pay(new CashPaymentStrategy());
                                break;
                            case 2:
                                bill.pay(new CheckPaymentStrategy());
                                break;
                            case 3:
                                Scanner scan = new Scanner(System.in);
                                System.out.println("Enter Name on Credit Card: ");
                                String name=scan.nextLine();
                                System.out.println("Enter Name Card Number: ");
                                String cardNumber=scan.nextLine();
                                System.out.println("Enter CVV: ");
                                String cvv=scan.nextLine();
                                String dateOfExpiry="2019/12/31";
                                bill.pay(new CreditCardPaymentStrategy(name,cardNumber,cvv,dateOfExpiry));
                                break;
                        }
                    }
                    break;
                case 0:
                    exit=true;
                    break;
                default:
                    break;
            }

        }while (!exit);
    }

    /**
     * Get Bill by bill id
     * @param billId
     * @return
     */
    public Bill getBillById(int billId){
        for(Bill bill:billArrayList){
            if(bill.getBillID()==billId)
                return bill;
        }
        return null;
    }

    public static void main(String[] args){

    }
}