package com.sjsu.architects;

/**
 * Created by sneha on 7/31/17.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class IdentityController {

    private static IdentityController instance = null;

    protected IdentityController(){

    }

    /**
     * Constructor
     * @return
     */
    public static IdentityController getInstance(){
        if(instance == null)
            instance = new IdentityController();
        return instance;
    }
    ArrayList<Person> accountList = new ArrayList<>();

    public ArrayList<Person> getAccountList() {
        return accountList;
    }

    public static int identity = 0;
    PromotionController promotionController = PromotionController.getInstance();


    /**
     * Sign up a member
     * @param accountType
     * @return
     */
    public int signUp(int accountType) {
        int returnID = -1;
        //console for input
        System.out.println("\n Please Enter User Name ");
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();
        System.out.println(" Please Enter Password ");
        scan = new Scanner(System.in);
        String passWord = scan.nextLine();
        System.out.println(" Please Enter Full Name ");
        scan = new Scanner(System.in);
        String fullname = scan.nextLine();

        switch (accountType) {
            case 1:
                Manager manager = new Manager();
                manager.setId(++identity);
                manager.username = userName;
                manager.password = passWord;
                manager.name = fullname;
                accountList.add(manager);
                returnID = manager.getId();
            case 2:
                System.out.println(" Please Enter the salary");
                scan = new Scanner(System.in);
                Double salary = scan.nextDouble();
                Staff staff = new Staff();
                staff.setId(++identity);
                staff.username = userName;
                staff.password = passWord;
                staff.name = fullname;
                staff.setSalary(salary);
                accountList.add(staff);
                returnID = staff.getId();
            case 3:
                Member member = new Member(promotionController); //TODO promotion null fails
                member.setId(++identity);
                member.username = userName;
                member.password = passWord;
                member.name = fullname;
                accountList.add(member);
                returnID = member.getId();
        }

        return returnID;
    }


    public void signUpStaff() {
        signUp(2);
    }

    public void signUpMember() {
        signUp(3);
    }

    public void signUpManager() {
        signUp(1);
    }

    /**
     * Sign in a member
     * @return
     */
    public int signIn(){
        System.out.println(" Please Enter User Name ");
        Scanner scan = new Scanner(System.in);
        String userName = scan.nextLine();
        System.out.println(" Please Enter Password ");
        scan = new Scanner(System.in);
        String passWord = scan.nextLine();
        for(Person person: accountList)
        {
            if((person.getUsername().equals(userName)) && (person.getPassword().equals(passWord)))
            {
                return person.getId();
            }
        }
        System.out.println(" User Name and Password does not match  ");
        return -1;
    }

    /**
     * Get Account by id
     * @param id
     * @return
     */
    public Person getAccountById(int id) {
        for (Person person : accountList) {
            if (person.getId() == id)
                return person;
        }
        return null;
    }


    /**
     * Create Sample Data
     */
    public void createSampleData(){
        Manager manager = new Manager();
        manager.setId(++identity);
        manager.username = "admin";
        manager.password = "admin";
        manager.name = "Mr. Carton";
        accountList.add(manager);

        Staff staff = new Staff();
        staff.setId(++identity);
        staff.username = "staff";
        staff.password = "staff";
        staff.name = "Johny";
        staff.setSalary(450);
        accountList.add(staff);

        Member member = new Member(PromotionController.getInstance());
        member.setId(++identity);
        member.username = "member";
        member.password = "member";
        member.name = "The Member";
        accountList.add(member);

        Member member2 = new Member(PromotionController.getInstance());
        member2.setId(++identity);
        member2.username = "member2";
        member2.password = "member2";
        member2.name = "The Member 2";
        accountList.add(member2);
    }

    public void setAccountList(ArrayList<Person> staffList){
        this.accountList=staffList;
    }
}
