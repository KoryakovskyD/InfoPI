package controllers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Order;
import models.Ship;


public class Functions {
    
    private static Connection conn = null;
    private static Statement stmt = null;
    private static List<String> orderList = new ArrayList();
    private static List<String> bortList = new ArrayList();
    private static List<Order> infoList = new ArrayList();
    private static List<Ship> versionList = new ArrayList();
    
    public static Connection connect() {      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.62.1:3306/softwarebd", "root", "egamad");
            stmt =  conn.createStatement();
            if (conn == null) {
            throw new SQLException("Connection is null");
        }        
            System.out.println("Connection to MySQL has been established.");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection error: " + e.getMessage());
        } 
        return conn;
    }
    
    public static ObservableList getOrderList(){      
        try {                
            ResultSet rs = stmt.executeQuery("select * from Orders");
            while (rs.next()) {
                orderList.add(rs.getString("Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return FXCollections.observableArrayList(orderList);
    }
    
    public static ObservableList getBortList(String order){
        try {
            bortList.clear();
            ResultSet rs = stmt.executeQuery("select * from Ship where OrderName = " + getOrderId(order));
            while (rs.next()) {
                bortList.add(rs.getString("ProductNumber"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return FXCollections.observableArrayList(bortList);
    }
    
    public static ObservableList getInfoList(String order, int ship, boolean showPath) {
        infoList.clear();
        try {               
            ResultSet rs = stmt.executeQuery("select p.name as 'Software', p.iklm, bi.Version, p.path " +
                                             "from BortInfo bi " +
                                             "join orders o " +
                                             "on bi.OrderName = o.id " +
                                             "join ship s " +
                                             "on bi .BortName = s.id " +
                                             "join po p " +
                                             "on bi .POName = p.id " +
                                             "where s.ProductNumber = " + ship + " and o.Name = '" + order + "';");
            while (rs.next()) {
                if (showPath)
                    infoList.add(new Order(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
                else
                    infoList.add(new Order(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return FXCollections.observableArrayList(infoList);
    }
    
    public static void setInfoList(int order, int ship, int software, String newVers) {                 
            try (PreparedStatement pstmt = conn.prepareStatement(
                              "update BortInfo set Version = ? where POName = ? and BortName = ? and OrderName = ?"
                           )) {
                        pstmt.setString(1, newVers);
                        pstmt.setInt(2, software);
                        pstmt.setInt(3, ship);
                        pstmt.setInt(4, order);
                        pstmt.executeUpdate();
            } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setShipVersion(Ship o, String newVers) {
        try (PreparedStatement pstmt = conn.prepareStatement(
                "update ship set Version = ? where id = ?"
        )) {
            pstmt.setString(1, newVers);
            pstmt.setInt(2, o.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int getBortId(int bort) {
        try {                
            ResultSet rs = stmt.executeQuery("select id from Ship where ProductNumber = " + bort);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static int getOrderId(String order) {
        try {                
            ResultSet rs = stmt.executeQuery("select id from Orders where Name = \'" + order + "\'");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return 0;
    }
    
    public static int getSoftwareId(String software) {
        try {                
            ResultSet rs = stmt.executeQuery("select id from PO where name = \'" + software + "\'");
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }
    
    
    
    public static void info() {
        Enumeration<Driver> e = DriverManager.getDrivers();
        Driver drv = null;
        while (e.hasMoreElements()) {
            drv = e.nextElement();
            System.out.println(drv.getClass().getCanonicalName());
        }
    }

    public static ObservableList getVersionList() {
        try {
            versionList.clear();
            ResultSet rs = stmt.executeQuery("select s.id, s.ProductNumber, s.ShipNumber, o.Name, s.Version  from ship s " +
                    "join orders o " +
                    "on s.OrderName = o.id ");
            while (rs.next()) {
                versionList.add(new Ship(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Functions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return FXCollections.observableArrayList(versionList);
    }
}
