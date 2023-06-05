/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import agency.Operations;
import agency.Travel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
/**
 *
 * @author Muhammed Ali
 */
public class JUnitTests {
    Operations op = new Operations();
    
    public JUnitTests() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     
    @Test // Kullanıcı Puanı güncelleme işleminin başarısının kontrolü
     public void testUpdateCustomerPoints() {
         String customerId = "2";
         Float reward = 5.5f;
         
         assertTrue(op.updateCustomerPoint(customerId, reward));
     }
     
     @Test // Ödeme işleminin başarısının kontrolü
     public void testPayment() {
         String choice = "cash";
         Float price = 100f;
         
         assertTrue(op.updatePayments(choice, price));
     }
     
     @Test // Bilet sorgulama işleminde alınan biletin doğruluğunun kontrolü
     public void testTicketQuery() {
         String firmName = "THY";
         String place = "Ankara";
         String destination = "Yozgat";
         String date = "2023-5-26";
         
         Travel travel = op.getTravels(firmName, place, destination, date).get(0);
         
         assertTrue(travel.getFirmName().matches(firmName));  
         assertTrue(travel.getPlace().matches(place));  
         assertTrue(travel.getDestination().matches(destination));  
     }
     
     @Test // Firmaların puanlarının güncellenmesi işleminin başarısının kontrolü
     public void testUpdateFirmPoints() {
         String firmName = "Pegasus";
         Float point = 4.5f;
         
         assertTrue(op.updateFirmPoints(firmName, point));
     }
     
     @Test // Otellerin puanlarının güncellenmesi işleminin başarısının kontrolü
     public void testUpdateHotelPoints() {
         String firmName = "ALTINKUMSAL";
         Float point = 4.5f;
         
         assertTrue(op.updateHotelPoints(firmName, point));
     }
     
     @Test // Koltuk Sayısı eksiltme işleminin başarısının kontrolü
     public void testDecreaseSeats(){
         
         Travel travel = new Travel("TCDD","Ankara","İzmir","2023-05-15","14:30",200f,1);
         
         assertTrue(op.decreaseSeats(travel));
     }
     
     @Test // Önceki koltuk sayısının ödeme sonrasındaki koltuk sayısından 1 eksik olduğunun kontrolü
     public void testSeats(){

         Travel travel = op.getTravels("TCDD", "Ankara", "İzmir", "2023-05-15").get(0);
         int seatsBefore = travel.getSeats();
         
         op.decreaseSeats(new Travel("TCDD","Ankara","İzmir","2023-05-15","14:30",200f,1));
         
         travel = op.getTravels("TCDD", "Ankara", "İzmir", "2023-05-15").get(0);
         int seatsAfter = travel.getSeats();
         
         assertEquals(seatsBefore-1, seatsAfter);
     }
     
     @Test // Yeni ulaşım firması ekleme işleminin başarısının kontrolü
     public void testAddFirm(){
         String firmName = "LÜKS ARTVİN";
         Float point = 1.8f;
         
         assertTrue(op.addFirm(firmName, point));
     }
     
     @Test // Yeni sefer ekleme işleminin başarısının kontrolü
     public void testAddTravel(){
         
         assertTrue(op.addTravel("LÜKS ARTVİN","Giresun","Yozgat","2023-08-28","11:30",420f,38));
     }
     
     @Test //Firma puanı güncelleme işlemi sonrası firmanın puanının istenen puanda olup olmadığının kontrolü
     public void testFirmPoints(){
         String firmName = "Pegasus";
         Float point = 6.4f;
         
         op.updateFirmPoints(firmName, point);
                 
         assertFalse(!point.equals(op.getPoint(firmName)));
     }     
}
