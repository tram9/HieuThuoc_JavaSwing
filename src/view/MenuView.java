/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;


import model.Login;
import controller.LoginCtrl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import model.DanhMuc;

/**
 *
 * @author Hiep Phuong
 */
public class MenuView extends JFrame {
    String userRole;

    private JButton taiKhoanBtn;
    private JButton sphamBtn;
    private JButton nhanVienBtn;
    private JButton danhMucBtn;
    private JButton nhaCcBtn;
    private JButton logoutBtn;
    private JLabel background;
    
    public MenuView(String role){
        userRole = role;
        initComponents();
    }
    private void initComponents(){
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        taiKhoanBtn = new JButton("Tài Khoản");
        sphamBtn = new JButton("Sản Phẩm");
        nhanVienBtn = new JButton("Nhân Viên");
        danhMucBtn = new JButton("Danh Mục");
        nhaCcBtn = new JButton("Nhà Cung Cấp");
        logoutBtn = new JButton("Đăng xuất");
        ImageIcon back = new ImageIcon("./img/background.png");
        Image backk = back.getImage();
        Image backgr = backk.getScaledInstance(850, 450, Image.SCALE_SMOOTH);
        ImageIcon bg = new ImageIcon(backgr);
        background = new JLabel();
        background.setIcon(bg);
        background.setHorizontalAlignment(JLabel.CENTER);
        background.setVerticalAlignment(JLabel.CENTER);
        
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
        
        
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 0, 0);
        JPanel panel = new JPanel();
        panel.setSize(850,30);
        panel.setLayout(layout);
        
        panel.add(taiKhoanBtn);
        panel.add(sphamBtn);
        panel.add(nhanVienBtn);
        panel.add(danhMucBtn);
        panel.add(nhaCcBtn);
        panel.add(logoutBtn);
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        this.getContentPane().add(panel);
        this.add(separator, BorderLayout.SOUTH);
        background.setBounds(0, 0, 400, 300);
        this.add(background);
        this.setTitle("MENU");
        this.setSize(850, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        //requestFocusInWindow(Window);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null,
                        "Bạn có muốn thoát chương trình?",
                        "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION);

                if (confirmed == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }else{
                
                }
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
    }
    
    public void khoa(){
        taiKhoanBtn.setEnabled(false);
        nhanVienBtn.setEnabled(false);
    }
    public static void main(String[] args) {
       // MenuView mv = new MenuView();
        LoginView lv = new LoginView();
    }

    
}
