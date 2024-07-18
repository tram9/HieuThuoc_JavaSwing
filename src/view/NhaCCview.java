/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import controller.NhaCCctrl;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.NhaCCmodel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ngoc Lien - PC
 */
public class NhaCCview extends JFrame {
    private NhaCCctrl control;
    private JButton taiKhoanBtn;
    private JButton sphamBtn;
    private JButton nhanVienBtn;
    private JButton danhMucBtn;
    private JButton nhaCcBtn;
    private JButton logoutBtn;
    private JLabel background;
    
    JButton AddBtn,EditBtn,ClearBtn,DeleteBtn,TimkiemBtn,ExcelBtn;
    JLabel MaLabel,TenLabel,DiachiLabel,SDTLabel,MSTLabel,GhiChuLabel;
    JTextField MaField,TenField,DiachiField,SDTField,MSTField,GhiChuField,TimkiemField;
    DefaultTableModel tablemodel;
    JTable NhaCC_tbl;
    JScrollPane scrollPane;
    String[] columnName= {"MaNhaCC","TenNhaCC","Địa chỉ","Số điện thoại","Mã số thuế","Ghi chú"};
    
    public NhaCCview(String userRole){
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
        
        this.setTitle("Nhà cung cấp");
        this.setSize(850, 500);
        
        MaLabel=new JLabel("Mã nhà CC");
        TenLabel=new JLabel("Tên nhà CC");
        DiachiLabel=new JLabel("Địa chỉ");
        SDTLabel=new JLabel("SĐT");
        MSTLabel=new JLabel("Mã số thuế");
        GhiChuLabel=new JLabel("Ghi chú");
        
        MaField=new JTextField(28);
        TenField=new JTextField(28);
        DiachiField=new JTextField(28);
        SDTField=new JTextField(28);
        
        MSTField=new JTextField(28);
        GhiChuField=new JTextField(28);
        TimkiemField=new JTextField(22);
        addPlaceholder(TimkiemField, "Mã / Tên/ Địa chỉ ");
        
        AddBtn=new JButton("Add");
        AddBtn.setPreferredSize(new Dimension(100,25));
        EditBtn=new JButton("Edit");
        EditBtn.setPreferredSize(new Dimension(100,25));
        ClearBtn=new JButton("Clear");
        ClearBtn.setPreferredSize(new Dimension(100,25));
        DeleteBtn=new JButton("Delete");
        DeleteBtn.setPreferredSize(new Dimension(100,25));
        TimkiemBtn=new JButton("Tìm kiếm");
        ExcelBtn=new JButton("Xuất Excel");
        ExcelBtn.setPreferredSize(new Dimension(100,25));
        
        Object data =new Object[][]{};
        tablemodel =new DefaultTableModel((Object[][]) data,columnName);
        NhaCC_tbl= new JTable(tablemodel);
        scrollPane= new JScrollPane(NhaCC_tbl);
        NhaCC_tbl.setModel(tablemodel);
        scrollPane.setViewportView(NhaCC_tbl);
        scrollPane.setPreferredSize(new Dimension(820,200));
        
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
        
        SpringLayout layout= new SpringLayout();
        JPanel pa=new JPanel();
//        pa.setSize(800, 450);
        pa.setLayout(layout);
        pa.add(menu);
        pa.add(scrollPane);
        
        pa.add(MaLabel);
        pa.add(TenLabel);
        pa.add(DiachiLabel);
        pa.add(SDTLabel);
        pa.add(MSTLabel);
        pa.add(GhiChuLabel);
        
        pa.add(MaField);
        pa.add(TenField);
        pa.add(DiachiField);
        pa.add(SDTField);
        pa.add(MSTField);
        pa.add(GhiChuField);
        pa.add(TimkiemField);
        
        pa.add(AddBtn);
        pa.add(EditBtn);
        pa.add(ClearBtn);
        pa.add(DeleteBtn);
        pa.add(TimkiemBtn);
        pa.add(ExcelBtn);
        
        layout.putConstraint(SpringLayout.WEST, menu, 0, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, menu, 0, SpringLayout.NORTH, pa);
        
        layout.putConstraint(SpringLayout.WEST, TimkiemField, 30, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, TimkiemField, 42, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, TimkiemBtn, 300, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, TimkiemBtn, 40, SpringLayout.NORTH  , pa);
        
        layout.putConstraint(SpringLayout.WEST, MaLabel, 30, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, MaLabel, 80, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, MaField, 100, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, MaField, 80, SpringLayout.NORTH  , pa);
        
        layout.putConstraint(SpringLayout.WEST, TenLabel, 30, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, TenLabel, 110, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, TenField, 100, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, TenField, 110, SpringLayout.NORTH  , pa);
        
        layout.putConstraint(SpringLayout.WEST, DiachiLabel, 30, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, DiachiLabel, 140, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, DiachiField, 100, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, DiachiField, 140, SpringLayout.NORTH  , pa);
        
        layout.putConstraint(SpringLayout.WEST, SDTLabel, 430, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, SDTLabel, 80, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, SDTField, 500, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, SDTField, 80, SpringLayout.NORTH  , pa);
        
        layout.putConstraint(SpringLayout.WEST, MSTLabel, 430, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, MSTLabel, 110, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, MSTField, 500, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, MSTField, 110, SpringLayout.NORTH  , pa);
        
        layout.putConstraint(SpringLayout.WEST, GhiChuLabel, 430, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, GhiChuLabel, 140, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, GhiChuField, 500, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, GhiChuField, 140, SpringLayout.NORTH  , pa);
        
        layout.putConstraint(SpringLayout.WEST, AddBtn, 100, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, AddBtn, 180, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, EditBtn, 220, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, EditBtn, 180, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, ClearBtn, 340, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, ClearBtn, 180, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, DeleteBtn, 460, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, DeleteBtn, 180, SpringLayout.NORTH  , pa);
        layout.putConstraint(SpringLayout.WEST, ExcelBtn, 580, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, ExcelBtn, 180, SpringLayout.NORTH  , pa);
        
        layout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, pa);
        layout.putConstraint(SpringLayout.NORTH, scrollPane, 230, SpringLayout.NORTH, pa);
        
