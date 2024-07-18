/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.TaiKhoan;


/**
 *
 * @author Hiep Phuong
 */
public class TaiKhoanCtrl {
    private Connection conn;

    public TaiKhoanCtrl(){
        String url = "jdbc:mysql://localhost:3306/hieuthuoc";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root","");
            System.out.println("connect done");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet taikhoan(){
        ResultSet result = null;
        String sql = "SELECT * FROM taikhoan";
        try {
            Statement statement = (Statement) conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void addTaiKhoan(TaiKhoan tk){
        String id = tk.getId();
        String email = tk.getEmail();
        String pass = tk.getPass();
        String hoten = tk.getHoten();
        String quyen = tk.getQuyen();
        String sql= "INSERT INTO taikhoan(id_taikhoan, email, mkhau, hoten, quyen) value(?,?,?,?,?)";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1,id);
            ps.setString(2,email);
            ps.setString(3,pass);
            ps.setString(4,hoten);
            ps.setString(5,quyen);

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
    
    public void editTaiKhoan(TaiKhoan tk){
        String id = tk.getId();
        String email = tk.getEmail();
        String pass = tk.getPass();
        String hoten = tk.getHoten();
        String quyen = tk.getQuyen();
        
        String sql = "UPDATE taikhoan SET email=?, mkhau =?, hoten = ?, quyen = ? WHERE id_taikhoan = '"+id+"'";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            ps.setString(3, hoten);
            ps.setString(4, quyen);
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
    
    public void deleteTaiKhoan(TaiKhoan tk){
        String id = tk.getId();
        
        String sql= "DELETE from taikhoan where id_taikhoan='"+ id+ "'";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }
    
    public void sortTaiKhoan(DefaultTableModel d){
        d.setRowCount(0);
        String sql = "SELECT * FROM taikhoan ORDER BY hoten ASC";  
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery(sql);
            
            while(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[5];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng)
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                rows[2] = result.getString(3);
                rows[3] = result.getString(4);
                rows[4] = result.getString(5);
                d.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Timkiem(DefaultTableModel tbm, String searchOption, String keyword){
        tbm.setRowCount(0);
        String query = "SELECT * FROM taikhoan WHERE ";
        try{
            if(searchOption.equals("Email")) {
                query += "email LIKE ?";
                keyword = "%" + keyword + "%";
            }else if(searchOption.equals("Họ tên")) {
                query += "hoten LIKE ?";
                keyword = "%" + keyword + "%";
            }else if (searchOption.equals("Quyền")) {
                query += "quyen LIKE ?";
                keyword = "%" + keyword + "%";
            }
            PreparedStatement statement= conn.prepareStatement(query);
            statement.setString(1, keyword);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                String rows[] = new String[5];
                    rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng)
                    rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                    rows[2] = result.getString(3);
                    rows[3] = result.getString(4);
                    rows[4] = result.getString(5);
                    tbm.addRow(rows);
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            e.printStackTrace();
        }
        
    }
   
}
