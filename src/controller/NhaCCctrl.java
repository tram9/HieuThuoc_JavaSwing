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
import model.NhaCCmodel;
import view.NhaCCview;

/**
 *
 * @author Ngoc Lien - PC
 */
public class NhaCCctrl {
    private Connection conn;
    NhaCCview view;
    public void NhaCCctrl(){
        try{   
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/hieuthuoc";
            conn= DriverManager.getConnection(url, "root", "");
            System.out.println("connection success");
            
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
    public ResultSet danhsach(){
        ResultSet result = null;
        String sql = "SELECT * FROM tblnhacc";
        try {
            Statement statement = (Statement) conn.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void Add(NhaCCmodel mo){
        NhaCCctrl();
        String sql = "INSERT INTO tblnhacc(maNhaCC,tenNhaCC,diachi,sdt,masothue,ghichu) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, mo.getMaNhaCC());
            ps.setString(2, mo.getTenNhaCC());
            ps.setString(3, mo.getDiachi());
            ps.setString(4, mo.getSDT());
            ps.setString(5, mo.getMST());
            ps.setString(6, mo.getGhichu());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Edit(NhaCCmodel mo){
        
        String maNhaCC=mo.getMaNhaCC();
        String tenNhaCC=mo.getTenNhaCC();
        String diachi=mo.getDiachi();
        String sdt=mo.getSDT();
        String masothue=mo.getMST();
        String ghichu=mo.getGhichu();
        String sql = "UPDATE tblnhacc SET tenNhaCC='" + tenNhaCC + "', diachi='" 
                        + diachi + "', sdt='" + sdt + "', masothue='"
                        + masothue + "', ghichu='" + ghichu + "' WHERE maNhaCC='" + maNhaCC + "'";
//                System.out.println("SQL Statement: " + sql); 
        try {
            PreparedStatement ps=conn.prepareStatement(sql);
            
            JOptionPane.showMessageDialog(null, "Sửa thành công","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void Clear(NhaCCmodel mo){
        
    }
    public void Delete(NhaCCmodel mo){
        NhaCCctrl();
        String maNhaCC = mo.getMaNhaCC();
       
            String sql= "DELETE from tblnhacc where maNhaCC='"+ maNhaCC+ "'";
            try {
                PreparedStatement ps= conn.prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Không thành công","Thông báo",JOptionPane.ERROR_MESSAGE);
            }
    }
    public void Timkiem(DefaultTableModel tablemodel, String keyword){
        NhaCCctrl();
        
        String query = "SELECT * FROM tblnhacc WHERE maNhaCC LIKE ? "
                + "OR tenNhaCC LIKE ? "
                + "OR diachi LIKE ?";
        keyword="%"+ keyword +"%";
        try{
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, keyword);
        statement.setString(2, keyword);
        statement.setString(3, keyword);

        ResultSet resultSet = statement.executeQuery();

        // Đọc dữ liệu từ ResultSet và thêm vào DefaultTableModel
        while (resultSet.next()) {
            String maNhaCC = resultSet.getString("maNhaCC");
            String tenNhaCC = resultSet.getString("tenNhaCC");
            String diachi = resultSet.getString("diachi");
            String sdt = resultSet.getString("sdt");
            String masothue = resultSet.getString("masothue");
            String ghichu = resultSet.getString("ghichu");
            

            tablemodel.addRow(new Object[]{maNhaCC, tenNhaCC, diachi, sdt, masothue,ghichu});
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            e.printStackTrace();
        }
    }
}
