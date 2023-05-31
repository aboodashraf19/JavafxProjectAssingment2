/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Abood
 */
public class User {

    private int id;
    private String role;
    private String username;
    private String password;
    private String email;
    private String gender;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String role) {
        this.role = role;
    }

    public User(int id, String username, String password, String email, String gender, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int save() throws ClassNotFoundException, SQLException {
        Connection c = DB.getInstance().getConnection();
        PreparedStatement ps = null;
        int recordCounter = 0;
        String sql = "INSERT INTO USERS (ID, USERNAME,PASSWRD,EMAIL,GENDER,ROLE)VALUES(?,?,?,?,?,?)";
        ps = c.prepareStatement(sql);
        ps.setString(1, this.getUsername());
        ps.setString(2, this.getUsername());
        ps.setString(3, this.getPassword());
        ps.setString(4, this.getEmail());
        ps.setString(5, this.getGender());
        ps.setString(6, this.getRole());
        recordCounter = ps.executeUpdate();
        if (recordCounter > 0) {
            System.out.println(this.getUsername()
                    + "User was added successfully!");
        }
        if (ps != null) {
            ps.close();
        }
        c.close();
        return recordCounter;
    }
}
