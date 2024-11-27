/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Spotify.clone;

import java.sql.*;

public class SpotifyAuth {
    private static final String DB_URL = "jdbc:mysql://localhost:3308/";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String dbname="spotify-clone";

    public static boolean registerUser(String username, String email, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL+dbname, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)")) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loginUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL+dbname, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // User found

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}