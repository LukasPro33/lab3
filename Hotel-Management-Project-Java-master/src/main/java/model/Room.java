package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable{
    List<Customer> customers;

    ArrayList<Food> food = new ArrayList<>();
    RoomType type;


    public Room(List<Customer> customers,RoomType type) {
        this.customers = customers;
        this.type = type;
    }

    public void addFood(Food food) {
        this.food.add(food);
    }

    enum RoomType {
        LUXURY_DOUBLE,
        DELUXE_DOUBLE,
        LUXURY_SINGLE,
        DELUXE_SINGLE
    }

}
