
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;


import com.toedter.calendar.JDateChooser;
import controller.ControlThuoc;
import controller.LoginCtrl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import model.Login;
import model.ModelThuoc;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Phuong Thao
 */
public class FormThuoc extends JFrame{ 
    
    // Khai bao nut
    private JButton taiKhoanBtn;
    private JButton sphamBtn;
    private JButton nhanVienBtn;
    private JButton danhMucBtn;
    private JButton nhaCcBtn;
    private JButton logoutBtn;
    
    JPanel controlPanel, menu,contentPanel;
    JTextField txtMaThuoc, txtTenThuoc, txtDonGia, txtDonVi, txtSoLuong;
    JTextField txtNhapTim;
    
    JDateChooser dateNSX, dateHSD;
    JLabel titleLabel;
    public JComboBox cboTenNCC, cboTenDM;
    JComboBox cboTimKiem;
    JButton btnAdd, btnEdit, btnDelete, btnClear, btnXuatExcel;
    JButton btnTimKiem;
    static int row = 0;
    public Object o;
    JTable jtable;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;
    JLabel lbHien;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public FormThuoc (String userRole){
        
        prepare(userRole);
        ControlThuoc ctrl = new ControlThuoc();
        hienthi_tbl(ctrl);
    }
    public void prepare(String userRole){
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 
        this.setSize(850, 600);
        this.setTitle("Quản lý sản phẩm");

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
        
        if(!userRole.equals("admin")){
            taiKhoanBtn.setEnabled(false);
            nhanVienBtn.setEnabled(false);
        }
       
        /////////////////////////////////

        txtMaThuoc= new JTextField();        
        txtTenThuoc= new JTextField();
        txtDonVi= new JTextField();
        txtDonGia=new JTextField();
        txtSoLuong=new JTextField();
        txtNhapTim= new JTextField();
        cboTenDM= new JComboBox();
        cboTenNCC = new JComboBox();
        cboTimKiem= new JComboBox(new String[]{"Mã Thuốc","Số Lượng", "Tên NCC","HSD"});
       
        
        dateNSX= new JDateChooser();
        dateHSD= new JDateChooser();
        dateNSX.setDateFormatString("dd/MM/yyyy");
        dateHSD.setDateFormatString("dd/MM/yyyy");
        
        btnAdd=new JButton("Add");
        btnEdit=new JButton("Edit");
        btnDelete=new JButton("Delete");
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
        btnClear=new JButton("Clear");
        btnTimKiem= new JButton("Tìm Kiếm");
        btnXuatExcel= new JButton("Xuất Excel");
        
        String column[]={"Mã Thuốc", "Tên Thuốc", "Đơn vị", "Đơn Giá","Tên NCC","Tên DM","Số Lượng","NSX","HSD"};
        tableModel= new DefaultTableModel(column, 0);  //column là một mảng chứa các tên cột của bảng. 
        jtable=new JTable(tableModel); 
        jtable.setPreferredScrollableViewportSize(new Dimension(800,150)); 
        
        scrollPane = new JScrollPane(jtable); //Tạo JScrollPane chứa JTable và tự động thêm thanh cuộn 
        
        //----------MainFrame---------
        menu = new JPanel();
        menu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        contentPanel = new JPanel(new BorderLayout());
        
        // Thêm vào main frame
        add(menu, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER); 
        
        //------------Menu--------------
        menu.setPreferredSize(new Dimension(850,30));
        menu.add(taiKhoanBtn);
        menu.add(sphamBtn);
        menu.add(nhanVienBtn);
        menu.add(danhMucBtn);
        menu.add(nhaCcBtn);
        menu.add(logoutBtn);
        
        //------------ContentPanel---------
        contentPanel.setPreferredSize(new Dimension(850,30));
        
        controlPanel= new JPanel();
        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setPreferredSize(new Dimension(800,30));

        titleLabel = new JLabel("Quản lý sản phẩm");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Aria",Font.BOLD,24));
        // Tạo contentPanel chứa titleLabel và controlPanel
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(controlPanel, BorderLayout.CENTER);
       
            //-----------ControlPanel--------------
        GridBagConstraints gbc= new GridBagConstraints();

        
        // 1. panel tìm kiếm
        JPanel panelTim  = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 10, 5,10);  // tạo k/c xung quanh panelTim
        gbc.fill = GridBagConstraints.BOTH;
        
        panelTim.setLayout(new GridBagLayout());
        panelTim.setBorder(BorderFactory.createEtchedBorder());
        controlPanel.add(panelTim, gbc);
        
