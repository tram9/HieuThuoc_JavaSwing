/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.toedter.calendar.JDateChooser;
import controller.ctr_nv;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 *
 * @author TRINH NGOC TRAM
 */
public class view_nv extends JFrame {
    
    // Khai bao nut
    private JButton taiKhoanBtn;
    private JButton sphamBtn;
    private JButton nhanVienBtn;
    private JButton danhMucBtn;
    private JButton nhaCcBtn;
    private JButton logoutBtn;
    JPanel  menu,contentPanel;


    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField txtID, txtHoTen, txtEmail, txtDiaChi, txtSDT,txtSearch;
    private JDateChooser dateChooser;
    private JButton btnThem, btnXoa, btnCapNhat, btnSearch;
    private JTextArea txtArea;
    private JLabel titleLabel;

    
    private int selectedRow = -1;
    
    public view_nv(String userRole) {
        
        // Thiết lập cấu trúc JFrame
        setTitle("Quản Lý Nhân Viên");
        setSize(850, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// đóng mặc định
        setLocationRelativeTo(null);//cửa sổ xuất hiện ở giữa màn hình
        setResizable(false);

       //Khoi tao cac nut
        taiKhoanBtn = new JButton("Tài Khoản");
        sphamBtn = new JButton("Sản Phẩm");
        nhanVienBtn = new JButton("Nhân Viên");
        danhMucBtn = new JButton("Danh Mục");
        nhaCcBtn = new JButton("Nhà Cung Cấp");
        logoutBtn = new JButton("Đăng xuất");
        
        taiKhoanBtn.setBorderPainted(false);
        sphamBtn.setBorderPainted(false);
        nhanVienBtn.setBorderPainted(false);
        danhMucBtn.setBorderPainted(false);
        nhaCcBtn.setBorderPainted(false);
        logoutBtn.setBorderPainted(false);
        
        taiKhoanBtn.setPreferredSize(new Dimension(137,30));
        sphamBtn.setPreferredSize(new Dimension(140,30));
        nhanVienBtn.setPreferredSize(new Dimension(137,30));
        danhMucBtn.setPreferredSize(new Dimension(141,30));
        nhaCcBtn.setPreferredSize(new Dimension(137,30));
        logoutBtn.setPreferredSize(new Dimension(140,30));
        
        menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        
//        menu.setPreferredSize(new Dimension(20,30));
        menu.add(taiKhoanBtn);
        menu.add(sphamBtn);
        menu.add(nhanVienBtn);
        menu.add(danhMucBtn);
        menu.add(nhaCcBtn);
        menu.add(logoutBtn);
        

        /////////////////////////////////


        // Khởi tạo các thành phần giao diện
        JLabel lblID = new JLabel("ID:");
        JLabel lblHoTen = new JLabel("Họ và tên:");
        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        JLabel lblSDT = new JLabel("Số điện thoại:");
        
        txtID = new JTextField(10);
        //txtID.setEditable(false);// k đc chỉnh sửa
        
        txtHoTen = new JTextField(20);
        txtEmail = new JTextField(20);
        txtDiaChi = new JTextField(30);
        txtSDT = new JTextField(15);

        dateChooser = new JDateChooser();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        dateChooser.setDateFormatString(sdf.toPattern());
        
        btnThem = new JButton("Thêm");
        btnXoa = new JButton("Xóa");
        btnCapNhat = new JButton("Cập nhật");
        // Khởi tạo JComboBox và thêm các phần tử vào nó
        String[] columns = {"Họ tên", "Email", "Địa chỉ"};
        JComboBox<String> cboColumns = new JComboBox<>(columns);
        
        txtSearch = new JTextField(20);
        
        btnSearch = new JButton("Tìm kiếm");

        txtArea = new JTextArea(10, 40);
        JScrollPane scrollPane = new JScrollPane(txtArea);
        

        // Khởi tạo tableModel với các cột tương ứng với thuộc tính của nhân viên
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ và tên");
        tableModel.addColumn("Ngày sinh");
        tableModel.addColumn("Email");
        tableModel.addColumn("Địa chỉ");
        tableModel.addColumn("Số điện thoại");

        // Khởi tạo JTable với tableModel
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);
        
        
        
        // Đặt layout cho JFrame
        
        contentPanel = new JPanel(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridBagLayout());
        JPanel buttonPanel = new JPanel();
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);//trên trái dưới phải

