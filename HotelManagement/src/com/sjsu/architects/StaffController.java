package com.sjsu.architects;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by sneha on 7/31/17.
 */
public class StaffController {

    /**
     * Add a staff
     * @param manager
     */
    public void createStaff(Manager manager){

        IdentityController idController = IdentityController.getInstance();

        Staff staff = new Staff();

        System.out.println("Enter staff name");
        Scanner scan = new Scanner(System.in);
        staff.setName(scan.nextLine());

        staff.setId(++IdentityController.identity);

        System.out.println("Enter staff salary");
        scan = new Scanner(System.in);
        staff.setSalary(scan.nextDouble());

        System.out.println("Enter user name");
        scan = new Scanner(System.in);
        staff.setUsername(scan.nextLine());

        System.out.println("Enter password");
        scan = new Scanner(System.in);
        staff.setPassword(scan.nextLine());


        ArrayList<Person> staffList = manager.getStaffList();

        staffList.add(staff);
        idController.setAccountList(staffList);
    }


    /**
     * Delete a staff
     * @param manager
     */
    public void deleteStaff(Manager manager){

        System.out.println("Enter staff id to delete");

        int id  = Bootstrap.handleUserInput();

        IdentityController identityController = IdentityController.getInstance();
        ArrayList<Person> staffList = identityController.getAccountList();

        for(Person person : staffList){
            if(person.getId() == id){
                staffList.remove(person);
                break;
            }
        }

    }

    /**
     * View staff member
     * @param manager
     */
    public void viewAllStaff(Manager manager){
        IdentityController identityController = IdentityController.getInstance();
        ArrayList<Person> staffList = identityController.getAccountList();
        System.out.println(" --------------------------------------------- ");
        System.out.println("|   Id  |  First Name        |    Salary     | ");
        System.out.println(" ---------------------------------------------- ");
        for(Person person : staffList){
           if(person instanceof Staff){
               System.out.println("|   "+person.getId()+"   |      "+person.getName()+"         |     "+((Staff) person).getSalary()+"     | ");
           }
        }
       // System.out.println(" ---------------------------------------------");

    }

    /**
     * Update staff member
     * @param manager
     */
    public void updateStaff(Manager manager){

        System.out.println("Enter staff id to update");

        int id  = Bootstrap.handleUserInput();

        System.out.println("Enter Updated staff name ");
        Scanner scan = new Scanner(System.in);
        String staffName = scan.nextLine();

        System.out.println("Enter Updated staff salary");
        scan = new Scanner(System.in);
        Double salary = scan.nextDouble();

        IdentityController identityController = IdentityController.getInstance();
        ArrayList<Person> staffList = identityController.getAccountList();

        for(Person person : staffList){
            if(person.getId() == id){
                staffList.remove(person);
                Staff staff = new Staff();
                staff.setId(id);
                staff.setName(staffName);
                staff.setSalary(salary);
                staffList.add(staff);
                break;
            }
        }
        identityController.setAccountList(staffList);

    }

    // Test Adding Staff, Deleting Staff and Updating Staff

    public static void main(String[] argC) {

        Manager manager = new Manager();
        StaffController controller = new StaffController();
        controller.createStaff(manager);
        controller.createStaff(manager);

        IdentityController identityController = IdentityController.getInstance();
        ArrayList<Person> staffList = identityController.getAccountList();


        for (Person person : identityController.getAccountList()) {
            //typecasting Staff as person as only the staff has the salary details
            Staff staff = (Staff) person;
            System.out.println(staff.getId() + ":" + staff.getName()+ ":" + staff.getSalary());

        }

        controller.deleteStaff(manager);
        for (Person person : identityController.getAccountList()) {
            Staff staff = (Staff) person;
            System.out.println(staff.getId() + ":" + staff.getName()+ ":" + staff.getSalary());

        }

        controller.updateStaff(manager);
        for (Person person : identityController.getAccountList()) {
            Staff staff = (Staff) person;
            System.out.println(staff.getId() + ":" + staff.getName()+ ":" + staff.getSalary());

        }

    }
}
