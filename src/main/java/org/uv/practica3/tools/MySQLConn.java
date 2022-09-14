package org.uv.practica3.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qinux
 */
public class MySQLConn {
    private static MySQLConn instance = null;
    
    private String url = "jdbc:mysql://localhost:3306/crud_users";
    private Connection con = null;
    
    private MySQLConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "Thebeatles97?");
            
            Logger.getLogger(MySQLConn.class.getName()).log(Level.INFO, "Se conecto a la db");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MySQLConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MySQLConn getInstance(){
        if(instance == null)
            instance = new MySQLConn();
        return instance;
    }
    
    public Connection getConnection(){
        return con;
    }
}
