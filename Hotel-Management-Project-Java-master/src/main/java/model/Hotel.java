package model;

import java.io.*;
import java.util.*;

public class Hotel implements RoomOperations, Serializable
{
    static Holder holder = new Holder();
    static Map<Integer, Room.RoomType> roomTypeMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);
    static void custDetails(int i, int roomNumber)
    {
        List<Customer> customers= new ArrayList<>();
        // if single room then only one customer
        customers.add(Customer.enterCustomerDetails(sc));
        // if double room then two customers
        if(i<3)
        {
            customers.add(Customer.enterCustomerDetails(sc));
        }

        switch (i) {
            case 1 -> holder.luxuryDoubleRoom[roomNumber] = new Room(customers, Room.RoomType.LUXURY_DOUBLE);
            case 2 -> holder.deluxeDoubleRoom[roomNumber] = new Room(customers, Room.RoomType.DELUXE_DOUBLE);
            case 3 -> holder.luxurySingleRoom[roomNumber] = new Room(customers, Room.RoomType.LUXURY_SINGLE);
            case 4 -> holder.deluxeSingleRoom[roomNumber] = new Room(customers, Room.RoomType.DELUXE_SINGLE);
            default -> System.out.println("Wrong option");
        }
    }


    public static Hotel loadHotelData(String fileName) {
        roomTypeMap.put(1, Room.RoomType.LUXURY_DOUBLE);
        roomTypeMap.put(2, Room.RoomType.DELUXE_DOUBLE);
        roomTypeMap.put(3, Room.RoomType.LUXURY_SINGLE);
        roomTypeMap.put(4, Room.RoomType.DELUXE_SINGLE);
        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fin);
                return (Hotel) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Hotel();
    }

    public void saveHotelData(String fileName) {
        try (FileOutputStream fout = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Error in writing " + e);
        }
    }

    @Override
    public void displayRoomDetails(int roomType) {
        String[] roomDescriptions = {
                "Number of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day: 4000",
                "Number of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day: 3000",
                "Number of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day: 2200",
                "Number of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day: 1200"
        };
            if (roomType >= 1 && roomType <= 4) {
                System.out.println(roomDescriptions[roomType - 1]);
            } else {
                System.out.println("Enter valid room type");
            }
    }

    @Override
    public void displayRoomAvailability(int roomType) {
            int count = 0;

            switch (roomType) {
                case 1 -> count = countAvailableRooms(holder.luxuryDoubleRoom);
                case 2 -> count = countAvailableRooms(holder.deluxeDoubleRoom);
                case 3 -> count = countAvailableRooms(holder.luxurySingleRoom);
                case 4 -> count = countAvailableRooms(holder.deluxeSingleRoom);
                default -> {
                    System.out.println("Enter valid option");
                    return;
                }
            }
            System.out.println("Number of rooms available : " + count);
        }
    static int countAvailableRooms(Object[] roomArray) {
        int count = 0;
        for (Object o : roomArray) {
            if (o == null)
                count++;
        }
        return count;
    }

    @Override
    public void bookRoom(int roomType) {
        int roomNumber;
        System.out.println("\nChoose room number from : ");

        switch (roomType) {
            case 1 -> roomNumber = selectAvailableRoom(holder.luxuryDoubleRoom);
            case 2 -> roomNumber = selectAvailableRoom(holder.deluxeDoubleRoom);
            case 3 -> roomNumber = selectAvailableRoom(holder.luxurySingleRoom);
            case 4 -> roomNumber = selectAvailableRoom(holder.deluxeSingleRoom);
            default -> {
                System.out.println("Enter valid room type");
                return;
            }
        }

        if (roomNumber != -1) {
            custDetails(roomType,roomNumber);
            System.out.println("model.Room Booked");
        } else {
            System.out.println("Invalid Option");
        }
    }



    @Override
    public void orderFood(int roomType, int roomNumber) {
        Room.RoomType mappedRoomType = roomTypeMap.get(roomType);
        if (mappedRoomType != null) {
            Order.placeOrder(getRoomByType(roomNumber, mappedRoomType));
        } else {
            System.out.println("Invalid room type");
        }
    }

    private Room getRoomByType(int roomNumber, Room.RoomType roomType) {
        switch (roomType) {
            case LUXURY_DOUBLE:
                return holder.luxuryDoubleRoom[roomNumber];
            case DELUXE_DOUBLE:
                return holder.deluxeDoubleRoom[roomNumber];
            case LUXURY_SINGLE:
                return holder.luxurySingleRoom[roomNumber];
            case DELUXE_SINGLE:
                return holder.deluxeSingleRoom[roomNumber];
            default:
                throw new IllegalArgumentException("Invalid room type");
        }
    }
    @Override
    public void checkout(int roomNumber) {
        switch (roomNumber) {
            case 1:
                checkoutRoom(holder.luxuryDoubleRoom, roomNumber);
                break;
            case 2:
                checkoutRoom(holder.deluxeDoubleRoom, roomNumber);
                break;
            case 3:
                checkoutRoom(holder.luxurySingleRoom, roomNumber);
                break;
            case 4:
                checkoutRoom(holder.deluxeSingleRoom, roomNumber);
                break;
            default:
                System.out.println("Enter valid option");
                break;
        }
    }
    static void checkoutRoom(Room[] rooms, int roomNumber) {
        if (rooms[roomNumber] != null) {
            System.out.println("model.Room used by " + rooms[roomNumber].customers.get(0).getName());
            System.out.println("Do you want to checkout? (y/n)");
            char w = sc.next().charAt(0);
            if (w == 'y' || w == 'Y') {
                Billing.generateBill(rooms[roomNumber],rooms[roomNumber].type,roomNumber);
                rooms[roomNumber] = null;
                System.out.println("Deallocated successfully");
            }
        } else {
            System.out.println("model.Room is empty");
        }
    }
    private static int selectAvailableRoom(Room[] rooms) {
        for (int j = 0; j < rooms.length; j++) {
            if (rooms[j] == null) {
                System.out.print(j + 1 + ",");
            }
        }
        System.out.print("\nEnter room number: ");
        int roomNumber = sc.nextInt();
        if (isRoomValid(rooms, roomNumber)) {
            return roomNumber - 1;
        } else {
            return -1;
        }
    }

    private static boolean isRoomValid(Room[] rooms, int roomNumber) {
        return roomNumber >= 1 && roomNumber <= rooms.length && rooms[roomNumber - 1] == null;
    }
}

