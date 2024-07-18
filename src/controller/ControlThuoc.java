/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.ModelThuoc;
import view.FormThuoc;

/**
 *
 * @author Phuong Thao
 */
public class ControlThuoc {
    private Connection conn;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public ControlThuoc(){
        try{   
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/hieuthuoc";
            String user="root";
            String password="";    
            conn= DriverManager.getConnection(url, user, password);
//         System.out.println("connection success");
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
            System.out.println("connect not done");
        }
    }
     // Đưa dữ liệu từ CSDL lên JComboBox
    public void loadDataToComboBox(FormThuoc view) {
        try {
            // Tạo statement và thực hiện truy vấn để lấy dữ liệu từ CSDL
            Statement statement = conn.createStatement();
            Statement statement1 = conn.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT tenNhaCC FROM tblnhacc ");
            ResultSet resultSet1 = statement1.executeQuery("SELECT tenLoai FROM danhmuc");
            // Xóa toàn bộ mục trong JComboBox trước khi thêm mới
            view.cboTenNCC.removeAllItems();
            view.cboTenDM.removeAllItems();
            // Thêm dữ liệu từ kết quả truy vấn vào JComboBox
            while (resultSet.next()) {
                String item = resultSet.getString("tenNhaCC");
                view.cboTenNCC.addItem(item);
            }
            while (resultSet1.next()) {
                String item1 = resultSet1.getString("tenLoai");
                view.cboTenDM.addItem(item1);
            }
            // Đóng các tài nguyên
            resultSet.close();resultSet1.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // đưa dữ liệu từ CSDL lên bảng
    public void rsTableModel(JTable tbl, DefaultTableModel model) {
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(" SELECT * FROM "
                    + "(tbl_sanpham as sp inner join tblnhacc as ncc on sp.maNhaCC=ncc.maNhaCC ) "
                    + "inner join danhmuc as dm on sp.id_loaisp =dm.id_loaisp order by maThuoc ASC");
             addTableModel(model, resultSet);  // gọi hàm addrow vào bảng
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addTableModel(DefaultTableModel model, ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
            while (resultSet.next()) {
               Object[] row = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    if (metaData.getColumnTypeName(i + 1).equals("DATE")) {
                        // Nếu cột là kiểu DATE, chuyển đổi giá trị sang chuỗi "dd/MM/yyyy"
                        Date date = resultSet.getDate(i + 1);
                        if (date != null) {
                            row[i] = dateFormat.format(date);
                        } 
                } else {
                    // Nếu không phải kiểu DATE, giữ nguyên giá trị
                    row[i] = resultSet.getObject(i + 1);
                    }
                 }
                // Lấy giá trị Tên NCC từ ResultSet
                String tenNCC = resultSet.getString("tenNhaCC");
                row[4] = tenNCC;
                String tenLoai = resultSet.getString("tenLoai");
                row[5] = tenLoai;
            // Thêm hàng vào bảng
            model.addRow(row);
             }
    }
    public String layma(FormThuoc view){
        String maNCC = null;
        String maLoai = null;
        // Lấy tên của nhà cung cấp và danh mục từ combobox
        String tenNCC = view.cboTenNCC.getSelectedItem().toString();
        String tenDM = view.cboTenDM.getSelectedItem().toString();

        String sql1 = "SELECT maNhaCC FROM tblnhacc WHERE tenNhaCC = ?";
        String sql2 = "SELECT id_loaisp FROM danhmuc WHERE tenLoai  = ?";
        try {
            PreparedStatement statement1 = conn.prepareStatement(sql1);
            statement1.setString(1, tenNCC);
            ResultSet resultSet1 = statement1.executeQuery();

            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, tenDM);
            ResultSet resultSet2 = statement2.executeQuery();
            if (resultSet1.next()) {
                maNCC = resultSet1.getString("maNhaCC");
            }
            if (resultSet2.next()) {
                maLoai = resultSet2.getString("id_loaisp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Trả về các giá trị maNCC và maLoai dưới dạng mảng hoặc đối tượng chứa các giá trị này
        return maNCC + "," + maLoai; 
    }
    public void add_thuoc(ModelThuoc mt, FormThuoc view) {
        // Gọi phương thức layma() để lấy mã từ tên
        String[] mas = layma(view).split(",");
        String maNCC = mas[0];
        String maLoai = mas[1];
//        System.out.println(maNCC + maLoai);
        String sql = "INSERT INTO tbl_sanpham "
                + "(maThuoc, tenThuoc, donVi, donGia, maNhaCC, id_loaisp, soLuong, ngaySX, hanSD) VALUES(?,?,?,?,?,?,?,?,?)";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,mt.getMaThuoc());
            ps.setString(2, mt.getTenThuoc());
            ps.setString(3, mt.getDonVi());
            ps.setInt(4, mt.getDonGia());
            ps.setString(5, maNCC);
            ps.setString(6, maLoai);
            ps.setInt(7, mt.getSoLuong());
            //get...trả về một đối tượng java.util.Date. cần chuyển đổi nó thành java.sql.Date
            java.sql.Date datesx = new java.sql.Date(mt.getDateNSX().getTime());
            ps.setDate(8, datesx);
            java.sql.Date datesd = new java.sql.Date(mt.getDateHSD().getTime());
            ps.setDate(9, datesd);
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm không thành công I(DAO)", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //return;
            e.printStackTrace();
        }
    }
    public void edit_thuoc(ModelThuoc mt, FormThuoc view){
        String[] mas = layma(view).split(",");
        String maNCC = mas[0];
        String maLoai = mas[1];
        String sql = "UPDATE tbl_sanpham SET tenThuoc = ?, donVi = ?, donGia = ?, maNhaCC = ?, id_loaisp  = ?,soLuong=?, ngaySX=?, hanSD=? WHERE maThuoc = ? ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,mt.getTenThuoc());
            ps.setString(2, mt.getDonVi());
            ps.setInt(3, mt.getDonGia());
            ps.setString(4, maNCC);
            ps.setString(5, maLoai);
            ps.setInt(6, mt.getSoLuong());
            ps.setDate(7, new java.sql.Date(mt.getDateNSX().getTime()));
            ps.setDate(8, new java.sql.Date(mt.getDateHSD().getTime()));
            ps.setString(9, mt.getMaThuoc());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } 
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
        }      
    }
    public void delete_thuoc(ModelThuoc mt){
        String id = mt.getMaThuoc();
        String sql= "DELETE from tbl_sanpham where maThuoc='"+ id + "'";
        try {
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Xoa khong thanh cong!");
            e.printStackTrace();
        }
    }
    public void search_thuoc(DefaultTableModel tableModel, String searchOption, String keyword) throws ParseException{
        String query = " SELECT * "
                + "FROM (tbl_sanpham as sp inner join tblnhacc as ncc on sp.maNhaCC=ncc.maNhaCC )"
                + "inner join danhmuc as dm on sp.id_loaisp=dm.id_loaisp "
                + "where ";
        try{
            if(searchOption.equals("Số Lượng")) {
                query += "soLuong= ?";
            }else if(searchOption.equals("Mã Thuốc")) {
                query += "maThuoc LIKE ?";
                keyword = "%" + keyword + "%";
            }else if (searchOption.equals("Tên NCC")) {
                query += "tenNhaCC LIKE ?";
                keyword = "%" + keyword + "%";
            }else if (searchOption.equals("HSD")) {
                query += "hanSD <= ?";
                System.out.println("kiểu Date "+keyword);
            }
            PreparedStatement statement= conn.prepareStatement(query);
            statement.setString(1, keyword);
            ResultSet resultSet = statement.executeQuery();

            addTableModel(tableModel, resultSet);  // gọi hàm thêm vào jbảng
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả");
            e.printStackTrace();
        }
    }
//    tìm kiếm sản phẩm < (ngày hiện tại +30 ngày)  sắp hết hạn
//    SELECT* FROM tbl_sanpham WHERE hanSD <= DATE_ADD(NOW(), INTERVAL 30 DAY);
}

    
