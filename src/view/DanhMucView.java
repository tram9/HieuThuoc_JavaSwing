/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.DanhMucCtrl;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.DanhMuc;
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
public class DanhMucView extends JFrame{
    DanhMucCtrl dmct = new DanhMucCtrl();
    private JButton taiKhoanBtn;
    private JButton sphamBtn;
    private JButton nhanVienBtn;
    private JButton danhMucBtn;
    private JButton nhaCcBtn;
    private JButton logoutBtn;
    private JLabel background;
    
    private JLabel titleLabel;
    private JLabel IDLabel;
    private JLabel nameLabel;
    private JLabel findLabel;
    private JLabel findIcon;
    private JLabel resetIcon;
    
    private JTextField IDField;
    private JTextField nameField;
    private JTextField findField;
    
    private JButton addBtn;
    private JButton editBtn;
    private JButton deleteBtn;
    private JButton clearBtn;
    private JButton sortByNameBtn;
    private JButton ExcelBtn;
    
     private JScrollPane ScrollTable;
    private JTable danhMucTable;
    
    private DefaultTableModel tableModel;
    
    private final String[] columnNames = new String[] {
            "id", "tên loại"
    };
    
    private final Object data = new Object[][]{};
    private DefaultTableModel dtm = new DefaultTableModel((Object[][]) data, columnNames);
    private DefaultTableModel fdtm = new DefaultTableModel((Object[][]) data, columnNames);
    public DanhMucView(String userRole){
        initComponents(userRole);
    }
    private void initComponents(String userRole){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        
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
        
        if(!userRole.equals("admin")){
            taiKhoanBtn.setEnabled(false);
            nhanVienBtn.setEnabled(false);
        }
        
        titleLabel = new JLabel("QUẢN LÝ DANH MỤC");
        IDLabel = new JLabel("ID: ");
        nameLabel = new JLabel("Tên loại: ");
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
        
        IDField = new JTextField(15);
        nameField = new JTextField(15);
        findField = new JTextField(15);
        addPlaceholder(findField, "tìm kiếm theo tên ");
        
        addBtn = new JButton("Add");
        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        sortByNameBtn = new JButton("Sort by Name");
        ExcelBtn = new JButton("Export Excel");
        
        ScrollTable = new JScrollPane();
        danhMucTable = new JTable();
        
        tableModel = new DefaultTableModel((Object[][]) data, columnNames);
        danhMucTable.setModel(tableModel);
        ScrollTable.setViewportView(danhMucTable);
        ScrollTable.setPreferredSize(new Dimension(400,300));
        
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
        panel.setSize(850, 450);
        panel.setLayout(layout);
        
        panel.add(menu);
        panel.add(ScrollTable);
        
        panel.add(titleLabel);
        panel.add(IDLabel);
        panel.add(nameLabel);
        panel.add(findLabel);
        panel.add(findIcon);
        panel.add(resetIcon);
        
        panel.add(IDField);
        panel.add(nameField);
        panel.add(findField);
        
        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(clearBtn);
        panel.add(sortByNameBtn);
        panel.add(ExcelBtn);
        
        layout.putConstraint(SpringLayout.WEST, menu, 0, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, menu, 0, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, titleLabel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLabel, 60, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, IDLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 140, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, IDField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, IDField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 140, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 180, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editBtn, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editBtn, 180, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteBtn, 160, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn, 180, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 245, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 180, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, sortByNameBtn, 360, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByNameBtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, ExcelBtn, -76, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, ExcelBtn, 360, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, ScrollTable, 360, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ScrollTable, 40, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, findLabel, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, findLabel, 365, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, findField, 90, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, findField, 365, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, findIcon, 270, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, findIcon, 365, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, resetIcon, 296, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, resetIcon, 365, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.setTitle("QUẢN LÝ DANH MỤC");
        this.setSize(850,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    MenuView mv = new MenuView(userRole);
                    //mv.khoa();
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
                    setVisible(false);
                    view_nv nvv = new view_nv(userRole);
                    nvv.setVisible(true);
                }
            });
            danhMucBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
        
        addBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String id_ch= IDField.getText();
                if (id_ch.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập id tài khoản!", "Thông báo", JOptionPane.WARNING_MESSAGE);

                }

