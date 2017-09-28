package com.sjsu.architects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


/**
 * Actual implementation of Booking
 */
public class StandardBookingController {

    Booking booking = new Booking();

    /**
     * Constructor
     */
    public StandardBookingController( ){
        Member member = new Member();
        member.setEmail_address("");
        member.setName("");

        booking.setBookingID();
        booking.setDateOfBooking(new Date("9999/07/31"));
        booking.setMember(member);
        this.booking = booking;
    }

    public void bookRoom(Booking booking){
        this.booking = booking;
    }

    /**
     * Book Spa Service
     * @param bookingID
     */
    public void bookSpaServices(int bookingID){

        HashMap<Integer, MemberBookingProxy> spaBooking = RoomBooking.getMemberRoomBookings();
        MemberBookingProxy member = spaBooking.get(bookingID);

        if(member !=null) {
            ArrayList<SpaService> spaItems = Hotel.getServiceList();
            ArrayList<SpaService> selectedSpaService = new ArrayList<>();
            int UserType = -1;

            do {

                for (int i = 0; i < spaItems.size(); i++) {
                    System.out.println(spaItems.get(i).getServiceNumber() + ". " + spaItems.get(i).getServiceName() + " " + spaItems.get(i).getPrice());
                }
                System.out.println("0. Exit");

                UserType = Bootstrap.handleUserInput();
                if (UserType != 0) {
                    SpaService service = spaItems.get((UserType - 1));
                    selectedSpaService.add(service);
                    System.out.println("Customer " + member.bookings().getMember().getName() + " requested " + service.getServiceName());

                } else if (UserType == 0) {
                    break;
                } else {
                    break;
                }
            }
            while (1 != 0);


            SpaBooking booking = new SpaBooking();
            booking.setSpaServices(selectedSpaService);
            booking.setMember(member.bookings().getMember());

            for (SpaService spaService : selectedSpaService)
                booking.appendDescription(spaService.getServiceName());

            HashMap<Integer, SpaBooking> spaBookingHashMap = new HashMap<>();
            spaBookingHashMap.put(bookingID, booking);
            SpaBooking.setSpaService(spaBookingHashMap);
        } else {
            System.out.println("No Booking found");
        }

    }

    /**
     * Order Food
     * @param bookingID
     */
    public void orderFood(int bookingID){
        HashMap<Integer, MemberBookingProxy> memberBooking = RoomBooking.getMemberRoomBookings();
        MemberBookingProxy member = memberBooking.get(bookingID);

        if(member !=null) {
            ArrayList<Food> foodItems = Hotel.getFoodItems();
            ArrayList<Food> selectedFood = new ArrayList<>();
            int UserType = -1;
            do {

                for (int i = 0; i < foodItems.size(); i++) {
                    System.out.println(foodItems.get(i).getItemNumber() + ". " + foodItems.get(i).getItemName() + " " + foodItems.get(i).getPrice());
                }
                System.out.println("0. Exit");
                UserType = Bootstrap.handleUserInput();

                if (UserType != 0) {
                    Food service = foodItems.get((UserType - 1));
                    //selectedFood.add(foodItems.get(UserType-1));
                    selectedFood.add(service);
                    System.out.println("Customer " + member.bookings().getMember().getName() + " requested " + service.getItemName());

                } else if (UserType == 0) {
                    break;
                } else {
                    break;
                }
            }
            while (1 != 0);

            FoodBooking booking = new FoodBooking();
            booking.foodList(selectedFood);
            booking.setMember(member.bookings().getMember());


            for (Food foodsrv : selectedFood)
                booking.appendDescription(foodsrv.getItemName());

            HashMap<Integer, FoodBooking> foodBookingHashMap = new HashMap<>();
            foodBookingHashMap.put(bookingID, booking);
            FoodBooking.setFoodOrders(foodBookingHashMap);
        } else {
            System.out.println("No booking found");
        }

    }


    public Booking getRoomBookings(){
        return booking;
    }

}

