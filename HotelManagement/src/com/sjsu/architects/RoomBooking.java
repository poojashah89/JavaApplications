package com.sjsu.architects;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomBooking extends Booking  {

    ArrayList<Room> bookRoom = new ArrayList<>();
    private static HashMap<Integer , MemberBookingProxy> memberRoomBookings = new HashMap<Integer, MemberBookingProxy>();

    public ArrayList<Room> getBookedRooms() {
        return bookRoom;
    }

    public void setBookRoom(ArrayList<Room> bookRoom) {
        this.bookRoom = bookRoom;
    }

    /**
     * Checkin
     * @param bookingID
     */
    public void checkin(int bookingID){
        MemberBookingProxy booking= memberRoomBookings.get(bookingID);

        if(booking != null) {
            RoomBooking roomBooking = (RoomBooking) booking.bookings();
            ArrayList<Room> rooms = roomBooking.getBookedRooms();

            //Actual list of rooms
            int size = rooms.size();

            if (size > 0) {
                System.out.println("Customer is checked in to : ");
                for (Room room : rooms) {
                    if (!room.isRoomAvailable()) {
                        //Room is already reserved for user
                        room.setCheckedinRoom(true);
                    }
                    System.out.println(room.getRoomNumber());
                }
            }
        } else {
            System.out.println("No Booking found");
        }

    }


    public void checkout(int bookingID){
        MemberBookingProxy booking= memberRoomBookings.get(bookingID);

        if(booking != null) {
            RoomBooking roomBooking = (RoomBooking) booking.bookings();
            ArrayList<Room> rooms = roomBooking.getBookedRooms();

            //Actual list of rooms
            int size = rooms.size();

            if (size > 0) {
                if (roomBooking.isPaid) {
                    for (Room room : rooms) {
                        room.setRoomAvailable(true);
                        room.setCheckedinRoom(false);
                        System.out.println("Customer is checked out successfully ");
                    }
                } else {
                    System.out.println("Customer has not paid the amount");
                }
            }
        } else {
            System.out.println("No Booking found");
        }
    }


    public static HashMap<Integer, MemberBookingProxy> getMemberRoomBookings() {
        return memberRoomBookings;
    }

    public static void setMemberRoomBookings(HashMap<Integer, MemberBookingProxy> memberRoomBookings) {
        RoomBooking.memberRoomBookings = memberRoomBookings;
    }

    /**
     * Get Unpaid booking of member
     * @param memberId
     * @return
     */
    public static ArrayList<RoomBooking> getUnpaidBookingOfMember(int memberId){
        ArrayList<RoomBooking> memberRoomBookingList= new ArrayList<RoomBooking>();
        for (MemberBookingProxy booking:memberRoomBookings.values()){
            RoomBooking roomBooking = (RoomBooking)booking.bookings();
            if(roomBooking.getMember().getId()==memberId && !roomBooking.isPaid())
                memberRoomBookingList.add(roomBooking);
        }
        return memberRoomBookingList;
    }

//    /**
//     * Get unpaid romm booking details
//     * @param roomNumber
//     * @return
//     */
////    public static RoomBooking getUnpaidRoomBooking(int roomNumber){
////
////        for (MemberBookingProxy booking:memberRoomBookings.values()){
////            RoomBooking roomBooking = (RoomBooking)booking.bookings();
////            for(Room room:roomBooking.getBookedRooms())
////                if (room.getRoomNumber()==roomNumber)
////                    return roomBooking;
////        }
////        return null;
////    }

    @Override
    public void paid() {
        Booking booking= memberRoomBookings.get(getBookingID()).bookings();
        booking.isPaid=true;
    }

    @Override
    public double cost() {
        double totalCost=0;
        for (Room room:getBookedRooms()){
            totalCost+=room.getRoomPrice();
        }
        return totalCost;
    }
}
