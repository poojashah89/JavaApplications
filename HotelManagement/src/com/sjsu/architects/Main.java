package com.sjsu.architects;

import java.util.Scanner;

public class Main {

    public static int iUserType = 0;

    RoomBooking roomBookings = new RoomBooking();

    public static void main(String[] args) {

        // Creating Hotel

        Hotel hotel = new Hotel();
        hotel.setHotelName("Carton Hotel");


        //Creating manager

        IdentityController identityController = IdentityController.getInstance();
        identityController.createSampleData();
        Manager manager = (Manager)identityController.getAccountById(1);

        //initialize the system
        Bootstrap.initialize();

        do {
            System.out.println("\n\n ---------------------- Welcome to " + hotel.getHotelName() + " ----------------------");
            System.out.println(" 1. Check Room Availability | 2. Sign In  | 3. Sign Up Member | 0. Exit \n");
            int iOperation = Bootstrap.handleUserInput();
            if (iOperation == 1) {
                MemberController memberOperations = new MemberController();
                memberOperations.checkRoomAvailablility();

            }
            else if (iOperation == 2) {

                do {
                    System.out.println("\n Please Select the Account Type you want to Sign In as ");
                    System.out.println("  --------------------------------------------------------------------");
                    System.out.println(" 1. Manager   | 2. Staff   | 3. Carton Hotel Member | 0. Exit   ");

                    int UserType = Bootstrap.handleUserInput();
                    IdentityController login = IdentityController.getInstance();
                    if(UserType == 0){
                        break;
                    }
                    int userId = login.signIn();

                    if(userId != -1) {
                        if (UserType == 1) {
                            ManagerMenu(manager);
                        } else if (UserType == 2) {
                            StaffMenu();
                        } else if (UserType == 3) {
                            CustomerMenu(userId);
                        } else if (UserType == 0) {
                            break;
                        } else {
                            break;
                        }
                    }
                }
                while (1 != 0);

            }
            else if (iOperation == 3)
            {


                IdentityController signup = IdentityController.getInstance();
                int userId = signup.signUp(3);
                System.out.println("Please note down Customer ID " + userId);
                CustomerMenu(userId);

                /*do {

                    System.out.println("\n Please Enter The Account Type for sign up");
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println(" 1. Carton Hotel Member  | 0. Exit");

                    int UserType = Bootstrap.handleUserInput();
                    IdentityController signup = IdentityController.getInstance();
                    if (UserType == 1) {
                        int userId =  signup.signUp(1);
                        StaffMenu();
                    } else if (UserType == 2) {
                        int userId = signup.signUp(2);
                        CustomerMenu(userId);
                    }
                    else if (UserType == 0){
                        break;
                    }
                    else
                    {
                        break;
                    }
                }
                while (1 != 0);*/


            }
            else if (iOperation == 0)
            {
                break;
            }
            else
            {
                break;
            }
        }while (1 !=0);

    }

    /**
     * Access Manager View
     * @param manager
     */
    public static void ManagerMenu(Manager manager) {
        do {
            System.out.println("\n Welcome " + manager.getName() + " (loggedin as : Manager)");
            System.out.println(" -----------------------------------------------------------------------");
            System.out.println(" 1. Add Staff Member | 2. Delete Staff Member  | 3. Update Staff Member  | 4. View Staff  |" +
                    " 5. Add Room | 6. Delete Room | 7. Update Room | 8. View Room | 9. Generate Promotion | 0. Exit ");

            int iOperation = Bootstrap.handleUserInput();
            StaffController staffcontroller = new StaffController();
            RoomController roomcontroller = new RoomController();
            PromotionController promotionController = PromotionController.getInstance();
            if (iOperation == 1) {
              //  staffcontroller.createStaff(manager);
                IdentityController signup = IdentityController.getInstance();
                int userId = signup.signUp(2);

            } else if (iOperation == 2) {
                staffcontroller.deleteStaff(manager);

            } else if (iOperation == 3) {
                staffcontroller.updateStaff(manager);

            } else if (iOperation == 4){
                staffcontroller.viewAllStaff(manager);

            }else if (iOperation == 5){
                roomcontroller.createRoom(manager);

            }else if (iOperation == 6){
                roomcontroller.deleteRoom(manager);

            }else if (iOperation == 7){
                roomcontroller.updateRoom(manager);

            }else if (iOperation == 8) {
                MemberController memberOperations = new MemberController();
                memberOperations.managerViewRoom();

            }else if (iOperation == 9){
              //  Member memberPromotion = new Member(promotionController);
                promotionController.createPromotion();

            }else if (iOperation == 0){
                break;
            }
            else {
                break;
            }

        } while (1 != 0);

    }