        // Thêm các thành phần vào inputPanel
        
       
        inputPanel.add(lblID, gbc);
        gbc.gridy++;
        inputPanel.add(lblHoTen, gbc);
        gbc.gridy++;
        inputPanel.add(lblNgaySinh, gbc);
        gbc.gridy++; 
        inputPanel.add(lblEmail, gbc);
        gbc.gridy++;
        inputPanel.add(lblDiaChi, gbc);
        gbc.gridy++;
        inputPanel.add(lblSDT, gbc);
       
        
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        inputPanel.add(txtID, gbc);
        gbc.gridy++;
        inputPanel.add(txtHoTen, gbc);
        gbc.gridy++;
        inputPanel.add(dateChooser, gbc);
        gbc.gridy++;
        inputPanel.add(txtEmail, gbc);
        gbc.gridy++;
        inputPanel.add(txtDiaChi, gbc);
        gbc.gridy++;
        inputPanel.add(txtSDT, gbc);
        
      
        
        // Thêm các nút vào buttonPanel
        JButton btnXuatExcel = new JButton("Xuất Excel");
        
        buttonPanel.add(btnXuatExcel);
        
        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        

        buttonPanel.add(btnCapNhat);
        
        JButton btnThoat = new JButton("clear");
        buttonPanel.add(btnThoat);
        
        
        
        buttonPanel.add(txtSearch);
        buttonPanel.add(cboColumns);
        buttonPanel.add(btnSearch);
        
