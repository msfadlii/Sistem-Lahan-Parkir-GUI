package View.Parkir;

import Controller.ParkirController;
import View.Login.Login;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JFrame implements ActionListener {
    JLabel judulLabel, platLabel, tglLabel, menu, namaAdmin;
    JTextField platField, jamField;
    DatePicker datePicker;
    JButton submitButton, menuParkir, menuListParkir, menuListCheckout, logoutButton;
    JPanel panel;

    ParkirController parkirController = new ParkirController();
    public void setFrame(){
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Sistem Lahan Parkir");
        component();
        setVisible(true);
    }

    public void component(){
        panel = new JPanel();
        panel.setBounds(0,0, 150, 400);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        menu = new JLabel("=== Menu ===");
        menu.setBounds(35, 10, 100, 20);

        namaAdmin = new JLabel("Halo, Admin " + Login.nama);
        namaAdmin.setBounds(25, 40, 150, 20);

        //list menu
        menuParkir = new JButton("Input Parkir");
        menuParkir.setBounds(25, 80, 100, 20);
        menuParkir.setFocusPainted(false);
        menuParkir.addActionListener(this);
        menuListParkir = new JButton("List Parkir");
        menuListParkir.setBounds(25, 120, 100, 20);
        menuListParkir.setFocusPainted(false);
        menuListParkir.addActionListener(this);
        menuListCheckout = new JButton("List Checkout");
        menuListCheckout.setBounds(15, 160, 120, 20);
        menuListCheckout.setFocusPainted(false);
        menuListCheckout.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(25, 300, 100, 20);
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(this);

        panel.add(menu);
        panel.add(namaAdmin);
        panel.add(menuParkir);
        panel.add(menuListParkir);
        panel.add(menuListCheckout);
        panel.add(logoutButton);

        judulLabel = new JLabel("Input Parkir");
        judulLabel.setBounds(380, 10, 180, 25);
        platLabel = new JLabel("Plat Nomer : ");
        platLabel.setBounds(200, 50, 100, 20);
        platField = new JTextField();
        platField.setBounds(200, 70, 100, 30);

        tglLabel = new JLabel("Tanggal dan Jam : ");
        tglLabel.setBounds(200, 100, 150, 20);
        datePicker = new DatePicker();
        datePicker.setBounds(200, 120, 180, 30);
        jamField = new JTextField();
        jamField.setBounds(390, 120, 100, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 160, 100, 30);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);

        add(panel);
        add(judulLabel);
        add(platLabel);
        add(platField);
        add(tglLabel);
        add(datePicker);
        add(jamField);
        add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == menuParkir){
            this.setVisible(false);

            Main main = new Main();
            main.main(null);
        }

        if(event.getSource() == menuListParkir){
            this.setVisible(false);

            ListParkir listParkir = new ListParkir();
            listParkir.main(null);
        }

        if(event.getSource() == menuListCheckout){
            this.setVisible(false);

            ListCheckout listCheckout = new ListCheckout();
            listCheckout.main(null);
        }

        if(event.getSource() == logoutButton){
            this.setVisible(false);

            Login login = new Login();
            login.main(null);;
        }

        if(event.getSource() == submitButton){
            String plat = platField.getText();
            String tgl = datePicker.getDateStringOrEmptyString();
            String jam = jamField.getText();

            boolean cek = parkirController.insert(plat, tgl, jam, Login.nama);
            if(cek){
                JOptionPane.showMessageDialog(this, "Plat sudah ada, input plat lain!");
            }else{
                JOptionPane.showMessageDialog(this, "Plat berhasil ditambahkan!");
                platField.setText("");
                datePicker.clear();
                jamField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setFrame();
    }
}