package View.Parkir;

import Controller.ParkirController;
import View.Login.Login;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends JFrame implements ActionListener {
    JLabel judulLabel, platLabel, tglLabel, namaAdmin, image, jam;
    ImageIcon imageIcon, backgroundImage;
    Image background;
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
        ImageIcon imageIcon = new ImageIcon("src/res/parking-car.png");
        setIconImage(imageIcon.getImage());
        setVisible(true);
    }

    public void component(){
        panel = new JPanel();
        panel.setBounds(0,0, 150, 400);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        imageIcon = new ImageIcon("src/res/parking-area.png");
        image = new JLabel(imageIcon);
        image.setBounds(45,10, 64, 64);

        namaAdmin = new JLabel("Halo, Admin " + Login.nama);
        namaAdmin.setBounds(25, 78, 150, 20);
        namaAdmin.setFont(new Font("Times New Roman", Font.BOLD, 12));

        //list menu
        menuParkir = new JButton("Input Parkir");
        menuParkir.setBounds(25, 120, 100, 20);
        menuParkir.setFont(new Font("Times New Roman", Font.BOLD, 12));
        menuParkir.setFocusPainted(false);
        menuParkir.addActionListener(this);
        menuListParkir = new JButton("List Parkir");
        menuListParkir.setBounds(25, 150, 100, 20);
        menuListParkir.setFont(new Font("Times New Roman", Font.BOLD, 12));
        menuListParkir.setFocusPainted(false);
        menuListParkir.addActionListener(this);
        menuListCheckout = new JButton("List Checkout");
        menuListCheckout.setBounds(15, 180, 120, 20);
        menuListCheckout.setFont(new Font("Times New Roman", Font.BOLD, 12));
        menuListCheckout.setFocusPainted(false);
        menuListCheckout.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(25, 300, 100, 20);
        logoutButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        logoutButton.setFocusPainted(false);
        logoutButton.addActionListener(this);

        panel.add(image);
        panel.add(namaAdmin);
        panel.add(menuParkir);
        panel.add(menuListParkir);
        panel.add(menuListCheckout);
        panel.add(logoutButton);

        //Realtime Jam
        jam = new JLabel();
        jam.setBounds(720, 0, 300, 20);
        jam.setFont(new Font("Times New Roman", Font.BOLD, 14));
        Timer timer = new Timer(1000, this);
        timer.start();
        updateWaktu();

        judulLabel = new JLabel("Input Parkir");
        judulLabel.setBounds(380, 10, 180, 25);
        judulLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        platLabel = new JLabel("Plat Nomer : ");
        platLabel.setBounds(200, 50, 100, 20);
        platLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        platField = new JTextField();
        platField.setBounds(200, 70, 100, 30);
        platField.setFont(new Font("Times New Roman", Font.BOLD, 12));

        tglLabel = new JLabel("Tanggal dan Jam : ");
        tglLabel.setBounds(200, 100, 150, 20);
        tglLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        datePicker = new DatePicker();
        datePicker.setBounds(200, 120, 180, 30);
        datePicker.setFont(new Font("Times New Roman", Font.BOLD, 12));
        jamField = new JTextField();
        jamField.setBounds(390, 120, 100, 30);
        jamField.setFont(new Font("Times New Roman", Font.BOLD, 12));

        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 160, 100, 30);
        submitButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(this);

        ImageIcon back = new ImageIcon("src/res/back1.png");
        JLabel labelBack = new JLabel(back);
        labelBack.setBounds(220, 100, 600, 300);

        add(panel);
        add(jam);
        add(judulLabel);
        add(platLabel);
        add(platField);
        add(tglLabel);
        add(datePicker);
        add(jamField);
        add(submitButton);
        add(labelBack);
    }

    private void updateWaktu(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(new Date());
        jam.setText(formattedTime);
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

            if(plat.isEmpty() || tgl.isEmpty() || jam.isEmpty()){
                JOptionPane.showMessageDialog(this, "Lengkapi Input terlebih dahulu!");
            }else{
                if(parkirController.cekJam(jam)){
                    boolean cek = parkirController.insert(plat, tgl, jam, Login.nama);
                    if(cek){
                        JOptionPane.showMessageDialog(this, "Plat sudah ada, input plat lain!");
                    }else{
                        JOptionPane.showMessageDialog(this, "Plat berhasil ditambahkan!");
                        platField.setText("");
                        datePicker.clear();
                        jamField.setText("");
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Masukkan Format Jam yang benar. Contoh: 00:00");
                }
            }
        }
        updateWaktu();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setFrame();
    }
}