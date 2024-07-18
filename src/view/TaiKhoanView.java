 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.TaiKhoanCtrl;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import model.TaiKhoan;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Hiep Phuong
 */
public class TaiKhoanView extends JFrame{
    TaiKhoanCtrl tkc = new TaiKhoanCtrl();
    
    private JButton taiKhoanBtn;
    private JButton sphamBtn;
    private JButton nhanVienBtn;
    private JButton danhMucBtn;
    private JButton nhaCcBtn;
    private JButton logoutBtn;
    
    private JLabel titleLabel;
    private JLabel IDLabel;
    private JLabel emailLabel;
    private JLabel passLabel;
    private JLabel nameLabel;
    private JLabel quyenLabel;
    private JLabel findLabel;
    private JLabel findIcon;
    private JLabel resetIcon;
    private JLabel count;
    
    private JTextField IDField;
    private JTextField emailField;
    private JTextField passField;
    private JTextField nameField;
    private JTextField quyenField;
    private JTextField findField;
    
    private JComboBox findCBO;
    
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JButton sortByNameBtn;
    private JButton sortByIDBtn;
    private JButton ExcelBtn;
    
    private JScrollPane ScrollTable;
    private JTable taiKhoanTable;
    
    private DefaultTableModel tableModel;
    
    private final String[] columnNames = new String[] {
            "id", "email", "mật khẩu", "họ tên", "quyền"
    };
    
    private final Object data = new Object[][]{};
    
    private DefaultTableModel dtm = new DefaultTableModel((Object[][]) data, columnNames);
    private DefaultTableModel fdtm = new DefaultTableModel((Object[][]) data, columnNames);
    public TaiKhoanView(String userRole){
        
        initComponents(userRole);
    }
    private void initComponents(String userRole){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
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
        
        titleLabel = new JLabel("QUẢN LÝ TÀI KHOẢN");
        IDLabel = new JLabel("ID: ");
        emailLabel = new JLabel("Email: ");
        passLabel = new JLabel("Mật khẩu: ");
        nameLabel = new JLabel("Họ tên: ");
        quyenLabel = new JLabel("Quyền: ");
        findLabel = new JLabel("Tìm kiếm: ");
        ImageIcon find = new ImageIcon("./img/find.png");
        Image findIma = find.getImage();
        Image findImage = findIma.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon findI = new ImageIcon(findImage);
        findIcon = new JLabel();
        findIcon.setIcon(findI);
        ImageIcon reset = new ImageIcon("./img/reset.png");
        Image resetIma = reset.getImage();
        Image resetImage = resetIma.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon resetI = new ImageIcon(resetImage);
        resetIcon = new JLabel();
        resetIcon.setIcon(resetI);
        count = new JLabel();
        
        findCBO = new JComboBox(new String[]{"Email","Họ tên","Quyền"});
        findCBO.setPreferredSize(new Dimension(70,20));
        
        IDField = new JTextField(15);
        emailField = new JTextField(15);
        passField = new JTextField(15);
        nameField = new JTextField(15);
        quyenField = new JTextField(15);
        findField = new JTextField(15);
        
        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortByNameBtn = new JButton("Sort by Name");
        sortByIDBtn = new JButton("Sort by ID");
        ExcelBtn = new JButton("Export Excel");
        
        ScrollTable = new JScrollPane();
        taiKhoanTable = new JTable();
        
        tableModel = new DefaultTableModel((Object[][]) data, columnNames);
        taiKhoanTable.setModel(tableModel);
        ScrollTable.setViewportView(taiKhoanTable);
        ScrollTable.setPreferredSize(new Dimension(480,300));
        
        FlowLayout layout_menu = new FlowLayout(FlowLayout.LEFT, 0, 0);
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(850,30));
        menu.setLayout(layout_menu);
        
        menu.add(taiKhoanBtn);
        menu.add(sphamBtn);
        menu.add(nhanVienBtn);
        menu.add(danhMucBtn);
        menu.add(nhaCcBtn);
        menu.add(logoutBtn);
        
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(850, 400);
        panel.setLayout(layout);
        
        panel.add(menu);
        
        panel.add(ScrollTable);
        
        panel.add(titleLabel);
        panel.add(IDLabel);
        panel.add(emailLabel);
        panel.add(passLabel);
        panel.add(nameLabel);
        panel.add(quyenLabel);
        panel.add(findLabel);
        panel.add(findIcon);
        panel.add(resetIcon);
        panel.add(count);
        panel.add(findCBO);
        
