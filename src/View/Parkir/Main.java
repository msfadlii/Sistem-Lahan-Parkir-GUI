package View.Parkir;

import Controller.ParkirController;
import View.Login.Login;
import com.github.lgooddatepicker.components.DateTimePicker;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    JLabel judulLabel, platLabel, tglLabel, menu, namaAdmin;
    JTextField platField;
    DateTimePicker dateTimePicker;
    JButton submitButton, menuParkir, menuListAdmin, menuListParkir, logoutButton;
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
        menuListParkir = new JButton("List Parkir");
        menuListParkir.setBounds(25, 120, 100, 20);
        menuListAdmin = new JButton("List Admin");
        menuListAdmin.setBounds(25, 160, 100 ,20);
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(25, 300, 100, 20);

        panel.add(menu);
        panel.add(namaAdmin);
        panel.add(menuParkir);
        panel.add(menuListParkir);
        panel.add(menuListAdmin);
        panel.add(logoutButton);

        judulLabel = new JLabel("Input Parkir");
        judulLabel.setBounds(380, 10, 180, 25);
        platLabel = new JLabel("Plat Nomer : ");
        platLabel.setBounds(200, 50, 100, 20);
        platField = new JTextField();
        platField.setBounds(200, 70, 100, 30);

        tglLabel = new JLabel("Tanggal dan Jam : ");
        tglLabel.setBounds(200, 100, 100, 20);
        dateTimePicker = new DateTimePicker();
        dateTimePicker.setBounds(200, 120, 250, 30);

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 160, 100, 30);

        submitButton.addActionListener((event->{
            String plat = platField.getText();
            String tgl = dateTimePicker.getDatePicker().getDateStringOrEmptyString().formatted();
            String jam = dateTimePicker.getTimePicker().getTimeStringOrEmptyString();
            String date =  tgl + " " +jam;

            System.out.println(date);

            boolean cek = parkirController.insert(plat, date, Login.nama);
            if(cek){
                JOptionPane.showMessageDialog(this, "Plat sudah ada, input plat lain!");
            }else{
                JOptionPane.showMessageDialog(this, "Plat berhasil ditambahkan!");
                platField.setText("");
                dateTimePicker.clear();
            }
        }));
        add(panel);
        add(judulLabel);
        add(platLabel);
        add(platField);
        add(tglLabel);
        add(dateTimePicker);
        add(submitButton);
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setFrame();
    }
}