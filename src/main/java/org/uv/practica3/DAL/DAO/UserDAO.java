package org.uv.practica3.DAL.DAO;


import org.uv.practica3.DAL.POJO.User;
import org.uv.practica3.DAL.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qinux
 */
public class UserDAO {
    private Connection conn;
    private PreparedStatement query;
    
    public UserDAO(Connection conn) {
        this.conn = conn;
    }
    
    /**
     * Create *** ⚡
     */
    public void store(User user) {
        try {
            query = conn.prepareStatement(String.format("insert into user values(null, '%s', '%s', '%s');", user.getNombre(), user.getDireccion(), user.getTelefono()));
            query.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Read *** ⚡⚡
     */
    public List<User> aquire() {
        List<User> users = new ArrayList<User>();
        try {
            query = conn.prepareStatement("select * from user;");
            ResultSet rs = query.executeQuery();
            
            while(rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    /**
     * Update *** ⚡⚡⚡
     */
    public void shift(User user) {
        try {
            query = conn.prepareStatement(String.format("update user set nombre = '%s', direccion = '%s', telefono = '%s' where clave = %d;", user.getNombre(), user.getDireccion(), user.getTelefono(), user.getClave()));
            query.execute();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete *** ⚡⚡⚡⚡
     */
    public void remove(int clave) {
        try {
            query = conn.prepareStatement(String.format("delete from user where clave = %d;", clave));
            query.execute();
                    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
