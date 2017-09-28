package com.sjsu.architects;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodBooking extends Booking{

    private static HashMap<Integer , FoodBooking> foodOrders = new HashMap<Integer, FoodBooking>();

    ArrayList<Food> foodArrayList = new ArrayList<>();

    public ArrayList<Food> getFoodItems() {
        return foodArrayList;
    }

    public void foodList(ArrayList<Food> food) {
        this.foodArrayList = food;
    }

    public static HashMap<Integer, FoodBooking> getFoodOrders() {
        return foodOrders;
    }

    public static void setFoodOrders(HashMap<Integer, FoodBooking> foodOrders) {
        FoodBooking.foodOrders = foodOrders;
    }

    /**
     * Get Unpaid booking of member
     * @param memberId
     * @return
     */
    public static ArrayList<FoodBooking> getUnpaidBookingOfMember(int memberId){
        ArrayList<FoodBooking> memberBookingList= new ArrayList<FoodBooking>();
        for (FoodBooking booking:foodOrders.values()){
            if(booking.getMember().getId()==memberId && !booking.isPaid())
                memberBookingList.add(booking);
        }
        return memberBookingList;
    }

    @Override
    public double cost() {
        double totalcost=0;
        for (Food food:foodArrayList)
            totalcost+=food.getPrice();
        return totalcost;
    }

    @Override
    public void paid() {
        foodOrders.get(getBookingID()).isPaid=true;
    }

}
