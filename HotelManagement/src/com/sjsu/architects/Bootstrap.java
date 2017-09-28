package com.sjsu.architects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Bootstrap {

    /**
     * User input
     * @return
     */
    public static int handleUserInput(){
        int userInput = -1;
        Scanner scan = new Scanner(System.in);
        try{

            userInput = scan.nextInt();
        } catch (Exception ex){
            System.out.println("Try again. Incorrect input: number is required");
            Scanner scan1 = new Scanner(System.in);
            userInput = scan1.nextInt();
        }
        return userInput;
    }

    public static void initialize(){
        try{
            // Creating Initial 20 Rooms each of Different Types (Single, Double, Deluxe and Suite) and Bed Types (Single, Double, Queen and King Bed)
            initializeRoom();
            // Creating SPA Service
            initializeHotelServiceList();
            // Creating Food Items
            initializeFoodItems();

            RoomBooking roomBooking = createSampleRoomBooking();


            createSampleFoodBooking(roomBooking);

            //create sample promotion
            Promotion promotion= new Promotion();
            promotion.setDiscountRatio(0.3);
            promotion.setPromotionID("PRO");
            promotion.setPromotionValidity(true);
            PromotionController.getInstance().addPromotion(promotion);

        } catch (Exception ex){
            System.out.println("Oops! Something went wrong! " + ex.getMessage());
        }
    }

    private static void createSampleFoodBooking(RoomBooking roomBooking) {
        HashMap<Integer, MemberBookingProxy> memberBooking = RoomBooking.getMemberRoomBookings();
        MemberBookingProxy member = memberBooking.get(roomBooking.getBookingID());

        if(member !=null) {
            ArrayList<Food> foodItems = Hotel.getFoodItems();
            ArrayList<Food> selectedFood = new ArrayList<>();
            selectedFood.add(foodItems.get(0));
            FoodBooking booking = new FoodBooking();
            booking.setBookingID(roomBooking.getBookingID());
            booking.foodList(selectedFood);
            booking.setMember(member.bookings().getMember());

            for (Food foodsrv : selectedFood)
                booking.appendDescription(foodsrv.getItemName());

            HashMap<Integer, FoodBooking> foodBookingHashMap = new HashMap<>();
            foodBookingHashMap.put(roomBooking.getBookingID(), booking);
            FoodBooking.setFoodOrders(foodBookingHashMap);
        }
    }

    private static RoomBooking createSampleRoomBooking() {

        RoomBooking roomBooking = new RoomBooking();
        ArrayList<Room> roomlist= new ArrayList<Room>();
        roomlist.add(Hotel.getRoomList().get(0));
        roomBooking.setBookRoom(roomlist);
        roomBooking.appendDescription("Room "+Hotel.getRoomList().get(0).getRoomNumber());
        roomBooking.setBookingID(999);
        roomBooking.setMember((Member) IdentityController.getInstance().getAccountById(3));

        MemberBookingProxy bookingProxy = new MemberBookingProxy();
        bookingProxy.book(roomBooking);
        HashMap<Integer, MemberBookingProxy> list = new HashMap<>();
        list.put(roomBooking.getBookingID(), bookingProxy);
        RoomBooking.setMemberRoomBookings(list);

        RoomBooking roomBooking2 = new RoomBooking();
        ArrayList<Room> roomlist2= new ArrayList<Room>();
        roomlist2.add(Hotel.getRoomList().get(1));
        roomBooking2.setBookRoom(roomlist2);
        roomBooking2.appendDescription("Room "+Hotel.getRoomList().get(0).getRoomNumber());
        roomBooking2.setBookingID();
        roomBooking2.setMember((Member) IdentityController.getInstance().getAccountById(4));

        MemberBookingProxy bookingProxy2 = new MemberBookingProxy();
        bookingProxy2.book(roomBooking2);
        HashMap<Integer, MemberBookingProxy> list2 = new HashMap<>();
        list2.put(roomBooking2.getBookingID(), bookingProxy2);
        RoomBooking.setMemberRoomBookings(list2);

        return roomBooking;
    }

    /**
     * Initialize Room details
     */
    private static void initializeRoom(){

        ArrayList<Room> roomlist = new ArrayList<>();
        int singleroomNo = 100; int doubleroomNo = 200; int deluxeroomNo = 300; int suiteroomNo =400;
        for (int i=0; i<=20; i++)
        {
            Room room = new Room();
            room.setRoomNumber(singleroomNo++);
            room.setRoomType("Single Room");
            room.setBedType("Twin Bed");
            room.setRoomPrice(100);
            room.setRoomAvailable(true);
            roomlist.add(room);

            Room room1 = new Room();
            room1.setRoomNumber(doubleroomNo++);
            room1.setRoomType("Double Room");
            room1.setBedType("Double Bed");
            room1.setRoomPrice(150);
            room1.setRoomAvailable(true);
            roomlist.add(room1);

            Room room2 = new Room();
            room2.setRoomNumber(deluxeroomNo++);
            room2.setRoomType("Deluxe Room");
            room2.setBedType("Queen Bed");
            room2.setRoomPrice(200);
            room2.setRoomAvailable(true);
            roomlist.add(room2);

            Room room3 = new Room();
            room3.setRoomNumber(suiteroomNo++);
            room3.setRoomType("Suite");
            room3.setBedType("King Bed");
            room3.setRoomPrice(250);
            room3.setRoomAvailable(true);
            roomlist.add(room3);

            Hotel.setRoomList(roomlist);

        }
    }

    /**
     * Initializing Spa Services
     */
    private static void initializeHotelServiceList() {

        ArrayList<SpaService> spaServices = new ArrayList<>();

        SpaService massage = new SpaService();
        massage.setServiceName("Massage Service");
        massage.setPrice(100);
        massage.setServiceNumber(1);
        spaServices.add(massage);

        SpaService massage1 = new SpaService();
        massage1.setServiceName("Manicure");
        massage1.setPrice(80);
        massage1.setServiceNumber(2);
        spaServices.add(massage1);

        SpaService massage2 = new SpaService();
        massage2.setServiceName("Pedicure");
        massage2.setPrice(80);
        massage2.setServiceNumber(3);
        spaServices.add(massage2);

        Hotel.setServiceList(spaServices);
    }


    /**
     * Initialize Food Item list
     */
    private static void initializeFoodItems() {

        ArrayList<Food> FooServices = new ArrayList<>();
        Food food = new Food();
        food.setItemName("Egg Omlette");
        food.setPrice(5);
        food.setItemNumber(1);
        FooServices.add(food);


        Food food1 = new Food();
        food1.setItemName("Fries");
        food1.setPrice(6);
        food1.setItemNumber(2);
        FooServices.add(food1);

        Food food2 = new Food();
        food2.setItemName("Burger");
        food2.setPrice(10);
        food2.setItemNumber(3);
        FooServices.add(food2);

        Food food3 = new Food();
        food3.setItemName("Pizza");
        food3.setPrice(12);
        food3.setItemNumber(4);
        FooServices.add(food3);

        Food food4 = new Food();
        food4.setItemName("Sandwich");
        food4.setPrice(12);
        food4.setItemNumber(5);
        FooServices.add(food4);

        Hotel.setFoodItems(FooServices);

    }
}