        //các thành phần trong paneltim
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5,10); // tạo k/c xung quanh các t/p trong panel
        gbc.anchor = GridBagConstraints.WEST; //căn chỉnh về bên trái(tây)
      
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelTim.add(new JLabel("Chọn tìm kiếm theo"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        cboTimKiem.setPreferredSize(new Dimension(100,20));
        panelTim.add(cboTimKiem, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelTim.add(new JLabel("Nhập nội dung"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtNhapTim.setColumns(12);
        panelTim.add(txtNhapTim, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        panelTim.add(btnTimKiem,gbc);
        
        //2. panel nhập 
        JPanel panel= new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.setLayout(new GridBagLayout());
        panel.setPreferredSize(new Dimension(800,160));
        panel.setBorder(BorderFactory.createEtchedBorder());
        controlPanel.add(panel, gbc);
        
        // các thành phần
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // x tăng, y const;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Mã Thuốc"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        txtMaThuoc.setColumns(10);
        panel.add(txtMaThuoc, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(new JLabel("Tên Thuốc"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        txtTenThuoc.setPreferredSize(new Dimension(120,20));
        panel.add(txtTenThuoc, gbc);    
        
        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(new JLabel("Đơn Vị"), gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        txtDonVi.setPreferredSize(new Dimension(120,20));
        panel.add(txtDonVi, gbc);   
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Đơn Giá"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        txtDonGia.setColumns(10);
        panel.add(txtDonGia, gbc);  
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        panel.add(new JLabel("Tên NCC"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        cboTenNCC.setPreferredSize(new Dimension(120, 20));
        panel.add(cboTenNCC, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 1;
        panel.add(new JLabel("Tên DM"), gbc);
        gbc.gridx = 5;
        gbc.gridy = 1;
        cboTenDM.setPreferredSize(new Dimension(120, 20));
        panel.add(cboTenDM, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Số Lượng"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        txtSoLuong.setColumns(10);
        panel.add(txtSoLuong, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(new JLabel("NSX"), gbc);
        gbc.gridx = 3;
        gbc.gridy = 2;
        dateNSX.setPreferredSize(new Dimension(120,20));
        panel.add(dateNSX, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 2;
        panel.add(new JLabel("HSD"), gbc);
        gbc.gridx = 5;
        gbc.gridy = 2;
        dateHSD.setPreferredSize(new Dimension(120,20));
        panel.add(dateHSD, gbc);
        
        //2.2. Panel for Buttons
        JPanel buttonPanel = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 10; 
        gbc.anchor= GridBagConstraints.CENTER;
        panel.add(buttonPanel, gbc);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,10)); //k/c ngang giữa các thành phần là 20, kc dọc là 10
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnXuatExcel);
        
        // label hiện số dòng
        gbc.gridx = 0;
        gbc.gridy = 2; 
        gbc.anchor = GridBagConstraints.WEST;
        lbHien= new JLabel();
        controlPanel.add(lbHien, gbc);

        //3. panel table 
        JPanel Paneltable = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 3;
        controlPanel.add(Paneltable, gbc);
        Paneltable.setLayout(new BorderLayout());
        Paneltable.add(scrollPane, BorderLayout.CENTER); // add cuộn , bảng center
        
        
        this.setLocationRelativeTo(null);  // hiển thị chính giữa màn hình
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
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
        
        // hiển thị dữ liệu từ bảng lên ô text
        jtable.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
                row = jtable.getSelectedRow(); 
                
                // lay du lieu tu ô được chọn
                txtMaThuoc.setText(jtable.getValueAt(row, 0).toString());
                txtTenThuoc.setText(jtable.getValueAt(row, 1).toString());
                txtDonVi.setText(jtable.getValueAt(row, 2).toString());
                txtDonGia.setText(jtable.getValueAt(row, 3).toString());

                Object valuemaNCC = jtable.getValueAt(row, 4); 
               // Đặt giá trị mặc định cho JComboBox bằng giá trị từ hàng được chọn
                cboTenNCC.setSelectedItem(valuemaNCC);
                cboTenDM.setSelectedItem(jtable.getValueAt(row, 5));
        
                txtSoLuong.setText(jtable.getValueAt(row, 6).toString());
                try {
                    //chuyển đổi thành một đối tượng Date( dd/mm/yyyy)
                    dateNSX.setDate(dateFormat.parse(jtable.getValueAt(row, 7).toString()) );
                    dateHSD.setDate(dateFormat.parse(jtable.getValueAt(row, 8).toString()));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
               
                txtMaThuoc.setEditable(false);
                btnDelete.setEnabled(true);
                btnEdit.setEnabled(true);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        btnAdd.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String newMaThuoc= txtMaThuoc.getText();
                Date NSX= dateNSX.getDate();        
                Date HSD= dateHSD.getDate();
                boolean textnull= checktextnull();
                if(!textnull){
                    // Kiểm tra mã thuốc mới
                    boolean existMa = checkMaThuocExists(newMaThuoc);                    
                    boolean checkDate = checkNSX_HSD(NSX, HSD); // ktra ngày
                    if(!existMa){
                        if(!checkDate){
                            btn_add_actionperformed();
                        }else{
                          JOptionPane.showMessageDialog(null, "Ngày SX không thể lớn hớn HSD", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        }
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Mã thuốc đã tồn tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        txtMaThuoc.requestFocus();
                    }
                }else{
                  JOptionPane.showMessageDialog(null, "Bạn đang để trống dữ liệu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        }); 
        btnEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Date NSX= dateNSX.getDate();        
                Date HSD= dateHSD.getDate();
                boolean textnull= checktextnull();  
                boolean checkDate = checkNSX_HSD(NSX, HSD);
                if(!textnull){  //nếu false
                    
                    if(!checkDate){
                        btn_edit_actionperformed();
                    }else{
                        JOptionPane.showMessageDialog(null, "Ngày SX không thể lớn hớn HSD", "Thông báo", JOptionPane.WARNING_MESSAGE);
                     }
                }else{
                      JOptionPane.showMessageDialog(null, "Bạn đang để trống dữ liệu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                }
        });
        btnClear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_clear_actionperformed();
                tableModel.setRowCount(0); // đặt số lượng hàng:0
                ControlThuoc ctrl = new ControlThuoc();
                hienthi_tbl(ctrl);
            }
        });
       
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModelThuoc model= new ModelThuoc();
                model.setMaThuoc(txtMaThuoc.getText());
                int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    ControlThuoc ctrl = new ControlThuoc();
                    ctrl.delete_thuoc(model);

                    tableModel.removeRow(row);  // xóa 1 hàng row
                    tableModel.fireTableDataChanged(); //dữ liệu đã thay đổi, JTable sẽ tự động cập nhật giao diện 
                    btn_clear_actionperformed();
                    int rowCount= tableModel.getRowCount(); 
                    lbHien.setText("Có "+ rowCount + " sản phẩm");
                 }
             }
            });
        btnTimKiem.addActionListener(new ActionListener(){
             @Override
            public void actionPerformed(ActionEvent e) {
                 try {
                     btn_tim_actionperformed();
                 } catch (ParseException ex) {
                     Logger.getLogger(FormThuoc.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
        });
        btnXuatExcel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_xuat_actionperformed();
            }
        });
        txtSoLuong.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar(); // lấy kí tự người dùng vừa nhập
                // không phải là số...
                if (!Character.isDigit(c) || c== KeyEvent.VK_BACK_SPACE || c== KeyEvent.VK_DELETE) {
                    e.consume();// ko cho phép nhập ký tự // ngăn người dùng nhập kí tự vào
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        txtDonGia.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || c== KeyEvent.VK_BACK_SPACE || c== KeyEvent.VK_DELETE) {
                    e.consume();
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {} // được nhấn
            @Override
            public void keyReleased(KeyEvent e) {} // được thả ra
        });
        cboTimKiem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy phần tử đã chọn từ combobox
                String selectedItem = (String) cboTimKiem.getSelectedItem();

                if(selectedItem.equals("HSD")) {
                    addPlaceholder(txtNhapTim, "nhap dd/mm/yyyy"); // tạo văn bản /gợi ý người dùng nhập dl
                }
                else if(selectedItem.equals("Số Lượng")){
                    // Xóa các KeyListener cũ (ngày)
                    for (KeyListener listener : txtNhapTim.getKeyListeners()) {
                        txtNhapTim.removeKeyListener(listener);
                    }
                    txtNhapTim.addKeyListener(new KeyAdapter(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!Character.isDigit(c) || c== KeyEvent.VK_BACK_SPACE || c== KeyEvent.VK_DELETE) {
                            e.consume();// ko cho phép nhập ký tự
                        }
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {}
                    @Override
                    public void keyReleased(KeyEvent e) {}
                    });
                }
                else {
                    txtNhapTim.setText("");
                    for (KeyListener listener : txtNhapTim.getKeyListeners()) {
                        txtNhapTim.removeKeyListener(listener);
                    }
                }
            }
            
        });
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
    public boolean checktextnull(){
        return txtMaThuoc.getText().isEmpty() || txtMaThuoc.getText().contains(" ")
                ||txtTenThuoc.getText().isEmpty() || txtDonVi.getText().isEmpty()|| txtDonGia.getText().isEmpty()|| txtSoLuong.getText().isEmpty()||dateNSX.getDate()==null|| dateHSD.getDate()==null;
    // method này trả về true nếu có ít nhất một trường trống
    }
    // kiem tra trùng mã
    private boolean checkMaThuocExists(String newMaThuoc) {
        int rowCount = tableModel.getRowCount();
        String lowerNewMaThuoc = newMaThuoc.toLowerCase(); // Chuyển đổi mã mới về chữ thường
        for (int i = 0; i < rowCount; i++) {
            String existingMaThuoc = (String) tableModel.getValueAt(i, 0); // Lấy từ cột 0 
            if (existingMaThuoc.equals(lowerNewMaThuoc)) {
                return true; // Mã thuốc mới đã tồn tại trong bảng
            }
        }
        return false; 
    }
     public boolean checkNSX_HSD(Date nsxDate, Date hsdDate) {
        return nsxDate.compareTo(hsdDate)>=0;   // nsx sau hsd:  là giá trị >0
    }
    public void btn_add_actionperformed() {
       
        ModelThuoc model= new ModelThuoc();
        String maThuoc= txtMaThuoc.getText();
        String tenThuoc= txtTenThuoc.getText(); 
        String donVi= txtDonVi.getText(); 
        int donGia= Integer.parseInt(txtDonGia.getText());
        String tenNCC = cboTenNCC.getSelectedItem().toString();
        String tenDM = cboTenDM.getSelectedItem().toString();
        int soLuong= Integer.parseInt(txtSoLuong.getText());
        Date NSX= dateNSX.getDate();        
        Date HSD= dateHSD.getDate();
        String forDateNSX = dateFormat.format(NSX);
        String forDateHSD = dateFormat.format(HSD);
        // lấy dl từ text add vào bảng
        Object[] rowData = {maThuoc, tenThuoc, donVi, donGia, tenNCC, tenDM, soLuong,forDateNSX, forDateHSD};
        tableModel.addRow(rowData);
        
        int rowCount= tableModel.getRowCount();
        lbHien.setText("Có "+ rowCount + " sản phẩm");
        
        setModel(model);  // gọi hàm tạo set các thuộc tính
        ControlThuoc ctr = new ControlThuoc();
        ctr.add_thuoc(model, FormThuoc.this);
        //  một tham chiếu đến đối tượng FormThuoc nơi mà dữ liệu sẽ được hiển thị
    }
    public void btn_edit_actionperformed(){
        
        ModelThuoc model= new ModelThuoc();
        setModel(model); // gọi hàm tạo các set thuộc tính
        
        ControlThuoc ctr = new ControlThuoc();
        ctr.edit_thuoc(model, FormThuoc.this);
        
        String maUpdate = model.getMaThuoc();
        int rowIndex = -1; // khởi tạo rowIndex
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (maUpdate.equals(tableModel.getValueAt(i, 0).toString())) { 
                rowIndex = i; // tìm row
                break;
            }
        }
        if (rowIndex != -1) {
            // Update row in the table model
            tableModel.setValueAt(model.getMaThuoc(), rowIndex, 0); 
            tableModel.setValueAt(model.getTenThuoc(), rowIndex, 1);
            tableModel.setValueAt(model.getDonVi(), rowIndex, 2);
            tableModel.setValueAt(model.getDonGia(), rowIndex, 3);
            tableModel.setValueAt(cboTenNCC.getSelectedItem(), rowIndex, 4);               
            tableModel.setValueAt(cboTenDM.getSelectedItem(), rowIndex, 5);
            tableModel.setValueAt(model.getSoLuong(), rowIndex, 6);
            tableModel.setValueAt(dateFormat.format(dateNSX.getDate()), rowIndex, 7);
            tableModel.setValueAt(dateFormat.format(dateHSD.getDate()), rowIndex, 8);
        }
    }
    public void setModel(ModelThuoc model){
        model.setMaThuoc(txtMaThuoc.getText());
        model.setTenThuoc(txtTenThuoc.getText());
        model.setDonVi(txtDonVi.getText());
        model.setDonGia(Integer.parseInt(txtDonGia.getText()));
        model.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        model.setDateNSX(dateNSX.getDate());
        model.setDateHSD(dateHSD.getDate());
        // ko set combo
    }
    public void btn_tim_actionperformed() throws ParseException {
        
        ControlThuoc ctr= new ControlThuoc();
        // Lấy lựa chọn từ JComboBox
        String selectedOption = cboTimKiem.getSelectedItem().toString();
        String keyword = txtNhapTim.getText();
        if(keyword.isEmpty()){
             JOptionPane.showMessageDialog(null, "Bạn đang để trống!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        else{
            
            if (selectedOption.equals("HSD")) {
            keyword = txtNhapTim.getText();
            dateFormat.setLenient(false); // Không cho phép chuyển đổi ngày không hợp lệ

                try {
                    Date date = dateFormat.parse(keyword);
                    // System.out.println("Ngày hợp lệ: " + date);
                    DateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String formattedDate = outputDateFormat.format(date);
                    
                    tableModel.setRowCount(0);  // xóa dl cũ trong bảng
                    ctr.search_thuoc(tableModel, selectedOption, formattedDate);
                    int rowCount= tableModel.getRowCount();
                    lbHien.setText("Có "+ rowCount + " sản phẩm");

                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    txtNhapTim.setText("");
                }
            }
            else{
            tableModel.setRowCount(0);  
            ctr.search_thuoc(tableModel, selectedOption, keyword);
            int rowCount= tableModel.getRowCount();
            lbHien.setText("Có "+ rowCount + " sản phẩm");
            }
        }
    }
    
    public void btn_clear_actionperformed(){
        txtMaThuoc.setText("");
        txtTenThuoc.setText("");
        txtDonVi.setText("");
        txtDonGia.setText("");
        txtSoLuong.setText("");
        ControlThuoc ctrl = new ControlThuoc();
        ctrl.loadDataToComboBox(FormThuoc.this);  //để giá trị combobox
        dateNSX.setDate(null);
        dateHSD.setDate(null);
        txtNhapTim.setText("");
        txtMaThuoc.setEditable(true);
        btnDelete.setEnabled(false);
        btnEdit.setEnabled(false);
    }
    // đưa dữ liệu csdl lên bảng
     public void hienthi_tbl(ControlThuoc ctrl) {
        ctrl.rsTableModel(jtable, tableModel);   //csdl lên bảng
        ctrl.loadDataToComboBox(this);   // csdl lên combobox
        
        int rowCount= tableModel.getRowCount();
        lbHien.setText("Có "+ rowCount + " sản phẩm");
    }
     
   // Biến đếm cho tên tệp Excel
     int fileCount = 1;
     public void btn_xuat_actionperformed() {
         // Tạo workbook mới
        XSSFWorkbook workbook = new XSSFWorkbook();
        
        XSSFSheet sheet = workbook.createSheet("Sản phẩm " + fileCount);
        // Lấy số lượng hàng và cột trong JTable
        int rowCount = jtable.getRowCount();
        int columnCount = jtable.getColumnCount();
        
        XSSFRow headerRow = sheet.createRow(0);// Tạo một hàng mới để chứa tiêu đề cột
        // Ghi tiêu đề cột vào hàng tiêu đề
        for (int j = 0; j < columnCount; j++) {
            String columnHeader = jtable.getColumnName(j);
            // Tạo một ô mới cho tên cột
            XSSFCell cell = headerRow.createCell(j);
            // in đậm cho ô
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            XSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            cell.setCellStyle(style);
            // Ghi tên cột vào ô tương ứng trong hàng tiêu đề
            cell.setCellValue(columnHeader);
        }
        int columnWidth = 3000; // Độ rộng cho tất cả các cột 
        for (int j = 0; j < columnCount; j++) {
            sheet.setColumnWidth(j, columnWidth);
        }
        // Ghi dữ liệu từ JTable vào các hàng tiếp theo trong trang tính
        for (int i = 0; i < rowCount; i++) {
            XSSFRow row = sheet.createRow(i + 1); // Tạo một hàng mới (bỏ qua hàng tiêu đề)
            for (int j = 0; j < columnCount; j++) {
                Object value = jtable.getValueAt(i, j); // Lấy giá trị từ JTable
                if (value != null) {
                    row.createCell(j).setCellValue(value.toString()); // Ghi giá trị vào ô tương ứng trong hàng
                }
            }
        }
        try (FileOutputStream fileOut = new FileOutputStream("sanpham_" + fileCount + ".xlsx")) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Tạo tệp Excel thành công: sanpham_" + fileCount + ".xlsx");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tạo tệp Excel.");
        }
        fileCount++;
    }
     
    public static void main(String[] args) {
        LoginView lv = new LoginView();
    }
     
}


