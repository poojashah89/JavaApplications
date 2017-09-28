package com.sjsu.architects;

import java.util.ArrayList;

/**
 * Created by sneha on 7/30/17.
 */
public class Hotel {

    private String hotelName = null;

    private static ArrayList<Room> roomList = new ArrayList<>();
    private static ArrayList<SpaService> serviceList = new ArrayList<>();
    private static ArrayList<Food> foodItems = new ArrayList<>();


    public static ArrayList<SpaService> getServiceList() {
        return serviceList;
    }

    public static void setServiceList(ArrayList<SpaService> serviceList) {
        Hotel.serviceList = serviceList;
    }

    public static ArrayList<Food> getFoodItems() {
        return foodItems;
    }

    public static void setFoodItems(ArrayList<Food> foodItems) {
        Hotel.foodItems = foodItems;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }



    public static ArrayList<Room> getRoomList() {
        return roomList;
    }

    public static void setRoomList(ArrayList<Room> roomList) {
        Hotel.roomList = roomList;
    }



}
