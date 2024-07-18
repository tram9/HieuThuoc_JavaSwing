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
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.DanhMuc;

/**
 *
 * @author Hiep Phuong
 */
public class DanhMucCtrl {
    private Connection conn;
    public DanhMucCtrl() {
        String url = "jdbc:mysql://localhost:3306/hieuthuoc";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root","");
            System.out.println("connect done");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ResultSet danhmuc(){
        ResultSet result = null;
        String sql = "SELECT * FROM danhmuc";
        try {
            Statement statement = (Statement) conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void addDanhMuc(DanhMuc dm){
        String id = dm.getId();
        String name = dm.getName();
       
        String sql= "INSERT INTO danhmuc(id_loaisp, tenLoai) value(?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,name);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
    
    public void editDanhMuc(DanhMuc dm){
        String id = dm.getId();
        String name = dm.getName();
        
        String sql = "UPDATE danhmuc SET tenLoai = ? WHERE id_loaisp = '"+id+"'";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, name);
            
            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Không có dữ liệu cập nhật", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
    
    public void deleteDanhMuc(DanhMuc dm){
        String id = dm.getId();
        
        String sql= "DELETE from danhmuc where id_loaisp='"+ id+ "'";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void sortDanhMuc(DefaultTableModel d){
        d.setRowCount(0);
        String sql = "SELECT * FROM danhmuc ORDER BY tenLoai ASC";  
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery(sql);
            
            while(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[2];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng)
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                
                d.addRow(rows); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void Timkiem(DefaultTableModel tbm, String keyword){
        tbm.setRowCount(0);
        String query = "SELECT * FROM danhmuc WHERE tenLoai LIKE ? ";
        keyword="%"+ keyword +"%";
        try{
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, keyword);
           
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                String rows[] = new String[2];
                    rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng)
                    rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    
                    tbm.addRow(rows);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            e.printStackTrace();
        }
    }

}
