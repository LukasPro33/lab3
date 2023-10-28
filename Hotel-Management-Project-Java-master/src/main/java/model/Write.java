package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Write implements Runnable {
    Holder hotel;

    Write(Holder hotel) {
        this.hotel = hotel;
    }
    @Override
    public void run() {
        try (FileOutputStream fout = new FileOutputStream("backup");
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(hotel);
        } catch (IOException e) {
            System.out.println("Error in writing " + e);
        }
    }
}