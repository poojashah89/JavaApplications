package com.sjsu.architects;

/**
 * Created by sneha on 7/30/17.
 */
import java.util.ArrayList;
public class Manager extends Person {

    private double salary;

    private ArrayList<Person> staffList = new ArrayList<>();

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<Person> getStaffList() {
        return staffList;
    }

    public void setStaffList(ArrayList<Person> staffList) {
        this.staffList = staffList;
    }



}
