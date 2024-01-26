package View.Parkir;

import Controller.CheckoutController;
import Controller.ParkirController;
import Entity.Parkir;
import View.Login.Login;
import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListParkir extends JFrame implements ActionListener {
    JLabel namaAdmin, pilihLabel, tglLabel, checkoutLabel, image, jamLabel;
    ImageIcon imageIcon;
    JButton menuParkir, menuListParkir, menuListCheckout, logoutButton;
    DatePicker datePicker;
    JPanel panel;
    JTextField pilihField, jamField;
    ParkirController parkirController = new ParkirController();
    CheckoutController checkoutController = new CheckoutController();
    static JTable jTable;
    JScrollPane scrollPane;
    JButton btnDelete, btnCheckout;
    ArrayList<Parkir> parkirArrayList = parkirController.getListParkir();
    static String tgl, jam;
    public void setFrame(){
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("List Parkir");
        component();
        ImageIcon imageIcon = new ImageIcon("src/res/parking-car.png");
        setIconImage(imageIcon.getImage());
        setVisible(true);
    }

    public void component(){
        panel = new JPanel();
        panel.setBounds(0,0, 150, 500);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        imageIcon = new ImageIcon("src/res/parking-area.png");
        image = new JLabel(imageIcon);
        image.setBounds(45,10, 64, 64);

        namaAdmin = new JLabel("Halo, Admin " + Login.nama);
        namaAdmin.setBounds(25, 78, 150, 20);
        namaAdmin.setFont(new Font("Times New Roman", Font.BOLD, 12));

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
        jamLabel = new JLabel();
        jamLabel.setBounds(720, 0, 300, 20);
        jamLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        Timer timer = new Timer(1000, this);
        timer.start();
        updateWaktu();

        JLabel listLabel = new JLabel("List Parkir");
        listLabel.setBounds(380, 10, 100, 25);
        listLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        ParkirTableModel parkirTableModel = new ParkirTableModel(parkirArrayList);
        jTable = new JTable(parkirTableModel);
        jTable.setAutoCreateRowSorter(true);
        jTable.setRowHeight(40);

        //buat kolom nomor jadi tengah
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        jTable.getColumnModel().getColumn(0).setMaxWidth(50);

        scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(200, 40, 500, 230);
        //mengilangkan border
        scrollPane.setBorder(new LineBorder(new Color(0, 0, 0, 0)));
//        scrollPane.revalidate();
//        scrollPane.repaint();

        JTableHeader header = jTable.getTableHeader();
        header.setBackground(Color.gray);
        header.setForeground(Color.white);
        header.setFont(new Font("Times New Roman", Font.BOLD, 12));
        scrollPane.setColumnHeaderView(header);
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        //untuk memilih salah satu plat
        checkoutLabel = new JLabel("Checkout atau Delete Plat Nomor");
        checkoutLabel.setBounds(200, 320, 220, 20);
        checkoutLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pilihLabel = new JLabel("Pilih Plat Nomor : ");
        pilihLabel.setBounds(200, 340, 120, 20);
        pilihLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        pilihField = new JTextField();
        pilihField.setBounds(200, 360, 150, 30);
        pilihField.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(390, 360, 100, 20);
        btnCheckout.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnCheckout.setFocusPainted(false);
        btnCheckout.addActionListener(this);
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(500, 360, 100, 20);
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
        btnDelete.setFocusPainted(false);
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(this);

        tglLabel = new JLabel("Tanggal dan Jam : ");
        tglLabel.setBounds(200, 390, 180, 20);
        tglLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        datePicker = new DatePicker();
        datePicker.setBounds(200, 410, 200, 30);
        datePicker.setFont(new Font("Times New Roman", Font.BOLD, 12));
        jamField = new JTextField();
        jamField.setBounds(410, 410, 100, 30);
        jamField.setFont(new Font("Times New Roman", Font.BOLD, 12));

        ImageIcon back = new ImageIcon("src/res/back2.png");
        JLabel labelBack = new JLabel(back);
        labelBack.setBounds(300, 160, 600, 300);

        add(panel);
        add(jamLabel);
        add(scrollPane);
        add(listLabel);
        add(checkoutLabel);
        add(pilihLabel);
        add(pilihField);
        add(btnDelete);
        add(btnCheckout);
        add(tglLabel);
        add(datePicker);
        add(jamField);
        add(labelBack);
    }

    private void updateWaktu(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedTime = dateFormat.format(new Date());
        jamLabel.setText(formattedTime);
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

        if (event.getSource() == btnDelete){
            String data = pilihField.getText();
            if(data.isEmpty()){
                JOptionPane.showMessageDialog(this, "Lengkapi input terlebih dahulu!");
            }else{
                parkirController.delete(data);
            }
        }

        if(event.getSource() == btnCheckout){
            String data = pilihField.getText();
            tgl = datePicker.getDateStringOrEmptyString();
            jam = jamField.getText();
            if(data.isEmpty() || tgl.isEmpty() || jam.isEmpty()){
                JOptionPane.showMessageDialog(this, "Lengkapi input terlebih dahulu!");
            }else{
                if(parkirController.cekTanggal(data, tgl)){
                    if(parkirController.cekJam(jam)){
                        boolean cek = checkoutController.checkout(data, tgl, jam, Login.nama);
                        if(cek){
                            JOptionPane.showMessageDialog(this, "Mobil Berhasil di Checkout !");
                            this.setVisible(false);
                            ListParkir.main(null);
                        }else{
                            JOptionPane.showMessageDialog(this, "Plat Nomor yang dicari tidak ada !");
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "Masukkan Format Jam yang benar. Contoh: 00:00");
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Masukkan Tanggal Checkout lebih dari Tanggal Masuk !");
                }
            }
        }
        updateWaktu();
    }

    private static class ParkirTableModel extends AbstractTableModel{
        JCheckBox checkBox;
        private final String[] COLOUMS = {"Nomor", "Plat Nomor", "Tanggal", "Jam", "Admin"};
        private final Class[] COLOUMS_CLASS = {Integer.class, String.class, String.class, String.class, String.class};
        private ArrayList<Parkir> parkirArrayList;
        private ParkirTableModel(ArrayList<Parkir> list){
            this.parkirArrayList = list;
        }
        @Override
        public int getRowCount() {
            return parkirArrayList.size();
        }
        @Override
        public int getColumnCount() {
            return COLOUMS.length;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex){
                case 0 :
                    return rowIndex+1;
                case 1 :
                    return parkirArrayList.get(rowIndex).getKendaraan().getPlat_nomer();
                case 2 :
                    return parkirArrayList.get(rowIndex).getTanggalMasuk();
                case 3:
                    return parkirArrayList.get(rowIndex).getJamMasuk();
                case 4:
                    return parkirArrayList.get(rowIndex).getAdmin().getNama();
                default :
                    return "-";
            }
        }
        @Override
        public String getColumnName(int column) {
            return COLOUMS[column];
        }
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return COLOUMS_CLASS[columnIndex];
        }
    }

    public static void main(String[] args) {
        ListParkir listParkir = new ListParkir();
        listParkir.setFrame();
    }
}
