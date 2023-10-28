package model;

import java.io.Serializable;

public class Food implements Serializable
{
    int itemNo;
    int quantity;
    double price;

    Food(int itemNo,int quantity)
    {
        this.itemNo = itemNo;
        this.quantity = quantity;

        switch (itemNo) {
            case 1 -> price = quantity * 50.0;
            case 2 -> price = quantity * 60.0;
            case 3 -> price = quantity * 70.0;
            case 4 -> price = quantity * 30.0;
            default -> price = quantity * 20.0;
        }
    }
}