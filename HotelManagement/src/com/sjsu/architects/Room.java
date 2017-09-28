package com.sjsu.architects;

public class Room {

    private int roomNumber;
    private double roomPrice = 0.0;
    private boolean roomAvailable = true;
    private boolean checkedinRoom = false;
    private String roomType = null;
    private String bedType = null;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public boolean isRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(boolean roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public boolean isCheckedinRoom() {
        return checkedinRoom;
    }

    public void setCheckedinRoom(boolean checkedinRoom) {
        this.checkedinRoom = checkedinRoom;
    }
}
