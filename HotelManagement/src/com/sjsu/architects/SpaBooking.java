package com.sjsu.architects;

import java.util.ArrayList;
import java.util.HashMap;

public class SpaBooking extends Booking {

    private static HashMap<Integer , SpaBooking> spaService = new HashMap<Integer, SpaBooking>();

    ArrayList<SpaService> spaServices = new ArrayList<>();

    public ArrayList<SpaService> getSpaServices() {
        return spaServices;
    }

    public void setSpaServices(ArrayList<SpaService> spaServices) {
        this.spaServices = spaServices;
    }

    public static HashMap<Integer, SpaBooking> getSpaService() {
        return spaService;
    }

    public static void setSpaService(HashMap<Integer, SpaBooking> spaService) {
        SpaBooking.spaService = spaService;
    }

    public static ArrayList<SpaBooking> getUnpaidBookingOfMember(int memberId){
        ArrayList<SpaBooking> memberBookingList= new ArrayList<SpaBooking>();
        for (SpaBooking booking:spaService.values()){
            if(booking.getMember().getId()==memberId && !booking.isPaid())
                memberBookingList.add(booking);
        }
        return memberBookingList;
    }

    @Override
    public double cost() {
        double totalCost=0;
        for(SpaService service:spaServices)
            totalCost+=service.getPrice();
        return totalCost;
    }

    @Override
    public void paid() {
        spaService.get(getBookingID()).isPaid=true;
    }

}
