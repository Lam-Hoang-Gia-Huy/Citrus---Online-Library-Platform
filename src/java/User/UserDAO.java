/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public UserDTO login(String user, String password) {

        String sql = "select customer_id, Customer.username, customer_name from Customer inner join Login on Customer.username=Login.username where Login.username= ? and password= ?";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                UserDTO userDTO = new UserDTO();

                userDTO.setUserID(rs.getInt("customer_id"));
                userDTO.setUsername(rs.getString("username"));
                userDTO.setName(rs.getString("customer_name"));

                return userDTO;

            }
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }

    public List<UserDTO> getUserList() {
        ArrayList<UserDTO> list;
        list = new ArrayList<UserDTO>();
        String sql = "select customer_id,customer_name,username from Customer";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                )
                );
            }
            return list;
        } catch (SQLException ex) {
            System.out.println("Query error!" + ex.getMessage());
        }
        return null;
    }

    public boolean CreateLogin(String username, String password) {
        String sql = "INSERT INTO Login(username, password) VALUES (?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            int updateSuccess = ps.executeUpdate();
            if (updateSuccess > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Insert error!" + ex.getMessage());
        }
        return false;
    }

    public boolean CreateUser(int id, String name, String username) {
        String sql = "INSERT INTO Customer(customer_id, customer_name, username) VALUES (?,?,?)";
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, username);
            int updateSuccess = ps.executeUpdate();
            if (updateSuccess > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Insert error!" + ex.getMessage());
        }
        return false;
    }
}
