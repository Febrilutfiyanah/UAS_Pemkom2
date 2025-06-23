/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monitoringgizi.controller;
import com.monitoringgizi.model.FoodModel;
import com.monitoringgizi.model.Makanan;

import java.io.*;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FoodController {
    private FoodModel model;

    public FoodController(){
        model = new FoodModel();
    }
    public void tambahMakanan(String nama, int kalori) {
         model.insertMakanan(nama, kalori);
    }
    public List<Makanan> getMakananList(){
        return model.getAllMakanan();
    }
    public void simpanRiwayat(List<Makanan> makananList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("storage/riwayat.ser"))) {
            out.writeObject(makananList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Makanan> muatRiwayat(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("storage/riwayat.ser"))) {
            return (List<Makanan>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void eksporJson(List<Makanan> makananList) {
        model.exportToJson(makananList);
    }
}
