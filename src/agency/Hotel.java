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
public class Hotel {
    private String hotelName, region;
    private Date firstDate, lastDate;
    private float price, points;

    public Hotel(String hotelName, String region, Date firstDate, Date lastDate, float price, float points) {
        this.hotelName = hotelName;
        this.region = region;
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.price = price;
        this.points = points;
    }

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
    
    

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    
}
