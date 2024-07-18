/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Date;
/**
 *
 * @author TRINH NGOC TRAM
 */
public class ctr_nv {
    private static final String URL = "jdbc:mysql://localhost:3306/hieuthuoc";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection conn;
    
    public ctr_nv(){
        conn = getJDBCConnection();
    }
    public static Connection getJDBCConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //hien thi du lieu ra table
    public ResultSet fetchDataFromDatabase() throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM nhanvien");
        return preparedStatement.executeQuery();
    }
    // them
    public void insertData(String id, String hoten, Date age, String email, String diachi, String sdt) throws SQLException {
        String insertQuery = "INSERT INTO nhanvien (id, hoten, age, email, diachi, sdt) VALUES ( ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, hoten);
            preparedStatement.setDate(3, new java.sql.Date(age.getTime()));
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, diachi);
            preparedStatement.setString(6, sdt);

            preparedStatement.executeUpdate();
        }
    }
    //xoa
    public void deleteData(int id) throws SQLException {
        String deleteQuery = "DELETE FROM nhanvien WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
    
    //cap nhat
    public void updateData(int id, String hoten, Date age, String email, String diachi, String sdt) throws SQLException {
        String updateQuery = "UPDATE nhanvien SET hoten = ?, age = ?, email = ?, diachi = ?, sdt = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            
            preparedStatement.setString(1, hoten);
            preparedStatement.setDate(2, new java.sql.Date(age.getTime()));
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, diachi);
            preparedStatement.setString(5, sdt);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        }
    }
    //tim kiem
    public ResultSet searchData(String keyword, int columnIndex) throws SQLException {
        String columnName = null;
        // Xác định tên cột dựa trên columnIndex
        switch (columnIndex) {
            case 0:
                columnName = "hoten"; // Chọn cột "hoten"
                break;
            case 1:
                columnName = "email"; // Chọn cột "email"
                break;
            case 2:
                columnName = "diachi"; // Chọn cột "diachi"
                break;
            default:
                throw new IllegalArgumentException("Invalid column index");
        }

        // Tạo câu truy vấn SQL
        String sql = "SELECT * FROM nhanvien WHERE LOWER(" + columnName + ") LIKE ?";

        // Chuẩn bị câu truy vấn SQL
        PreparedStatement statement = conn.prepareStatement(sql);
        // Thiết lập tham số cho câu truy vấn
        statement.setString(1, "%" + keyword.toLowerCase() + "%");

        // Thực thi câu truy vấn
        return statement.executeQuery();
    }


}