        panel.add(IDField);
        panel.add(emailField);
        panel.add(passField);
        panel.add(nameField);
        panel.add(quyenField);
        panel.add(findField);
        
        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(clearBtn);
        panel.add(sortByNameBtn);
        panel.add(sortByIDBtn);
        panel.add(ExcelBtn);
        
        layout.putConstraint(SpringLayout.WEST, menu, 0, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, menu, 0, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, titleLabel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, IDLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quyenLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quyenLabel, 190, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, IDField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passField, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quyenField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quyenField, 190, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editBtn, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editBtn, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteBtn, 160, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 245, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 230, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, sortByNameBtn, 330, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByNameBtn, 380, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortByIDBtn, 450, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByIDBtn, 380, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, ExcelBtn, -25, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, ExcelBtn, 380, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, ScrollTable, 330, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ScrollTable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, count, 330, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, count, 355, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, findCBO, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, findCBO, 355, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, findLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, findLabel, 385, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, findField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, findField, 385, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, findIcon, 270, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, findIcon, 385, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, resetIcon, 296, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, resetIcon, 385, SpringLayout.NORTH, panel);
        
        this.setTitle("QUẢN LÝ TÀI KHOẢN");
        this.setSize(850,500);
        this.setLocationRelativeTo(null);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
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
                    setVisible(false);
                    view_nv nvv = new view_nv(userRole);
                    nvv.setVisible(true);
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
        
