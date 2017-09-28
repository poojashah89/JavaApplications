package com.sjsu.architects;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class MemberController {

    public MemberController(){

    }

    /**
     * Check Room availability
     */
    public void checkRoomAvailablility(){
        int totalAvailable = 0;
        int singleRoom = 0;
        int doubleRoom = 0;
        int deluxeRoom = 0;
        int suite = 0;

        //Actual list of rooms
        ArrayList<Room> roomAvailability = Hotel.getRoomList();
        int size = roomAvailability.size();

        if(size > 0) {
            for (Room ifRoomAvailable : roomAvailability) {
                if (ifRoomAvailable.isRoomAvailable()) {
                    if(ifRoomAvailable.getRoomType().equals("Single Room")){
                        singleRoom++;
                    } else if(ifRoomAvailable.getRoomType().equals("Double Room")){
                        doubleRoom++;
                    } else if(ifRoomAvailable.getRoomType().equals("Deluxe Room")){
                        deluxeRoom++;
                    } else if(ifRoomAvailable.getRoomType().equals("Suite")){
                        suite++;
                    }
                    totalAvailable++;
                }
            }
        }

        System.out.println(" -------------------------------------------------- ");
        System.out.println(" |   Room Type | " + "Bed Type | " + "Price | " + " Availability |");
        System.out.println(" --------------------------------------------------");
        System.out.println(" |   Single    | " + "Single   | " + "$100  |     " + singleRoom + "        | ");
        System.out.println(" |   Double    | " + "Double   | " + "$150  |     " + doubleRoom + "        | ");
        System.out.println(" |   Deluxe    | " + "Queen    | " + "$200  |     " + deluxeRoom + "        | ");
        System.out.println(" |   Suite     | " + "King     | " + "$250  |     " + suite + "        | ");
        System.out.println(" --------------------------------------------------");
        System.out.println(" Total Rooms Available: " + totalAvailable);

    }

    /**
     * Manager View  Room's
     */

    public void managerViewRoom(){
        int totalAvailable = 0;
        int singleRoom = 0;
        int totalSingleRooms = 0;
        int doubleRoom = 0;
        int totalDoubleRooms = 0;
        int deluxeRoom = 0;
        int totaldeluxeRoom = 0;
        int suite = 0;
        int totalSuite = 0;

        //Actual list of rooms
        ArrayList<Room> roomAvailability = Hotel.getRoomList();
        int size = roomAvailability.size();

        if(size > 0) {
            for (Room ifRoomAvailable : roomAvailability) {
                //if (ifRoomAvailable.isRoomAvailable()) {
                    if(ifRoomAvailable.getRoomType().equals("Single Room")){
                        if(ifRoomAvailable.isRoomAvailable()) {
                            singleRoom++;
                            totalAvailable++;
                        }
                        totalSingleRooms++;
                    } else if(ifRoomAvailable.getRoomType().equals("Double Room")){
                        if(ifRoomAvailable.isRoomAvailable()) {
                            doubleRoom++;
                            totalAvailable++;
                        }
                        totalDoubleRooms++;
                    } else if(ifRoomAvailable.getRoomType().equals("Deluxe Room")){
                        if(ifRoomAvailable.isRoomAvailable()){
                            deluxeRoom++;
                            totalAvailable++;
                        }
                        totaldeluxeRoom++;
                    } else if(ifRoomAvailable.getRoomType().equals("Suite")){
                        if(ifRoomAvailable.isRoomAvailable()){
                            suite++;
                            totalAvailable++;
                        }
                       totalSuite++;
                    }

                //}
            }
        }

        System.out.println(" ------------------------------------------------------------------------ ");
        System.out.println(" |   Room Type | " + "Bed Type | " + "Price | " + " Availability |"        +" Total Rooms  |"  );
        System.out.println(" ------------------------------------------------------------------------");
        System.out.println(" |   Single    | " + "Single   | " + "$100  |     " + singleRoom + "        |      "  +totalSingleRooms+ "      |" );
        System.out.println(" |   Double    | " + "Double   | " + "$150  |     " + doubleRoom + "        |      "  +totalDoubleRooms+ "      |" );
        System.out.println(" |   Deluxe    | " + "Queen    | " + "$200  |     " + deluxeRoom + "        |      "  + totaldeluxeRoom+ "      |" );
        System.out.println(" |   Suite     | " + "King     | " + "$250  |     " + suite + "        |      " + totalSuite+"      |");
        System.out.println(" -------------------------------------------------------------------------");
        System.out.println(" Total Rooms  " + size);
        System.out.println(" Total Available Rooms " + totalAvailable);

    }





    /**
     * Book a room for member
     * @param member
     * @return
     */
    public void bookRoom(Member member){

        MemberBookingProxy bookingProxy = new MemberBookingProxy();

        Room room = new Room();


        System.out.println(" Please Enter Room Type : 1. Single Room | 2. Double Room | 3. Deluxe Room | 4. Suite ");

        int UserType = Bootstrap.handleUserInput();

        if (UserType == 1) {
            room.setRoomType("Single Room");

        } else if (UserType == 2) {
            room.setRoomType("Double Room");
        } else if (UserType == 3) {

            room.setRoomType("Deluxe Room");
        } else if (UserType == 4) {

            room.setRoomType("Suite");
        }
        else {
            room.setRoomType("N/A");
        }

        System.out.println(" Please Enter Number of Rooms ");

        int number = Bootstrap.handleUserInput();

        System.out.println(" Please Enter Start Date of Booking (format : yyyy/mm/dd)");
        Scanner scanner = new Scanner(System.in);
        Date startDate = new Date(scanner.nextLine());

        System.out.println(" Please Enter End Date of Booking (format : yyyy/mm/dd)");
        scanner = new Scanner(System.in);
        Date endDate = new Date(scanner.nextLine());

        //Actual list of rooms
        ArrayList<Room> roomAvailability = Hotel.getRoomList();

        //Number of rooms for customers
        ArrayList<Room> roomBookings = new ArrayList<>();

        int size = roomAvailability.size();

        if(size > number){
            RoomBooking booking = new RoomBooking();

            for(int i = 0 ; i< number ; i++) {
                for(Room ifRoomAvailable : roomAvailability){
                    if(ifRoomAvailable.isRoomAvailable() && ifRoomAvailable.getRoomType().equals(room.getRoomType())) {
                        ifRoomAvailable.setRoomAvailable(false);
                        roomBookings.add(ifRoomAvailable);

                        booking.appendDescription("\nRoom "+ifRoomAvailable.getRoomNumber());
                        booking.appendDescription(ifRoomAvailable.getRoomType());
                        booking.appendDescription(ifRoomAvailable.getBedType());
                        break;
                    }
                }
            }
            booking.setDateOfBooking(startDate);
            booking.setEndDateOfBooking(endDate);
            booking.setBookingID();
            booking.setMember(member);
            booking.setBookRoom(roomBookings);

            bookingProxy.book(booking);

            HashMap<Integer, MemberBookingProxy> list = new HashMap<>();
            list.put(booking.getBookingID(), bookingProxy);
            RoomBooking.setMemberRoomBookings(list);
            System.out.println("Your booking is confirmed. Please note down the booking ID for future reference : " + bookingProxy.bookings().getBookingID());
        } else {
            System.err.println(number + " Rooms are not available");
        }

    }

    /**
     * For customer view
     */
    public void viewRoomBookings(int bookingID){
        HashMap<Integer, MemberBookingProxy> bookings = RoomBooking.getMemberRoomBookings();

        if(bookings.size() > 0) {

            MemberBookingProxy bookingOfMember = bookings.get(bookingID);
            if(bookingOfMember == null){
                System.out.println("No Bookings found");
            } else {
                RoomBooking roomBooking = (RoomBooking) bookingOfMember.bookings();
                System.out.println(" Booking for Member : " + roomBooking.getMember().getName() +
                        "\n No of Rooms : " + roomBooking.getBookedRooms().size() +
                        "\n Stay : From " + roomBooking.getDateOfBooking() + " To: " + roomBooking.getEndDateOfBooking());

                for (Room room : roomBooking.getBookedRooms()) {
                    System.out.println(" " + room.getRoomType() + " " + room.getRoomPrice());
                }
            }
        } else {
            System.out.println("No Bookings found");
        }

    }

    /**
     * For Staff View
     */
    public void viewBookings(int bookingID){

        viewRoomBookings(bookingID);

        HashMap<Integer, SpaBooking> spaBooking = SpaBooking.getSpaService();
        SpaBooking booking = spaBooking.get(bookingID);
        if(booking != null) {
            for (SpaService spa : booking.getSpaServices()) {
                System.out.println(spa.getServiceName() + " " + spa.getPrice());
            }
        }

        HashMap<Integer, FoodBooking> foodBooking = FoodBooking.getFoodOrders();
        FoodBooking foodList = foodBooking.get(bookingID);
        if(foodList != null) {
            for (Food food : foodList.getFoodItems()) {
                System.out.println(food.getItemName() + " " + food.getPrice());
            }
        }

    }

    /**
     * Cancel Booking
     * @param bookingID
     */
    public void cancelBooking(int bookingID) {
        HashMap<Integer, MemberBookingProxy> bookings = RoomBooking.getMemberRoomBookings();
        ArrayList<Room> roomlist = Hotel.getRoomList();

        if(bookings.size() > 0) {

            MemberBookingProxy membeBooking = bookings.get(bookingID);
            if(membeBooking != null ){
                bookings.remove(bookingID);
                RoomBooking booking = (RoomBooking) membeBooking.bookings();

                System.out.println("\n Your booking with id " + bookingID + " is cancelled");
                ArrayList<Room> bookedRooms = booking.getBookedRooms();

                for(Room bookedrooms : bookedRooms) {
                    for (Room rooms : roomlist) {
                        if (bookedrooms.getRoomNumber() == rooms.getRoomNumber()) {
                            //rooms.setCheckedinRoom(false);
                            rooms.setRoomAvailable(true);
                        }
                    }
                }

                Hotel.setRoomList(roomlist);
            }


        }
        else
            System.out.println("\n Your booking with id " + bookingID + " is not FOUND");
    }


}
