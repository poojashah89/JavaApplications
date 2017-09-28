package com.sjsu.architects;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sneha on 7/31/17.
 */
public class RoomController {

    /**
     * Add a Room
     * @param manager
     */
    public void createRoom(Manager manager){

        ArrayList<Room> roomlist = Hotel.getRoomList();
        Room room = new Room();

        System.out.println("Enter Room No.");

        int roomNo = Bootstrap.handleUserInput();
        for (Room roomsearch : roomlist)
        {
            if (roomsearch.getRoomNumber() == roomNo)
            {
                System.out.println(" Room Number Already exists");
                return;
            }

        }
        room.setRoomNumber(roomNo);

        System.out.println("Enter Room Type ");
        Scanner scan = new Scanner(System.in);
        room.setRoomType(scan.nextLine());

        System.out.println("Enter Bed Type");
        scan = new Scanner(System.in);
        room.setBedType(scan.nextLine());

        System.out.println("Enter Room Price");
        scan = new Scanner(System.in);
        room.setRoomPrice(scan.nextDouble());

        room.setRoomAvailable(true);

        roomlist.add(room);
        Hotel.setRoomList(roomlist);

    }

    /**
     * Delete a room
     * @param manager
     */
    public void deleteRoom(Manager manager){

        System.out.println("\n Enter Room Number to delete");

        int id  = Bootstrap.handleUserInput();
        ArrayList<Room> roomList = Hotel.getRoomList();

        for(Room room : roomList){
            if(room.getRoomNumber() == id){
                roomList.remove(room);
                break;
            }
        }

        Hotel.setRoomList(roomList);
    }

    /**
     * Update a room
     * @param manager
     */
    public void updateRoom(Manager manager){

        System.out.println("Enter Room Number to update");

        int roomNo  = Bootstrap.handleUserInput();

        System.out.println("Enter Updated Room Type ");
        Scanner scan = new Scanner(System.in);
        String roomType = scan.nextLine();

        System.out.println("Enter Updated Bed Type ");
        scan = new Scanner(System.in);
        String bedType = scan.nextLine();

        System.out.println("Enter Updates Room price");
        scan = new Scanner(System.in);
        Double price = scan.nextDouble();

        ArrayList<Room> roomlist = Hotel.getRoomList();

        for(Room room : roomlist){
            if(room.getRoomNumber() == roomNo){
                roomlist.remove(room);
                Room roomupdate = new Room();
                roomupdate.setRoomNumber(roomNo);
                roomupdate.setRoomType(roomType);
                roomupdate.setBedType(bedType);
                roomupdate.setRoomPrice(price);
                break;
            }
        }

        Hotel.setRoomList(roomlist);

    }




    // Testing For Room Add, Delete Update
    public static void main(String[] argC) {

        Manager manager = new Manager();
        Hotel hotel = new Hotel();
        RoomController controller = new RoomController();

        controller.createRoom(manager);
        controller.createRoom(manager);
        controller.createRoom(manager);
        for (Room room : hotel.getRoomList()) {
            System.out.println(room.getRoomNumber() + ":" + room.getRoomType() + ":" + room.getBedType() +":"+ room.getRoomPrice());
        }

        controller.deleteRoom(manager);
        for (Room room : hotel.getRoomList()) {
            System.out.println(room.getRoomNumber() + ":" + room.getRoomType()+ ":" + room.getBedType() +":"+ room.getRoomPrice());

        }

        controller.updateRoom(manager);
        for (Room room : hotel.getRoomList()) {
            System.out.println(room.getRoomNumber() + ":" + room.getRoomType()+ ":" + room.getBedType() +":"+ room.getRoomPrice());

        }


    }
}
