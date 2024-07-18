/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Hiep Phuong
 */
public class TaiKhoan {
    String id, email, pass, hoten, quyen;

    public TaiKhoan() {
    }

    public TaiKhoan(String id, String email, String pass, String hoten, String quyen) {
        this.id = id;
        this.email = email;
        this.pass = pass;
        this.hoten = hoten;
        this.quyen = quyen;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public String getHoten() {
        return hoten;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }
    
}
