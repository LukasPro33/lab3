package model;

import java.util.Scanner;

public class Customer {
    private final String name;
    private final String contact;
    private final String gender;

    public Customer(String name, String contact, String gender) {
        this.name = name;
        this.contact = contact;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }


    public static Customer enterCustomerDetails(Scanner sc) {
        System.out.print("Enter customer name: ");
        String name = sc.next();
        System.out.print("Enter contact number: ");
        String contact = sc.next();
        System.out.print("Enter gender: ");
        String gender = sc.next();
        return new Customer(name, contact, gender);
    }
}