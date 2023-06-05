/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agency;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Muhammed Ali
 */
public class Operations {
    
    private ResultSet rs=null;
    private static Connector db = DatabaseInstance.getDb();
    

    public ArrayList<String> getFirms() {
        ArrayList<String> firms = new ArrayList<>();
        firms.add("Firma seçiniz");
        try {
            String query= "SELECT firmname FROM firms";
            db.preState=db.con.prepareStatement(query);
         
            rs=db.preState.executeQuery();
            while(rs.next()){
                firms.add(rs.getString("firmname"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
     
        }
        return firms;
    }
    
    public HashMap<String,Float> getHotels() {
        HashMap<String,Float> hotels =  new HashMap<>();
        try {
            String query= "SELECT hotelname, points FROM hotels";
            db.preState=db.con.prepareStatement(query);
         
            rs=db.preState.executeQuery();
            while(rs.next()){
                hotels.put(rs.getString("hotelname"),rs.getFloat("points"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return hotels;
    }
    
    public Float getPoint(String firmname) {
        Float point=-1f;
        try {
            String query= "SELECT point FROM firms WHERE firmname = ?";
            db.preState=db.con.prepareStatement(query);
            db.preState.setString(1, firmname);
            rs=db.preState.executeQuery();
            while(rs.next()){
                point = rs.getFloat("point");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
     
        }
        return point;
    }
    
    public boolean updateCustomerPoint(String customerId, Float reward){
        try {
            String query = "UPDATE customers SET points = points + ? WHERE customer_id = ?";
            db.preState = db.con.prepareStatement(query);
            db.preState.setString(2, customerId);
            db.preState.setFloat(1, reward);
            
            return db.preState.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public boolean updatePayments(String choice, Float price){
        try {
            String query = "UPDATE payment SET amount = amount + ? WHERE type = ?";
            db.preState = db.con.prepareStatement(query);
            db.preState.setString(2, choice);
            db.preState.setFloat(1, price);
            
            return db.preState.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateFirmPoints(String fName, Float point){
        try {
            String query = "UPDATE firms SET point = ? WHERE firmname = ?";
            db.preState = db.con.prepareStatement(query);
            db.preState.setString(2, fName);
            db.preState.setFloat(1, point);
            
            return db.preState.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateHotelPoints(String fName, Float point){
        try {
            String query = "UPDATE hotels SET points = ? WHERE hotelname = ?";
            db.preState = db.con.prepareStatement(query);
            db.preState.setString(2, fName);
            db.preState.setFloat(1, point);
            
            return db.preState.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    public boolean decreaseSeats(Travel t){
        try {
            String query = "UPDATE travel SET seats = seats - 1 WHERE firmname = ? AND place = ? AND destination = ? AND date = ? AND time = ? AND price = ?";
            db.preState = db.con.prepareStatement(query);
            db.preState.setString(1, t.getFirmName());
            db.preState.setString(2, t.getPlace());
            db.preState.setString(3, t.getDestination());
            db.preState.setDate(4, java.sql.Date.valueOf(t.getDate().toString()));
            db.preState.setString(5, t.getTime());
            db.preState.setFloat(6, t.getPrice());
            
            return db.preState.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean addFirm (String fName, Float point){
        try {
            String query = "INSERT INTO firms(firmname, point) VALUES (?, ?); ";
            db.preState = db.con.prepareStatement(query);
            db.preState.setString(1, fName);
            db.preState.setFloat(2, point);
            
            return db.preState.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean addTravel (String fName, String place, String destination, String date, String time,Float price, int seats){
        try {
            String query = "INSERT INTO travel(firmname, place, destination, date, time, price, seats) VALUES (?, ?, ?, ?, ?, ?, ?); ";
            db.preState = db.con.prepareStatement(query);
            db.preState.setString(1, fName);
            db.preState.setString(2, place);
            db.preState.setString(3, destination);
            db.preState.setDate(4,java.sql.Date.valueOf(date) );
            db.preState.setString(5, time);
            db.preState.setInt(7, seats);
            db.preState.setFloat(6, price);
            
            return db.preState.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public HashMap<String,Float> getCustomers() {
        HashMap<String,Float> customers =  new HashMap<>();
        try {
            String query= "SELECT * FROM customers";
            db.preState=db.con.prepareStatement(query);
         
            rs=db.preState.executeQuery();
            while(rs.next()){
                customers.put(rs.getString("customer_id")+":  "+rs.getString("name"),rs.getFloat("points"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
     
        }
        return customers;
    }
     
    
    
    public ArrayList<Travel> getTravels(String firmname, String place, String destination, String date){
        ArrayList<Travel> travels = new ArrayList<>();
        try {
            String query= "SELECT * FROM travel WHERE firmname = ? AND place = ? AND destination = ? AND date= ?";
            db.preState=db.con.prepareStatement(query);
            db.preState.setString(1, firmname);
            db.preState.setString(2, place);
            db.preState.setString(3, destination);
            db.preState.setDate(4,java.sql.Date.valueOf(date));
            rs=db.preState.executeQuery();
            while(rs.next()){
                travels.add(new Travel(rs.getString("firmname"),rs.getString("place") , rs.getString("destination"), rs.getDate("date").toString(), rs.getString("time"), rs.getFloat("price"), rs.getInt("seats")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
     
        }
        return travels;
    }
    
     public ArrayList<Hotel> getHotels(String region, String date){
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            String query= "SELECT * FROM hotels WHERE region = ? AND firstdate <= ? AND lastdate >= ?";
            db.preState=db.con.prepareStatement(query);
            db.preState.setString(1, region);
            db.preState.setDate(2,java.sql.Date.valueOf(date));
            db.preState.setDate(3,java.sql.Date.valueOf(date));
            rs=db.preState.executeQuery();
            while(rs.next()){
                hotels.add(new Hotel(rs.getString("hotelname"),rs.getString("region"),rs.getDate("firstdate"),rs.getDate("lastdate"),rs.getFloat("price"),rs.getFloat("points")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
     
        }
        return hotels;
    }
    
    public ArrayList<String> getTravelPlaces() {
        ArrayList<String> places = new ArrayList<>();
        places.add("Kalkış Yeri");
        try {
            String query= "SELECT DISTINCT place FROM travel";
            db.preState=db.con.prepareStatement(query);
         
            rs=db.preState.executeQuery();
            while(rs.next()){
                places.add(rs.getString("place"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return places;
    }
    
    public ArrayList<String> getHotelPlaces() {
        ArrayList<String> places = new ArrayList<>();
        places.add("Bölge");
        try {
            String query= "SELECT DISTINCT region FROM hotels";
            db.preState=db.con.prepareStatement(query);
         
            rs=db.preState.executeQuery();
            while(rs.next()){
                places.add(rs.getString("region"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return places;
    }
    
    public ArrayList<String> getDestinations() {
        ArrayList<String> firms = new ArrayList<>();
        firms.add("Varış Yeri");
        try {
            String query= "SELECT DISTINCT destination FROM travel";
            db.preState=db.con.prepareStatement(query);
         
            rs=db.preState.executeQuery();
            while(rs.next()){
                firms.add(rs.getString("destination"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Operations.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return firms;
    }
}