        findCBO.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) findCBO.getSelectedItem();
                findField.setText("");
                for (KeyListener listener : findField.getKeyListeners()) {
                    findField.removeKeyListener(listener);
                }
            }
        });
        
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_actionPerformed();
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edit_actionPerformed();
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete_actionPerformed();
            }
        });
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear_actionPerformed();
            }
        });
        sortByNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortname_actionPerformed();
            }
        });
        
        sortByIDBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortid_actionPerformed();
            }
        });
        
        ExcelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excel_actionPerformed();
            }
        });
        
        resetIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                findField.setEditable(true);
                removeTable(); 
                fdtm.setRowCount(0);
                findField.setText("");
                findField.setFocusable(true);
                taiKhoanTable.setModel(tableModel);
                ScrollTable.setViewportView(taiKhoanTable);
                int rowCount= tableModel.getRowCount(); 
                count.setText("Có "+ rowCount + " tài khoản");
                JOptionPane.showMessageDialog(null, "Reset!");
            }
        });
        
        findIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                  
                String search = findCBO.getSelectedItem().toString();
                String keyword = findField.getText();
                
                if(keyword.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bạn đang để trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else{
                    removeTable();
                    fdtm.setColumnIdentifiers(columnNames);
                    TaiKhoanCtrl tkc = new TaiKhoanCtrl();
                    tkc.Timkiem(fdtm,search,keyword);
                    taiKhoanTable.setModel(fdtm);
                    ScrollTable.setViewportView(taiKhoanTable);
                    int rowCount= fdtm.getRowCount(); 
                    count.setText("Có "+ rowCount + " tài khoản");
                    findField.setText("");
                    findField.setFocusable(false);
                    findField.setEditable(false);
                    clear_actionPerformed();
                    JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");
                }
            }
        });
       
        taiKhoanTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                
                int selectedRow= taiKhoanTable.getSelectedRow();
            
                if (selectedRow != -1 && selectedRow < taiKhoanTable.getRowCount()) {
                    IDField.setEditable(false);
                    Object idValue = taiKhoanTable.getValueAt(selectedRow, 0);
                    Object nameValue = taiKhoanTable.getValueAt(selectedRow, 1);
                    Object ageValue = taiKhoanTable.getValueAt(selectedRow, 2);
                    Object addValue = taiKhoanTable.getValueAt(selectedRow, 3);
                    Object gpaValue = taiKhoanTable.getValueAt(selectedRow, 4);

                    IDField.setText(String.valueOf(idValue));
                    emailField.setText(String.valueOf(nameValue));
                    passField.setText(String.valueOf(ageValue));
                    nameField.setText(String.valueOf(addValue));
                    quyenField.setText(String.valueOf(gpaValue));
                    khoa(false);      

                }
            }
        });
        
        updateData(tkc.taikhoan());
        khoa(true);
    }
    
    private boolean validateText(String text) {
        if (!text.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@gmail\\.com$")) {
            // Nếu chuỗi không khớp với định dạng gmail, xóa ký tự vừa nhập
            emailField.setText("");
            return false;
        }
        else{
            return true;
        }
    }
    
    private boolean checkid(String id) {
        int rowCount = tableModel.getRowCount();
        String lowercase = id.toLowerCase(); 
        for (int i = 0; i < rowCount; i++) {
            String exist = (String) tableModel.getValueAt(i, 0); 
            if (exist.equals(lowercase)) {
                return true; 
            }
        }
        return false; 
    }
    
    private boolean checkEmail(String email) {
        int rowCount = tableModel.getRowCount();
        String lowercase = email.toLowerCase(); 
        for (int i = 0; i < rowCount; i++) {
            String exist = (String) tableModel.getValueAt(i, 1); 
            if (exist.equals(lowercase)) {
                return true; 
            }
        }
        return false; 
    }
   
    public void add_actionPerformed(){
        String id_ch= IDField.getText();
        if (id_ch.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập id tài khoản!", "Thông báo", JOptionPane.WARNING_MESSAGE);
          
        }
        if(!validateText(emailField.getText())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ Gmail hợp lệ!");
            emailField.setText("");
        }
        if(checkid(id_ch)){
            JOptionPane.showMessageDialog(null, "ID đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            IDField.setText("");
        }
        if(checkEmail(emailField.getText())){
            JOptionPane.showMessageDialog(null, "Email đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            emailField.setText("");
        }
        boolean check_email = validateText(emailField.getText());
        boolean id_exists = checkid(id_ch);
        boolean email_exists = checkEmail(emailField.getText());
        if(!id_exists && (check_email== true) && !email_exists){
            String id = IDField.getText();
            String email = emailField.getText();
            String pass = passField.getText();
            String hoten = nameField.getText();
            String quyen= quyenField.getText();

            Object[] rowData = {id, email, pass, hoten, quyen};
            tableModel.addRow(rowData);

            TaiKhoan tk = new TaiKhoan();
            setInfo(tk);

            TaiKhoanCtrl tkc = new TaiKhoanCtrl();
            tkc.addTaiKhoan(tk);
            clear_actionPerformed();
            int rowCount= tableModel.getRowCount(); 
            count.setText("Có "+ rowCount + " tài khoản");
        }else{
            JOptionPane.showMessageDialog(null, "Thêm không thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            //IDField.setText("");
            IDField.requestFocus();
        }
    }
    
    public void edit_actionPerformed(){
        String newEmail = emailField.getText();
        int selectedRow= taiKhoanTable.getSelectedRow();
        if(!validateText(emailField.getText())){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập địa chỉ Gmail hợp lệ!");
            //emailField.setText("");
        }
        else if((!newEmail.equals(taiKhoanTable.getValueAt(selectedRow, 1))) && checkEmail(emailField.getText())){
            JOptionPane.showMessageDialog(null, "Email đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            emailField.setText("");
        }
        else{
            TaiKhoan tk = new TaiKhoan();
            setInfo(tk);

            TaiKhoanCtrl tkc = new TaiKhoanCtrl();
            tkc.editTaiKhoan(tk);
            updateTabaleRow(tk);
            clear_actionPerformed();
        }
        
        
    }
    
    public void delete_actionPerformed(){
        TaiKhoan tk = new TaiKhoan();
        tk.setId(IDField.getText());
        int confirmed = JOptionPane.showConfirmDialog(null,
                        "Bạn có muốn xóa tài khoản "+IDField.getText()+"",
                        "Xác nhận xóa",
                        JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                TaiKhoanCtrl tkc = new TaiKhoanCtrl();
                tkc.deleteTaiKhoan(tk);
                deleteTableRow(tk);
                clear_actionPerformed();
                int rowCount= tableModel.getRowCount(); 
                count.setText("Có "+ rowCount + " tài khoản");
            } 
        
    }
    
    public void clear_actionPerformed(){
        IDField.setText("");
        emailField.setText("");
        passField.setText("");
        nameField.setText("");
        quyenField.setText("");
        IDField.setEditable(true);
        taiKhoanTable.clearSelection();
        khoa(true);
    }
    
    public void sortname_actionPerformed(){
        removeTable(); 
        TaiKhoanCtrl tkc = new TaiKhoanCtrl();
        tkc.sortTaiKhoan(dtm);
        dtm.setColumnIdentifiers(columnNames);
        taiKhoanTable.setModel(dtm);
        ScrollTable.setViewportView(taiKhoanTable);
        JOptionPane.showMessageDialog(null, "Sắp xếp thành công");
    }
    
    public void sortid_actionPerformed(){
        removeTable();
        tableModel.setColumnIdentifiers(columnNames);
        taiKhoanTable.setModel(tableModel);
        ScrollTable.setViewportView(taiKhoanTable);
        JOptionPane.showMessageDialog(null, "Sắp xếp thành công");
    }
    
    public void excel_actionPerformed(){
        // Tạo workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Tài khoản");
        // Ghi dữ liệu từ bảng vào file Excel
        int rowCount = taiKhoanTable.getRowCount();
        int columnCount = taiKhoanTable.getColumnCount();
        XSSFRow headerRow = sheet.createRow(0);
        
        
        sheet.setColumnWidth(0, 2000);
        sheet.setColumnWidth(1, 7000);
        sheet.setColumnWidth(2, 2000);
        sheet.setColumnWidth(3, 2000);
        sheet.setColumnWidth(4, 2000);
        // Ghi tiêu đề cột vào hàng tiêu đề
        for (int j = 0; j < columnCount; j++) {
            String columnHeader = taiKhoanTable.getColumnName(j); // Lấy tên cột từ JTable
            headerRow.createCell(j).setCellValue(columnHeader);
            
            Cell headerCell = headerRow.createCell(0);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER); // Căn giữa cho ô
            XSSFFont headerFont = workbook.createFont();
            headerFont.setBold(true); // Đặt đậm cho font
            headerCellStyle.setFont(headerFont);
            headerCell.setCellStyle(headerCellStyle);
        }
        for (int i = 1; i < rowCount; i++) {
            XSSFRow row = (XSSFRow) sheet.createRow(i);
            for (int j = 0; j < columnCount; j++) {
                Object value = taiKhoanTable.getValueAt(i, j);
                Cell cell = row.createCell(j);
                if (value != null) {
                    cell.setCellValue(value.toString());
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream("taikhoan.xlsx")) {
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(null, "Xuất excel thành công!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void removeTable(){
        Container container = taiKhoanTable.getParent(); // Lấy container chứa table (JScrollPane)
        container.removeAll(); // Xóa bỏ tất cả các thành phần con
        container.revalidate(); // Cập nhật lại giao diện
        container.repaint();
    }
    
    public void setInfo(TaiKhoan tk){
        tk.setId(IDField.getText());
        tk.setEmail(emailField.getText());
        tk.setPass(passField.getText());
        tk.setHoten(nameField.getText());
        tk.setQuyen(quyenField.getText());
    }
    
    
    public void updateData(ResultSet result){
        tableModel.setColumnIdentifiers(columnNames);
           
        try {
            while(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[5];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng)
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                rows[2] = result.getString(3);
                rows[3] = result.getString(4);
                rows[4] = result.getString(5);
                tableModel.addRow(rows); 
                int rowCount= tableModel.getRowCount(); 
                count.setText("Có "+ rowCount + " tài khoản");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateTabaleRow(TaiKhoan updatedData){
        String idToUpdate = updatedData.getId();

        int rowIndex = -1;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (idToUpdate.equals(tableModel.getValueAt(i, 0).toString())) {
                rowIndex = i;
                break;
            }
        }
        if (rowIndex != -1) {
            tableModel.setValueAt(updatedData.getId(), rowIndex, 0); 
            tableModel.setValueAt(updatedData.getEmail(), rowIndex, 1);
            tableModel.setValueAt(updatedData.getPass(), rowIndex, 2);
            tableModel.setValueAt(updatedData.getHoten(), rowIndex, 3);
            tableModel.setValueAt(updatedData.getQuyen(), rowIndex, 4);
        }
    }
    
    public void deleteTableRow(TaiKhoan deleteData){
        
        String idToDelete = deleteData.getId();
        int rowIndex = -1;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (idToDelete.equals(tableModel.getValueAt(i, 0).toString())) { 
                rowIndex = i;
                break;
            }
        }

        if (rowIndex != -1) {
            tableModel.removeRow(rowIndex);
        }
    }
           
    public void khoa(boolean b){
        addBtn.setEnabled(b);
        editBtn.setEnabled(!b);
        deleteBtn.setEnabled(!b);
    }
    
    public static void main(String[] args) {
        LoginView lv = new LoginView();
    }
}
