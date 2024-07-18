/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Phuong Thao
 */
public class ModelThuoc {

    String maThuoc, tenThuoc, donVi, maNCC, maLoai;
    int soLuong;
    int donGia;
    Date dateNSX, dateHSD;

    public ModelThuoc() {

    }

    public ModelThuoc(String maThuoc, String tenThuoc, String donVi, String maNCC, String maLoai, int soLuong, int donGia, Date dateNSX, Date dateHSD) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.donVi = donVi;
        this.maNCC = maNCC;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.dateNSX = dateNSX;
        this.dateHSD = dateHSD;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public Date getDateNSX() {
        return dateNSX;
    }

    public void setDateNSX(Date dateNSX) {
        this.dateNSX = dateNSX;
    }

    public Date getDateHSD() {
        return dateHSD;
    }

    public void setDateHSD(Date dateHSD) {
        this.dateHSD = dateHSD;
    }

}
