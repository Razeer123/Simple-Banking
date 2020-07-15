package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertApp {

    private final String fileName;

    protected InsertApp(String fileName) {
        this.fileName = fileName;
    }

    private Connection connect () {

        String url = "jdbc:sqlite:/Users/michalderej/Desktop/Database/" + fileName;
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;

    }

    public void insert (int id, long number, int pin, int balance) {

        String sql = "INSERT INTO card (id,number,pin,balance) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setLong(2, number);
            pstmt.setInt(3, pin);
            pstmt.setInt(4, balance);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
