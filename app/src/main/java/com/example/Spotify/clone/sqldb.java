/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Spotify.clone;
import java.sql.*;
/**
 *
 * @author deeks
 */
public class sqldb {
static Connection conn;
    static Statement st;
    static void connect(){
        String url="jdbc:mysql://localhost:3308/";
        String driver="com.mysql.cj.jdbc.Driver";
        String dbname="spotify-clone";
        String username="root";
        String password="";
        try{
            Class.forName(driver);
            conn=DriverManager.getConnection(url+dbname,username,password);
            st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
        catch(Exception e){
            System.out.println("No st");
            e.printStackTrace();
        }
    }
    static void connclose(){
        try{
            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    static int iud_data(String str){
        int r=0;
        try{
            r=st.executeUpdate(str);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return r;
    }
    static ResultSet fetchdata(String str) throws SQLException{
            return st.executeQuery(str);
    }
}



