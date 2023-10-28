package model;

import java.util.Scanner;

public class Order {
    private static final Scanner sc = new Scanner(System.in);

    public static void placeOrder(Room room) {
        try {
            System.out.println("\n==========\n   Menu:  \n==========\n\n1.Sandwich\tRs.50\n2.Pasta\t\tRs.60\n3.Noodles\tRs.70\n4.Coke\t\tRs.30\n");

            do {
                int item = sc.nextInt();
                System.out.print("Quantity- ");
                int quantity = sc.nextInt();

                room.addFood(new Food(item, quantity));

                System.out.println("Do you want to order anything else? (y/n)");
            } while (sc.next().equalsIgnoreCase("y"));
        } catch (NullPointerException e) {
            System.out.println("model.Room not booked");
        } catch (Exception e) {
            System.out.println("Cannot be done");
        }
    }
}