                boolean exists = checkid(id_ch);
                if(!exists){
                    String id = IDField.getText();
                    String tenloai = nameField.getText();

                    Object[] rowData = {id, tenloai};
                    tableModel.addRow(rowData);
                    DanhMuc dm = new DanhMuc();
                    dm.setId(IDField.getText());
                    dm.setName(nameField.getText());

                    DanhMucCtrl dmct = new DanhMucCtrl();
                    dmct.addDanhMuc(dm);
                }else{
                    JOptionPane.showMessageDialog(null, "Mã nhà cung cấp đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    IDField.setText("");
                    IDField.requestFocus();
                }
            } 
        });
        
        editBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DanhMuc dm = new DanhMuc();
                dm.setId(IDField.getText());
                dm.setName(nameField.getText());

                DanhMucCtrl dmct = new DanhMucCtrl();
                dmct.editDanhMuc(dm);
                updateTabaleRow(dm);
            } 
        });
        
        deleteBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                DanhMuc dm = new DanhMuc();
                dm.setId(IDField.getText());
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Bạn có muốn xóa loại sản phẩm "+IDField.getText()+"",
                        "Xác nhận xóa",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    DanhMucCtrl dmct = new DanhMucCtrl();
                    dmct.deleteDanhMuc(dm);
                    deleteTableRow(dm);
                    clearBtn.doClick(); 
                } 
            } 
        });
        
        sortByNameBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTable(); 
                DanhMucCtrl tkc = new DanhMucCtrl();
                tkc.sortDanhMuc(dtm);
                dtm.setColumnIdentifiers(columnNames);
                danhMucTable.setModel(dtm);
                ScrollTable.setViewportView(danhMucTable);
                clearBtn.doClick(); 
                JOptionPane.showMessageDialog(null, "Sắp xếp thành công");
            } 
        });
        
        clearBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                IDField.setText("");
                nameField.setText("");
                IDField.setEditable(true);
                danhMucTable.clearSelection();
                khoa(true);
            } 
        });
        
        resetIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                removeTable(); 
                findField.setText("");
                findField.setEditable(true);
                danhMucTable.setModel(tableModel);
                ScrollTable.setViewportView(danhMucTable);
                JOptionPane.showMessageDialog(null, "Reset!");
            }
        });
        
        
        
        findIcon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                String keyword = findField.getText();
                
                if(keyword.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Bạn đang để trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }else{
                    removeTable();  
                    fdtm.setColumnIdentifiers(columnNames);
                    DanhMucCtrl dmct = new DanhMucCtrl();
                    dmct.Timkiem(fdtm,keyword);
                    danhMucTable.setModel(fdtm);
                    ScrollTable.setViewportView(danhMucTable);
                    findField.setEditable(false);
                    JOptionPane.showMessageDialog(null, "Tìm kiếm thành công");
                }   
            }
        });
        
        ExcelBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Danh Mục");
                int rowCount = danhMucTable.getRowCount();
                int columnCount = danhMucTable.getColumnCount();
                XSSFRow headerRow = sheet.createRow(0);

                sheet.setColumnWidth(0, 2000);
                sheet.setColumnWidth(1, 7000);
                for (int j = 0; j < columnCount; j++) {
                    String columnHeader = danhMucTable.getColumnName(j); 
                    headerRow.createCell(j).setCellValue(columnHeader);

                    Cell headerCell = headerRow.createCell(0);
                    CellStyle headerCellStyle = workbook.createCellStyle();
                    headerCellStyle.setAlignment(HorizontalAlignment.CENTER); 
                    XSSFFont headerFont = workbook.createFont();
                    headerFont.setBold(true); 
                    headerCellStyle.setFont(headerFont);
                    headerCell.setCellStyle(headerCellStyle);
                }
                for (int i = 1; i < rowCount; i++) {
                    XSSFRow row = (XSSFRow) sheet.createRow(i);
                    for (int j = 0; j < columnCount; j++) {
                        Object value = danhMucTable.getValueAt(i, j);
                        Cell cell = row.createCell(j);
                        if (value != null) {
                            cell.setCellValue(value.toString());
                        }
                    }
                }

                try (FileOutputStream outputStream = new FileOutputStream("danhmuc.xlsx")) {
                    workbook.write(outputStream);
                    JOptionPane.showMessageDialog(null, "Xuất excel thành công!");
                } catch (IOException i) {
                    i.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + i.getMessage());
                }
            } 
        });
        
        danhMucTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                
                int selectedRow= danhMucTable.getSelectedRow();
            
                if (selectedRow != -1 && selectedRow < danhMucTable.getRowCount()) {
                    IDField.setEditable(false);
                    Object idValue = danhMucTable.getValueAt(selectedRow, 0);
                    Object nameValue = danhMucTable.getValueAt(selectedRow, 1);

                    IDField.setText(String.valueOf(idValue));
                    nameField.setText(String.valueOf(nameValue));
                    khoa(false);      

                }
            }
        });
        updateData(dmct.danhmuc());
        khoa(true);
    }
    
    public void removeTable(){
        Container container = danhMucTable.getParent(); // Lấy container chứa table (JScrollPane)
        container.removeAll(); // Xóa bỏ tất cả các thành phần con
        container.revalidate(); // Cập nhật lại giao diện
        container.repaint();
    }
    
    public void khoa(boolean b){
        addBtn.setEnabled(b);
        editBtn.setEnabled(!b);
        deleteBtn.setEnabled(!b);
    }
    
    public void updateData(ResultSet result){
        tableModel.setColumnIdentifiers(columnNames);
 
        try {
            while(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[2];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã hàng)
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên hàng
                tableModel.addRow(rows); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    
    public void updateTabaleRow(DanhMuc updatedData){
        // Get the ID to identify the row
        String idToUpdate = updatedData.getId();

        // Find the row index in the table model based on the ID
        int rowIndex = -1;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (idToUpdate.equals(tableModel.getValueAt(i, 0).toString())) { // Assuming ID is in the first column
                rowIndex = i;
                break;
            }
        }
        // Update the row if found
        if (rowIndex != -1) {
            // Update the corresponding row in the table model
            tableModel.setValueAt(updatedData.getId(), rowIndex, 0); // Assuming ID is in the first column
            tableModel.setValueAt(updatedData.getName(), rowIndex, 1);
            
        }
    }
    
    public void deleteTableRow(DanhMuc deleteData){
        
        String idToDelete = deleteData.getId();
        int rowIndex = -1;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (idToDelete.equals(tableModel.getValueAt(i, 0).toString())) { // Assuming ID is in the first column
                rowIndex = i;
                break;
            }
        }

    // Remove the row if found
        if (rowIndex != -1) {
            tableModel.removeRow(rowIndex);
        }
    }
    
    private void addPlaceholder(final JTextField textField, final String placeholder) {
        textField.setForeground(Color.GRAY);
        textField.setText(placeholder);
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
    
    public static void main(String[] args) {
        //DanhMucView dmv = new DanhMucView(userR);
        LoginView lv = new LoginView();
    }
}
