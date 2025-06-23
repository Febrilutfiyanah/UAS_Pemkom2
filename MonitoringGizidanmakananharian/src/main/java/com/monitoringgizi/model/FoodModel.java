/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monitoringgizi.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ASUS
 */
public class FoodModel {
    private Connection conn;

    public FoodModel(){
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:db/makanan.db");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS makanan (nama TEXT, kalori INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertMakanan(String nama, int kalori){
        new Thread(() ->{
            try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO makanan VALUES (?, ?)")) {
                pstmt.setString(1, nama);
                pstmt.setInt(2, kalori);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }   
        }).start();
    }
    public List<Makanan> getAllMakanan() {
        List<Makanan> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM makanan")) {
            while (rs.next()) {
                list.add(new Makanan(rs.getString("nama"), rs.getInt("kalori")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void exportToJson(List<Makanan> makananList) {
        JSONArray arr = new JSONArray();

        for (Makanan m : makananList) {
            JSONObject obj = new JSONObject();
            obj.put("nama", m.getNama());
            obj.put("kalori", m.getKalori());
            arr.add(obj);
        }
        try (FileWriter file = new FileWriter("storage/log_harian.json")) {
            file.write(arr.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
