package com.sjsu.architects;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by sneha on 7/30/17.
 */
public class Booking {

    private int bookingID;
    private Member member  = null;
    private Date dateOfBooking;
    private Date endDateOfBooking;
    protected boolean isPaid=false;
    private String description="";

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID() {
        Random random = new Random();
        this.bookingID = random.nextInt(1000);
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Date getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(Date dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Date getEndDateOfBooking() {
        return endDateOfBooking;
    }

    public void setEndDateOfBooking(Date endDateOfBooking) {
        this.endDateOfBooking = endDateOfBooking;
    }

    public double cost(){
        return 0;
    };

    public String getDescription(){
        return description;
    }

    public boolean isPaid(){
        return isPaid;
    }

    public void paid(){
        isPaid=true;
    }

    public void appendDescription(String content){
        if (!description.isEmpty())
            description+="|";
        description+=" "+content;
    }
}
