package model;

interface RoomOperations {
    void displayRoomDetails(int roomType);
    void displayRoomAvailability(int roomType);

    void bookRoom(int roomType);

    void orderFood(int roomType, int roomNumber);

    void checkout(int roomNumber);
}