        add(pa);
        control=new NhaCCctrl();
        control.NhaCCctrl();// kết nối cơ sở dữ liệu
        updateData(control.danhsach());// gọi hàm view để truy suất dữ liệu sau đó truyền cho hàm updateData để đưa dữ liệu vào tableModel và hiện lên Jtable trong Frame
        this.setVisible(true);
        requestFocusInWindow();
        setResizable(false);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                    MenuView mv = new MenuView(userRole);
                    mv.khoa();
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
                    setVisible(false);
                    DanhMucView dmv = new DanhMucView(userRole);
                    dmv.setVisible(true);
                }
            });
            nhaCcBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
        
        EditBtn.setEnabled(false);
        DeleteBtn.setEnabled(false);
        AddBtn.addActionListener(e -> AddBtn_actionPerformed());
        EditBtn.addActionListener(e -> EditBtn_actionPerformed());
        ClearBtn.addActionListener(e -> ClearBtn_actionPerformed());
        DeleteBtn.addActionListener(e -> DeleteBtn_actionPerformed());
        ExcelBtn.addActionListener(e -> ExcelBtn_actionPerformed());
        TimkiemBtn.addActionListener(e -> TimkiemBtn_actionPerformed());
        
        SDTField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // Kiểm tra nếu ký tự không phải là số
                if(SDTField.getText().length()>=10){
                    e.consume();
                }
                if (!Character.isDigit(c)) {
                    // Không cho phép nhập ký tự này vào ô văn bản
                    e.consume();
                }
            }
            // Các phương thức khác của KeyListener không được sử dụng ở đây
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        MSTField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent es) {
                char b = es.getKeyChar();
                if(MSTField.getText().length()>=10){
                    es.consume();
                }
                // Kiểm tra nếu ký tự không phải là số
                if (!Character.isDigit(b)) {
                    // Không cho phép nhập ký tự này vào ô văn bản
                    es.consume();
                }
            }
            // Các phương thức khác của KeyListener không được sử dụng ở đây
            @Override
            public void keyPressed(KeyEvent es) {}
            @Override
            public void keyReleased(KeyEvent es) {}
        });
        NhaCC_tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

        public void valueChanged(ListSelectionEvent e) {
            MaField.setEditable(false); // id chỉ đọc
            MSTField.setEditable(false);
            EditBtn.setEnabled(true);
            DeleteBtn.setEnabled(true);
            int selectedRow= NhaCC_tbl.getSelectedRow();

            if (selectedRow != -1 && selectedRow < NhaCC_tbl.getRowCount()) {
            // Retrieve data from the selected row and update JTextFields
                Object maValue = NhaCC_tbl.getValueAt(selectedRow, 0);
                Object tenValue = NhaCC_tbl.getValueAt(selectedRow, 1);
                Object dcValue = NhaCC_tbl.getValueAt(selectedRow, 2);
                Object sdtValue = NhaCC_tbl.getValueAt(selectedRow, 3);
                Object mstValue = NhaCC_tbl.getValueAt(selectedRow, 4);
                Object ghichuValue = NhaCC_tbl.getValueAt(selectedRow, 5);
                
                MaField.setText(String.valueOf(maValue));
                TenField.setText(String.valueOf(tenValue));
                DiachiField.setText(String.valueOf(dcValue));
                SDTField.setText(String.valueOf(sdtValue));
                MSTField.setText(String.valueOf(mstValue));
                GhiChuField.setText(String.valueOf(ghichuValue));
                }
            }
        });
    }
    
    public NhaCCmodel getAccount(NhaCCmodel mo) {
        mo.setMaNhaCC(MaField.getText());
        mo.setTenNhaCC(TenField.getText());
        mo.setDiachi(DiachiField.getText());
        mo.setSDT(SDTField.getText());
        mo.setMST(MSTField.getText());
        mo.setGhichu(GhiChuField.getText());
        return mo;
    }           
    
    public void updateData(ResultSet result){
        tablemodel.setColumnIdentifiers(columnName); // Đặt tiêu đề cho bảng theo các giá trị của mảng columnName
 
        try {
            while(result.next()){ // nếu còn đọc tiếp được một dòng dữ liệu
                String rows[] = new String[6];
                rows[0] = result.getString(1); // lấy dữ liệu tại cột số 1 (ứng với mã cc)
                rows[1] = result.getString(2); // lấy dữ liệu tai cột số 2 ứng với tên cc
                rows[2] = result.getString(3);
                rows[3] = result.getString(4);
                rows[4] = result.getString(5);
                rows[5] = result.getString(6);
                tablemodel.addRow(rows); // đưa dòng dữ liệu vào tableModel để hiện thị lên jtable
                //mỗi lần có sự thay đổi dữ liệu ở tableModel thì Jtable sẽ tự động update lại trên frame
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    private Object[] getrow(NhaCCmodel mo){
        Object[] rowData={mo.getMaNhaCC(),mo.getTenNhaCC(),mo.getDiachi(),
        mo.getSDT(),mo.getMST(),mo.getGhichu()};
        return rowData;
    }
    public void deletetablerow(NhaCCmodel deleteData){
        String maToDelete = deleteData.getMaNhaCC();
        int rowIndex = -1;
        for (int i = 0; i < tablemodel.getRowCount(); i++) {
            if (maToDelete.equals(tablemodel.getValueAt(i, 0).toString())) { // Assuming ID is in the first column
                rowIndex = i;
                break;
            }
        }
        if(rowIndex !=-1){
            tablemodel.removeRow(rowIndex);
        }
    }
    public void removeTable(){
        Container container = NhaCC_tbl.getParent(); // Lấy container chứa table (JScrollPane)
        container.removeAll(); // Xóa bỏ tất cả các thành phần con
        container.revalidate(); // Cập nhật lại giao diện
        container.repaint();
    }
    private boolean checkMaNhaCC(String newmaNhaCC) {
        int rowCount = tablemodel.getRowCount();
        String lowercaseNewmaNhaCC = newmaNhaCC.toLowerCase(); // Chuyển đổi mã mới về chữ thường
        for (int i = 0; i < rowCount; i++) {
            String existingmaNhaCC = (String) tablemodel.getValueAt(i, 0); // Lấy mã thuốc từ cột 0 (chỉnh sửa nếu cần)
            if (existingmaNhaCC.equals(lowercaseNewmaNhaCC)) {
                return true; // Mã thuốc mới đã tồn tại trong bảng
            }
        }
        return false; // Mã thuốc mới không tồn tại trong bảng
    }
    private boolean checksdt(String newsdt) { 
        int rowCount1 = tablemodel.getRowCount();
        String lowercaseNewsdt = newsdt.toLowerCase(); // Chuyển đổi mã mới về chữ thường
        for (int i = 0; i < rowCount1; i++) {
            String existingsdt = (String) tablemodel.getValueAt(i, 3); // Lấy mã thuốc từ cột 0 (chỉnh sửa nếu cần)
            if (existingsdt.equals(lowercaseNewsdt)) {
                return true; 
            }
        }
        return false; // mst mới không tồn tại trong bảng
    }
    private boolean checkmasothue(String newmasothue) {       
        int rowCount = tablemodel.getRowCount();
        String lowercaseNewmasothue = newmasothue.toLowerCase(); // Chuyển đổi mã mới về chữ thường
        for (int i = 0; i < rowCount; i++) {
            String existingmasothue = (String) tablemodel.getValueAt(i, 4); // Lấy mã thuốc từ cột 0 (chỉnh sửa nếu cần)
            if (existingmasothue.equals(lowercaseNewmasothue)) {
                return true; 
            }
        }
        return false; // mst mới không tồn tại trong bảng
    }
    private void AddBtn_actionPerformed(){
        String newmanhacc= MaField.getText();
        String newtennhacc= TenField.getText();
        String newdc= DiachiField.getText();
        String newsdt= SDTField.getText();
        String newmasothue= MSTField.getText();
        if (newmanhacc.isEmpty()||newmasothue.isEmpty()||newtennhacc.isEmpty()||newdc.isEmpty()||newsdt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng không để trống thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        // Kiểm tra xem mã thuốc mới đã tồn tại trong bảng hay không
        boolean maexists = checkMaNhaCC(newmanhacc);
        boolean sdtexists = checksdt(newsdt);
        boolean mstexists = checkmasothue(newmasothue);
        if(maexists){
            JOptionPane.showMessageDialog(null, "Mã nhà cung cấp đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            MaField.requestFocus();
        }
        else if (sdtexists) {
            JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            SDTField.requestFocus();
        }
        else if (mstexists) {
            JOptionPane.showMessageDialog(null, "Mã số thuế đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            MSTField.requestFocus();
        }
        else{
            NhaCCmodel mo=new NhaCCmodel();
            getAccount(mo);

            String maNhaCC=MaField.getText();
            String tenNhaCC=TenField.getText();
            String diachi=DiachiField.getText();
            String sdt=SDTField.getText();
            String mst=MSTField.getText();
            String ghichu=GhiChuField.getText();

            tablemodel.addRow(getrow(mo));
            NhaCCctrl ctr=new NhaCCctrl();
            ctr.Add(mo);
            // xóa dữ liệu của bảng
            tablemodel.setRowCount(0);
            updateData(control.danhsach());
            ClearBtn_actionPerformed();
        }
        EditBtn.setEnabled(false);
        DeleteBtn.setEnabled(false);
    }
    private void EditBtn_actionPerformed(){
        String newsdt= SDTField.getText();
        int selectedRow= NhaCC_tbl.getSelectedRow();
        
        boolean sdtexists = checksdt(newsdt);
//        boolean mstexists = checkmasothue(newmasothue);
        NhaCCmodel mo=new NhaCCmodel();
        getAccount(mo);
        if ((!newsdt.equals(NhaCC_tbl.getValueAt(selectedRow, 3)))&&(sdtexists)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            SDTField.requestFocus();
        }
        else{
            control.Edit(mo);
            ClearBtn_actionPerformed();
            String maToUpdate = mo.getMaNhaCC();

            // Find the row index in the table model based on the ID
            int rowIndex = -1;
            for (int i = 0; i < tablemodel.getRowCount(); i++) {
                if (maToUpdate.equals(tablemodel.getValueAt(i, 0).toString())) { // Assuming ID is in the first column

                    rowIndex = i;
                    break;
                }
            }

            // Update the row if found
            if (rowIndex != -1) {
                // Update the corresponding row in the table model
                tablemodel.setValueAt(mo.getMaNhaCC(), rowIndex, 0); // Assuming ID is in the first column
                tablemodel.setValueAt(mo.getTenNhaCC(), rowIndex, 1);
                tablemodel.setValueAt(mo.getDiachi(), rowIndex, 2);
                tablemodel.setValueAt(mo.getSDT(), rowIndex, 3);
                tablemodel.setValueAt(mo.getMST(), rowIndex, 4);
                tablemodel.setValueAt(mo.getGhichu(), rowIndex, 5);
            }
        }
        
    }
    public void ClearBtn_actionPerformed(){
        MaField.setText("");
        TenField.setText("");
        DiachiField.setText("");
        SDTField.setText("");
        MSTField.setText("");
        GhiChuField.setText("");
        TimkiemField.setText("");
        tablemodel.setRowCount(0);
        updateData(control.danhsach());// hiển thị bảng
        MaField.setEditable(true);
        MSTField.setEditable(true); 
        EditBtn.setEnabled(false);
        DeleteBtn.setEnabled(false);
    }
    private void DeleteBtn_actionPerformed(){
        String newmanhacc= MaField.getText();
        NhaCCmodel mo =new NhaCCmodel();
        mo.setMaNhaCC(MaField.getText());
        NhaCCctrl ctr=new NhaCCctrl();
        if(MaField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Vui lòng không để trống thông tin!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; 
        }else if(!checkMaNhaCC(newmanhacc)){
            JOptionPane.showMessageDialog(null, "mã nhà cung cấp không tồn tại", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return; 
        }
        else{
            ctr.Delete(mo);
            deletetablerow(mo);
        }
        ClearBtn_actionPerformed();
    }
    private void TimkiemBtn_actionPerformed(){
        tablemodel.setRowCount(0); 
        // Lấy từ khóa tìm kiếm từ JTextField
        String keyword = TimkiemField.getText();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được để trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            tablemodel.setRowCount(0);
            updateData(control.danhsach());// hiển thị bảng
            MaField.setEditable(true);
            MSTField.setEditable(true); // Hiển thị toàn bộ danh sách
        }else{
            tablemodel.setRowCount(0);  // xóa dl cũ trong bảng
            control.Timkiem(tablemodel, keyword);  // Tìm kiếm dựa trên từ khóa
            // Kiểm tra số lượng hàng sau khi tìm kiếm
            int rowCount = tablemodel.getRowCount();
            if (rowCount == 0) {
                // Nếu không có kết quả, hiển thị thông báo
                JOptionPane.showMessageDialog(null, "Không có kết quả được tìm thấy.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    public void ExcelBtn_actionPerformed(){
        // Tạo Workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Tạo một trang tính mới
        XSSFSheet sheet = workbook.createSheet("Nha cung cap");

        // Lấy số lượng hàng và cột trong JTable
        int rowCount = NhaCC_tbl.getRowCount();
        int columnCount = NhaCC_tbl.getColumnCount();

        // Tạo một hàng mới để chứa tiêu đề cột
        XSSFRow headerRow = sheet.createRow(0);

        // Ghi tiêu đề cột vào hàng tiêu đề
        for (int j = 0; j < columnCount; j++) {
            String columnHeader = NhaCC_tbl.getColumnName(j); // Lấy tên cột từ JTable
            headerRow.createCell(j).setCellValue(columnHeader); // Ghi tên cột vào ô tương ứng trong hàng tiêu đề
        }

        // Ghi dữ liệu từ JTable vào các hàng tiếp theo trong trang tính
        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.createRow(i + 1); // Tạo một hàng mới (bỏ qua hàng tiêu đề)
            for (int j = 0; j < columnCount; j++) {
                Object value = NhaCC_tbl.getValueAt(i, j); // Lấy giá trị từ JTable
                if (value != null) {
                    row.createCell(j).setCellValue(value.toString()); // Ghi giá trị vào ô tương ứng trong hàng
                }
            }
        }

        // Lưu workbook vào một file
        try (FileOutputStream outputStream = new FileOutputStream("E:/file bai tap/java/table_sp.xlsx")) {
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(null, "Xuất excel thành công");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi" + e.getMessage());
        }
    }
    private static void addPlaceholder(final JTextField textField, final String placeholder) {
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
        //NhaCCview NhaCC= new NhaCCview();
        LoginView lg = new LoginView();
    }
}