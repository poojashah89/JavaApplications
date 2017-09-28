package com.sjsu.architects;

/**
 * Created by tranpham on 8/2/17.
 */
public class MemberBillControllerProxy {
    BillController billController = new BillController();
    Member member=null;

    public MemberBillControllerProxy(Member member){
        this.member=member;
    }

    public void viewUnpaidBooking(){
        billController.viewUnpaidBookingOfMember(member);
    }

    public void pay(int billId){
        billController.payBill(billId);
    }
}
