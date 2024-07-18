/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;

/**
 *
 * @author Ngoc Lien - PC
 */
public class NhaCCmodel {

    static ResultSet searchNhacc(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String MaNhaCC, TenNhaCC, Diachi, SDT, MST, Ghichu;

    public NhaCCmodel() {
    }

    public NhaCCmodel(String MaNhaCC, String TenNhaCC, String Diachi, String SDT, String MST, String Ghichu) {
        this.MaNhaCC = MaNhaCC;
        this.TenNhaCC = TenNhaCC;
        this.Diachi = Diachi;
        this.SDT = SDT;
        this.MST = MST;
        this.Ghichu = Ghichu;
    }

    public String getMaNhaCC() {
        return MaNhaCC;
    }

    public void setMaNhaCC(String MaNhaCC) {
        this.MaNhaCC = MaNhaCC;
    }

    public String getTenNhaCC() {
        return TenNhaCC;
    }

    public void setTenNhaCC(String TenNhaCC) {
        this.TenNhaCC = TenNhaCC;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMST() {
        return MST;
    }

    public void setMST(String MST) {
        this.MST = MST;
    }

    public String getGhichu() {
        return Ghichu;
    }

    public void setGhichu(String Ghichu) {
        this.Ghichu = Ghichu;
    }
    
}
