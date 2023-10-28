package main;

import model.Hotel;

import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            Hotel hotel = Hotel.loadHotelData("backup");
            Scanner sc = new Scanner(System.in);
            String wish;

            do {
                int choice = getMenuChoice(sc);

                switch (choice) {
                    case 1 -> hotel.displayRoomDetails(getRoomType(sc));
                    case 2 -> hotel.displayRoomAvailability(getRoomType(sc));
                    case 3 -> hotel.bookRoom(getRoomType(sc));
                    case 4 -> hotel.orderFood(getRoomType(sc),getRoomNumber(sc));
                    case 5 -> hotel.checkout(getRoomNumber(sc));
                    default -> System.out.println("Wrong choice");
                }

                wish = getWish(sc);
            } while ("Y".equals(wish));

            hotel.saveHotelData("backup");
        }

        private static int getMenuChoice(Scanner sc) {
            System.out.println("\nEnter your choice:\n1. Display room details\n2. Display room availability\n3. Book\n4. model.Order food\n5. Checkout\n6. Exit");
            return sc.nextInt();
        }

        private static int getRoomType(Scanner sc) {
            System.out.println("Choose room type:\n1. Luxury Double model.Room\n2. Deluxe Double model.Room\n3. Luxury Single model.Room\n4. Deluxe Single model.Room");
            return sc.nextInt();
        }

        private static int getRoomNumber(Scanner sc) {
            System.out.print("model.Room Number: ");
            return sc.nextInt();
        }

        private static String getWish(Scanner sc) {
            System.out.println("\nContinue: (Y/N)");
            String wish = sc.next().substring(0, 1).toUpperCase();
            while (!"Y".equals(wish) && !"N".equals(wish)) {
                System.out.println("Invalid Option");
                System.out.println("\nContinue: (Y/N)");
                wish = sc.next().substring(0, 1).toUpperCase();
            }
            return wish;
        }
}
