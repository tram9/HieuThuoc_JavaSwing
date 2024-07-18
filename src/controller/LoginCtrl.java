/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Login;

/**
 *
 * @author Hiep Phuong
 */
public class LoginCtrl {
    private Connection conn;

    public String quyen = "";
    public LoginCtrl(){
        String url = "jdbc:mysql://localhost:3306/hieuthuoc";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root","");
            System.out.println("connect done");
        }catch(Exception e){
            e.printStackTrace();
        }
       
    }
    
    public boolean check(String email, String pass){
        String sql = "SELECT * FROM taikhoan WHERE email = ? AND mkhau = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(sql); // khởi tạo đối tượng thực thi các truy vấn
            ps.setString(1,email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return true; // Trả về true nếu ResultSet có ít nhất một dòng dữ liệu, tức là người dùng hợp lệ
            }else{
                return false;
            }

        }catch(SQLException s){
            s.printStackTrace();
            return false;
        }
    }
    
    public String role(Login lg){
        String email = lg.getEmail();
        String pass = lg.getPass();
        String sql = "SELECT quyen FROM taikhoan WHERE email = ? AND mkhau = ?";
        try{
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,email);
            ps.setString(2, pass);
            ResultSet result = ps.executeQuery();

            while(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                quyen = result.getString("quyen");
                
            }
        }catch(SQLException s){
            s.printStackTrace();
        }
        return quyen.toLowerCase();
    }
    
    public boolean admin(Login lg){
        role(lg);
        String target = "admin";
        if(target.equals(quyen.toLowerCase())){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean logined(Login lg){
        String email = lg.getEmail();
        String pass = lg.getPass();
        boolean check = check(email,pass);
        role(lg);
        if(check){
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Đăng nhập không thành công");
            return false;
        }
    }
    
}
