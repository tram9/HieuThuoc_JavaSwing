/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.LoginCtrl;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import model.Login;

/**
 *
 * @author Hiep Phuong
 */
public class LoginView extends JFrame{
    private int allow;
    private int max_allow = 3;
    
    private JLabel titleLable;
    private JLabel emailLable;
    private JLabel passLable;
    private JLabel showIcon;
    
    private JTextField emailField;
    private JPasswordField passField;
    
    private JButton loginBtn;
    
    public LoginView(){
        initComponents();
    }
    
    public void initComponents(){
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        titleLable = new JLabel("LOGIN");
        emailLable = new JLabel("Email");
        passLable = new JLabel("Password");
        
        ImageIcon show = new ImageIcon("./img/eye.png");
        Image showIma = show.getImage();
        Image showImage = showIma.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        ImageIcon showI = new ImageIcon(showImage);
        showIcon = new JLabel();
        showIcon.setIcon(showI);
        
        loginBtn = new JButton("Login");
        
        emailField = new JTextField(20);
        passField = new JPasswordField(17);
        passField.setEchoChar('\u2022');
        
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(400, 150);
        panel.setLayout(layout);
        
        panel.add(loginBtn);
        panel.add(titleLable);
        panel.add(emailLable);
        panel.add(passLable);
        panel.add(showIcon);
        panel.add(emailField);
        panel.add(passField);
        
        layout.putConstraint(SpringLayout.WEST, titleLable, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleLable, 20, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, emailLable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailLable, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passLable, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passLable, 80, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, emailField, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, emailField, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passField, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passField, 80, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.EAST, showIcon, -80, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, showIcon, 80, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, loginBtn, 270, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, loginBtn, 120, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.setTitle("LOGIN");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginBtn_actionPerformed();
            }
        });
        
        passField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginBtn_actionPerformed();
                }
            }
        });
        
        showIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                passField.setEchoChar((char) 0); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                passField.setEchoChar('\u2022'); 
            }
        });
        
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
        
    }
    
    public void loginBtn_actionPerformed(){ 
        if(emailField.getText().equals("")||passField.getPassword().equals("")){
            JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin");
            if(emailField.getText().equals("")){
                emailField.requestFocus();
            }else if(passField.getPassword().equals("")){
                passField.requestFocus();
            }
        }else{
            Login lg = new Login();
            lg.setEmail(emailField.getText());
            String password = new String(passField.getPassword());
            lg.setPass(password);
            LoginCtrl lc = new LoginCtrl();
            if(lc.logined(lg) == true){
                allow = 0;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        dispose();
                        MenuView mv = new MenuView(lc.role(lg));
                        if(lc.admin(lg)){
                            mv.setVisible(true);
                        }else{
                            mv.khoa();
                        }
                    }
                });
            }else{
                allow++;
                if(allow > max_allow){
                    loginBtn.setEnabled(false);
                    JOptionPane.showMessageDialog(null, "Sai mật khẩu quá 3 lần! Thử lại sau!");
                }else{
                }
            }
            
        }
    }
    public Login getAccount(){
        String password = new String(passField.getPassword());
        Login t= new Login(emailField.getText(),password);
        return t;
    }
    
    public static void main(String[] args) {
        LoginView lv = new LoginView();
    }
}
