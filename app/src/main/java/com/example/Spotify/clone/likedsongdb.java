/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Spotify.clone;
import java.sql.*;
/**
 *
 * @author Dhruvi Tanna
 */
public class likedsongdb {
    
    
    // Method to fetch all liked songs from the database
    public static ResultSet fetchLikedSongs() {
        // SQL query to fetch the liked songs and their details
        String query = "SELECT ls.like_id, s.song_path, s.song_name, s.artist_name, ls.liked_at "
                     + "FROM liked_songs ls "
                     + "JOIN songs s ON ls.song_id = s.id";
        try {
            // Use the fetchdata method from the sqldb class to execute the query
            return sqldb.fetchdata(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to insert a song into the liked_songs table
    public static int likeSong(int songId) {
        // SQL query to insert the liked song (song_id) into the liked_songs table
        String insertQuery = "INSERT INTO liked_songs (song_id) VALUES (?)";
        
        // Using a prepared statement to avoid SQL injection
        try (PreparedStatement pstmt = sqldb.conn.prepareStatement(insertQuery)) {
            pstmt.setInt(1, songId); // Setting the song_id to the query
            
            // Execute the insert query
            return pstmt.executeUpdate(); // Returns the number of rows affected
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Return 0 if the insert failed
        }
    }

    // Method to remove a song from the liked_songs table
    public static int unlikeSong(int songId) {
        // SQL query to remove the liked song (song_id) from the liked_songs table
        String deleteQuery = "DELETE FROM liked_songs WHERE song_id = ?";
        
        // Using a prepared statement to avoid SQL injection
        try (PreparedStatement pstmt = sqldb.conn.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, songId); // Setting the song_id to the query
            
            // Execute the delete query
            return pstmt.executeUpdate(); // Returns the number of rows affected
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Return 0 if the delete failed
        }
    }
}


