package View.Login;

import Controller.LoginController;
import View.Parkir.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JLabel titleLabel, unameLabel, passLabel, message, labelBack;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton submitButton;
    JCheckBox showPassword;
    ImageIcon back;
    LoginController loginController = new LoginController();
    public static String nama;
    public void setFrame(){
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Login Admin");
        component();
        ImageIcon imageIcon = new ImageIcon("src/res/parking-car.png");
        setIconImage(imageIcon.getImage());
        setVisible(true);
    }

    public void component(){
        titleLabel = new JLabel("Aplikasi Lahan Parkir");
        titleLabel.setBounds(200, 20    , 250, 30);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        unameLabel = new JLabel("Username : ");
        unameLabel.setBounds(170, 100, 150, 30);
        unameLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        usernameField = new JTextField();
        usernameField.setBounds(170, 130, 250, 30 );
        usernameField.setFont(new Font("Times New Roman", Font.BOLD, 12));

        passLabel = new JLabel("Password : ");
        passLabel.setBounds(170, 160, 150, 30);
        passLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
        passwordField = new JPasswordField();
        passwordField.setBounds(170, 190, 250, 30);
        passwordField.setFont(new Font("Times New Roman", Font.BOLD, 12));

        showPassword = new JCheckBox("Show Password");
        showPassword.setBounds(170, 220, 130, 20);
        showPassword.setFont(new Font("Times New Roman", Font.BOLD, 12));
        showPassword.addActionListener(this);

        submitButton = new JButton("Submit");
        submitButton.setBounds(320, 250, 100, 30);
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);

        message = new JLabel();
        message.setBounds(200, 290, 200, 30);
        message.setFont(new Font("Times New Roman", Font.ITALIC, 12));

        back = new ImageIcon("src/res/back5.png");
        labelBack = new JLabel(back);
        labelBack.setBounds(250, 70, 500, 300);

        add(titleLabel);
        add(unameLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(submitButton);
        add(message);
        add(showPassword);
        add(labelBack);
    }
    public static void main(String[] args) {
        Login login = new Login();
        login.setFrame();
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == submitButton){
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if(loginController.cekLogin(username, password)){
                message.setText("");
                JOptionPane.showMessageDialog(this, "Login Berhasil !");
                nama = loginController.getNama(username);
                this.setVisible(false);
                Main.main(null);
            }else{
                message.setText("Username atau Password salah !");
                message.setForeground(Color.RED);
            }
        }

        if(event.getSource() == showPassword){
            if(showPassword.isSelected()){
                passwordField.setEchoChar((char) 0);
            }else{
                passwordField.setEchoChar('*');
            }
        }
    }
}