    /**
     * Access Staff Menu
     */
    public static void StaffMenu()
    {

        do {
            MemberController memberOperations = new MemberController();
            System.out.println("\n Welcome (loggedin as : Staff) ");
            System.out.println(" -----------------------------------------------------------------------");
            System.out.println(" 1. Check For Room Availability | 2. Book Room  | 3. Member Sign Up " +
                    "|  4. Check In Customer | 5. Check Out Customer | 6. Generate Bill " +
                    "|  7. Book Spa | 8. Order Food | 0. Exit ");

            int iOperation = Bootstrap.handleUserInput();

            if (iOperation == 1) {
                memberOperations.checkRoomAvailablility();
            } else if (iOperation == 2) {
                System.out.println("Enter Member ID ");

                int userID = Bootstrap.handleUserInput();

                IdentityController accountList = IdentityController.getInstance();
                Member member = (Member) accountList.getAccountById(userID);
                if(member != null)
                    memberOperations.bookRoom(member);
                else
                    System.err.println("Member not found");
            } else if (iOperation == 3) {
                IdentityController signup = IdentityController.getInstance();
                int userId = signup.signUp(3);
                System.out.println("Please note down the Member ID : " + userId);

            } else if (iOperation == 4){
                System.out.println("Enter Booking ID ");

                int bookingid = Bootstrap.handleUserInput();

                RoomBooking checkin = new RoomBooking();
                checkin.checkin(bookingid);

            } else if (iOperation == 5){
                System.out.println("Enter Booking ID ");
                int bookingid = Bootstrap.handleUserInput();
                RoomBooking checkin = new RoomBooking();
                checkin.checkout(bookingid);

            } else if (iOperation == 6){
                BillController controller = new BillController();
                controller.run();

            } else if (iOperation == 7){
                System.out.println("\n Enter Booking ID ");

                int bookingid = Bootstrap.handleUserInput();

                StandardBookingController spa = new StandardBookingController();
                spa.bookSpaServices(bookingid);

            } else if (iOperation == 8){
                System.out.println("\n Enter Booking ID ");

                int bookingid = Bootstrap.handleUserInput();

                StandardBookingController food = new StandardBookingController();
                food.orderFood(bookingid);

            } else if (iOperation == 0){
                break;
            }
            else {
                break;
            }

        } while (1 != 0);


    }

    /**
     * Access Customer Menu
     * @param userId
     */
    public static void CustomerMenu(int userId)
    {

        IdentityController accountList = IdentityController.getInstance();
        Member member = (Member) accountList.getAccountById(userId);

        do {
            System.out.println("\n Welcome Carton Hotel Member " + member.getName());
            System.out.println(" -----------------------------------------------------------------------");
            System.out.println(" 1. Check Room Availability | 2. Book Room  | 3. Cancel Booking  " +
                    "\n 4. View Booking  | 5. Bill & Payment | 0. Exit ");

            int iOperation = Bootstrap.handleUserInput();

            MemberController memberOperations = new MemberController();
            if (iOperation == 1) {
                memberOperations.checkRoomAvailablility();
            } else if (iOperation == 2) {
                memberOperations.bookRoom(member);

            } else if (iOperation == 3) {
                System.out.println("\n Enter Booking ID: ");
                int bookingID = Bootstrap.handleUserInput();
                memberOperations.cancelBooking(bookingID);
            } else if (iOperation == 4){
                System.out.println("\n Enter Booking ID: ");
                int id = Bootstrap.handleUserInput();
                memberOperations.viewRoomBookings(id);
            } else if (iOperation == 5){
                BillController controller = new BillController();
                controller.run();
            } else if (iOperation == 0){
                break;
            }
            else {
                break;
            }
        } while (1 != 0);
    }
}
