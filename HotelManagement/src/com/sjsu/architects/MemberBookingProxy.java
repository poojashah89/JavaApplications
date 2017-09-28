package com.sjsu.architects;

public class MemberBookingProxy {

    StandardBookingController bookingc = new StandardBookingController();

    public Booking bookings(){
        return bookingc.getRoomBookings();

    }

    public void book(Booking booking){
        bookingc.bookRoom(booking);
    }


}
