/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.monitoringgizi.model;
import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Makanan {
    private String nama;
    private int kalori;
    
    public Makanan(String nama, int kalori) {
        this.nama = nama;
        this.kalori = kalori;
    }
    
    public String getNama(){
        return nama;
    }
    
    public int getKalori(){
        return kalori;
    }
}


