/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agency;

import java.util.Date;

/**
 *
 * @author Muhammed Ali
 */
public class Travel {
    private String firmName, place, destination, time,date;
    private float price;
    private int seats;

    public Travel(String firmName, String place, String destination,  String date, String time, float price, int seats) {
        this.firmName = firmName;
        this.place = place;
        this.destination = destination;
        this.time = time;
        this.date = date;
        this.price = price;
        this.seats = seats;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getPlace() {
        return place;
    }

    public String getDestination() {
        return destination;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public float getPrice() {
        return price;
    }

    public int getSeats() {
        return seats;
    }
    
    
}
