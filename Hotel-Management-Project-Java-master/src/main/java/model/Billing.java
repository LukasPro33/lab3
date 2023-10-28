package model;

import java.util.List;

public class Billing {
    public static void generateBill(Room room, Room.RoomType roomType, int roomNumber) {
        double roomCharge = calculateRoomCharge(roomType);
        double foodCharge = calculateFoodCharge(room.food);

        double totalAmount = roomCharge + foodCharge;

        printBill(room,roomType, roomNumber, roomCharge, foodCharge, totalAmount);
    }

    private static double calculateRoomCharge(Room.RoomType roomType) {
        return switch (roomType) {
            case LUXURY_DOUBLE -> 4000;
            case DELUXE_DOUBLE -> 3000;
            case LUXURY_SINGLE -> 2200;
            case DELUXE_SINGLE -> 1200;
        };
    }
    private static double calculateFoodCharge(List<Food> foodItems) {
        double foodCharge = 0;
        for (Food item : foodItems) {
            foodCharge += item.price;
        }
        return foodCharge;
    }

    private static void printBill(Room room,Room.RoomType roomType, int roomNumber, double roomCharge, double foodCharge, double totalAmount) {
        String[] list = {"Sandwich", "Pasta", "Noodles", "Coke"};

        System.out.println("\n*******");
        System.out.println(" Bill:-" + roomNumber +" "+ roomType.toString());
        System.out.println("*******");

        System.out.println("\nmodel.Room Charge - " + roomCharge);
        System.out.println("\n===============");
        System.out.println("model.Food Charges:- " +foodCharge);
        System.out.println("===============");
        System.out.println("Item   Quantity    Price");
        System.out.println("-------------------------");

        List<Food> foodItems = room.food;
        for (Food item : foodItems) {
            System.out.printf("%-10s%-10s%-10s%n", list[item.itemNo - 1], item.quantity, item.price);
        }

        System.out.println("\nTotal Amount- " + totalAmount);
    }
}