        // Đặt các thành phần trên JFrame
        
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        contentPanel.add(tableScrollPane, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Thêm vào main frame
        add(menu, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER); 
        
        
        // Lấy dữ liệu từ cơ sở dữ liệu và hiển thị lên bảng
        fetchDataAndDisplay();
       
        setVisible(true);
       
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    MenuView mv = new MenuView(userRole);
                    mv.setVisible(true);
            }
        });
        
        taiKhoanBtn.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TaiKhoanView tkv = new TaiKhoanView(userRole);
                tkv.setVisible(true);
            }
        });
        sphamBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                FormThuoc tv = new FormThuoc(userRole);
                
            }
        });
        nhanVienBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        danhMucBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                DanhMucView dmv = new DanhMucView(userRole);
                dmv.setVisible(true);
            }
        });
        nhaCcBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                NhaCCview nccv = new NhaCCview(userRole);
                nccv.setVisible(true);
            }
        });
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất?", "Xác nhận",
                JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    LoginView lv = new LoginView();
                    lv.setVisible(true);
                    dispose();
                }
            }
        });

        txtSDT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                // Giới hạn số lượng ký tự được nhập
                if (txtSDT.getText().length() >= 10) {
                    e.consume(); // Ngăn không cho nhập thêm ký tự
                }
                // Kiểm tra xem ký tự được nhập có phải là số không
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });


       // Xử lý sự kiện khi nhấn vào nút "Thoát"
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xóa việc chọn hàng hiện tại
                table.clearSelection();
                // Xóa dữ liệu đang hiển thị trên form
                txtID.setText("");
                txtHoTen.setText("");
                dateChooser.setDate(null);
                txtEmail.setText("");
                txtDiaChi.setText("");
                txtSDT.setText("");
                fetchDataAndDisplay();
            }
        });
        
        
        //them
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                String id = txtID.getText();
                String hoten = txtHoTen.getText();
                Date age = dateChooser.getDate();
                String email = txtEmail.getText();
                String diachi = txtDiaChi.getText();
                String sdt = txtSDT.getText();
                
                // Kiểm tra định dạng email
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(view_nv.this, "Email không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Thêm dữ liệu vào cơ sở dữ liệu
                try {
                    ctr_nv controller = new ctr_nv();
                    controller.insertData(id, hoten, age, email, diachi, sdt);

                    // Hiển thị dữ liệu mới trên bảng
                    fetchDataAndDisplay(); // Chỉ gọi fetchDataAndDisplay() khi thêm thành công
                    JOptionPane.showMessageDialog(view_nv.this, "Thêm nhân viên thành công!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view_nv.this, "Lỗi khi thêm dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
                //selectedRow = -1;
            }
        });

        
        // Thêm sự kiện cho nút "Tìm kiếm"
        btnSearch.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = txtSearch.getText().trim().toLowerCase(); // Lấy từ khóa tìm kiếm và chuyển đổi sang chữ thường
            //String keyword1 = txtSearch1.getText().trim().toLowerCase();
            int columnIndex = cboColumns.getSelectedIndex(); // Lấy chỉ mục của cột được chọn trong JComboBox
            if (!keyword.isEmpty()) {
                searchEmployee(keyword, columnIndex); // Gọi phương thức searchEmployee để tìm kiếm dữ liệu
            } else {
                JOptionPane.showMessageDialog(view_nv.this, "Vui lòng nhập từ khóa tìm kiếm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    });

        // Gắn sự kiện cho nút "Xuất excel"
        
        btnXuatExcel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Tạo một Workbook mới
            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                // Tạo một Sheet mới
                Sheet sheet = workbook.createSheet("Danh sách nhân viên");
                // Tạo một hàng (Row) đầu tiên trong Sheet chứa tiêu đề cột
                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < table.getColumnCount(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(table.getColumnName(i));
                }

                // Lặp qua các hàng của JTable để ghi dữ liệu vào Workbook
                for (int i = 0; i < table.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        Object value = table.getValueAt(i, j);
                        Cell cell = row.createCell(j);
                        if (value != null) {
                            cell.setCellValue(value.toString()); 
                        }
                    }
                }

                // Lưu Workbook vào một file
                try (OutputStream fileOut = new FileOutputStream("D:\\DanhSachNhanVien.xlsx")) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(view_nv.this, "Xuất Excel thành công!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view_nv.this, "Lỗi khi xuất Excel", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    });


        //xoa
        // Xử lý sự kiện cho nút Xóa
    btnXoa.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lấy chỉ mục của hàng được chọn trong bảng
        int selectedRow = table.getSelectedRow();

        // Kiểm tra xem hàng có được chọn hay không
        if (selectedRow != -1) {
            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(view_nv.this, "Bạn có chắc chắn muốn xóa nhân viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            
            // Kiểm tra xác nhận từ người dùng
            if (confirm == JOptionPane.YES_OPTION) {
                // Lấy ID của nhân viên cần xóa từ bảng
                int id = (int) tableModel.getValueAt(selectedRow, 0);

                try {
                    // Xóa nhân viên có ID tương ứng từ cơ sở dữ liệu
                    ctr_nv controller = new ctr_nv();
                    controller.deleteData(id);

                    // Xóa hàng khỏi bảng
                    tableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(view_nv.this, "Xóa nhân viên thành công!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view_nv.this, "Lỗi khi xóa nhân viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(view_nv.this, "Vui lòng chọn nhân viên cần xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }       
            
        }
    });

        

    // Gắn sự kiện cho bảng để cập nhật selectedRow khi người dùng chọn một hàng mới
    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            selectedRow = table.getSelectedRow();
            // Hiển thị thông tin của nhân viên được chọn trên form
            displaySelectedEmployee();
        }
    });



    // Gắn sự kiện cho nút "Cập nhật" để cập nhật thông tin của nhân viên trong bảng và trong cơ sở dữ liệu
    btnCapNhat.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selectedRow != -1) {
                
                // Lấy thông tin từ các trường nhập liệu
                
                String hoten = txtHoTen.getText();
                Date age = dateChooser.getDate();
                String email = txtEmail.getText();
                String diachi = txtDiaChi.getText();
                String sdt = txtSDT.getText();
                
                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(view_nv.this, "Email không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                 
                // Chuyển đổi ngày tháng thành chuỗi "dd/MM/yyyy"
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = dateFormat.format(age);
                
                // Cập nhật thông tin của nhân viên trong bảng
                //tableModel.setValueAt(id, selectedRow, 1);
                tableModel.setValueAt(hoten, selectedRow, 1);
                tableModel.setValueAt(dateString, selectedRow, 2);
                tableModel.setValueAt(email, selectedRow, 3);
                tableModel.setValueAt(diachi, selectedRow, 4);
                tableModel.setValueAt(sdt, selectedRow, 5);

                // Cập nhật thông tin của nhân viên trong cơ sở dữ liệu
                try {
                    ctr_nv controller = new ctr_nv();
                    controller.updateData((int) tableModel.getValueAt(selectedRow, 0), hoten, age, email, diachi, sdt);
                    JOptionPane.showMessageDialog(view_nv.this, "Cập nhật nhân viên thành công!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view_nv.this, "Lỗi khi cập nhật dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
            JOptionPane.showMessageDialog(view_nv.this, "Vui lòng chọn nhân viên cần cập nhật", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }       
        }
    });


    }
        private boolean isValidEmail(String email) {
         return email.contains("@");
     }

    private void searchEmployee(String keyword, int columnIndex) {
    // Xóa dữ liệu hiện tại trên bảng
    tableModel.setRowCount(0);

    // Gọi hàm tìm kiếm từ lớp controller
    ctr_nv controller = new ctr_nv();
    try {
        ResultSet resultSet = controller.searchData(keyword, columnIndex);
        while (resultSet.next()) {
            // Lấy thông tin từ cơ sở dữ liệu
            int id = resultSet.getInt("id");
            String hoten = resultSet.getString("hoten");
            Date age = resultSet.getDate("age");
            String email = resultSet.getString("email");
            String diachi = resultSet.getString("diachi");
            String sdt = resultSet.getString("sdt");

            // Chuyển đổi ngày tháng thành chuỗi "dd/MM/yyyy"
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = dateFormat.format(age);

            // Tạo vector dữ liệu mới và thêm vào bảng
            Vector<Object> rowData = new Vector<>();
            rowData.add(id);
            rowData.add(hoten);
            rowData.add(dateString);
            rowData.add(email);
            rowData.add(diachi);
            rowData.add(sdt);
            tableModel.addRow(rowData);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}





    
    private void fetchDataAndDisplay(){
        ctr_nv controller = new ctr_nv();
        tableModel.setRowCount(0);
        try {
            ResultSet resultSet = controller.fetchDataFromDatabase();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String hoten = resultSet.getString("hoten");
                Date age = resultSet.getDate("age");
                String email = resultSet.getString("email");
                String diachi = resultSet.getString("diachi");
                String sdt = resultSet.getString("sdt");
                // Chuyển đổi ngày tháng thành chuỗi "dd/MM/yyyy"
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = dateFormat.format(age);

                Vector<Object> rowData = new Vector<>();
                rowData.add(id);
                rowData.add(hoten);
                rowData.add(dateString);
                rowData.add(email);
                rowData.add(diachi);
                rowData.add(sdt);

                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    // Hàm hiển thị thông tin của nhân viên được chọn trên form
    private void displaySelectedEmployee() {
    if (selectedRow != -1) {
        // Lấy thông tin từ bảng và hiển thị trên các trường nhập liệu
        txtID.setText(String.valueOf(tableModel.getValueAt(selectedRow, 0))); // Hiển thị ID
        txtHoTen.setText((String) tableModel.getValueAt(selectedRow, 1));

        // Chuyển đổi chuỗi ngày tháng thành kiểu Date
        String dateString = (String) tableModel.getValueAt(selectedRow, 2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = dateFormat.parse(dateString);
            dateChooser.setDate(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        txtEmail.setText((String) tableModel.getValueAt(selectedRow, 3));
        txtDiaChi.setText((String) tableModel.getValueAt(selectedRow, 4));
        txtSDT.setText((String) tableModel.getValueAt(selectedRow, 5));
    }
}
    // Phương thức để tìm kiếm dữ liệu trong cơ sở dữ liệu dựa trên họ tên hoặc email
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
