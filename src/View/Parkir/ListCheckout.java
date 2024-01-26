package View.Parkir;

import Controller.CheckoutController;
import Entity.Checkout;
import View.Login.Login;

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

public class ListCheckout extends JFrame implements ActionListener {
    JLabel namaAdmin, cetakStruk, platLabel, image, jam;
    ImageIcon imageIcon;
    JButton menuParkir, menuListParkir, menuListCheckout, logoutButton, cetakAllButton, cetakButton;
    JTextField platField;
    JPanel panel;
    static JTable jTable;
    JScrollPane scrollPane;
    CheckoutController checkoutController = new CheckoutController();
    ArrayList<Checkout> checkoutArrayList = checkoutController.getListCheckout();
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
        jam = new JLabel();
        jam.setBounds(720, 0, 300, 20);
        jam.setFont(new Font("Times New Roman", Font.BOLD, 14));
        Timer timer = new Timer(1000, this);
        timer.start();
        updateWaktu();

        JLabel listLabel = new JLabel("List Checkout Parkir");
        listLabel.setBounds(380, 10, 150, 25);
        listLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        CheckoutTableModel checkoutTableModel = new CheckoutTableModel(checkoutArrayList);
        jTable = new JTable(checkoutTableModel);
        jTable.setAutoCreateRowSorter(true);
        jTable.setRowHeight(40);

        //buat kolom jadi tengah
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable.getColumnModel().getColumn(0).setMaxWidth(40);
        jTable.getColumnModel().getColumn(3).setMaxWidth(65);
        jTable.getColumnModel().getColumn(5).setMaxWidth(65);
        jTable.getColumnModel().getColumn(7).setMaxWidth(50);
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        scrollPane = new JScrollPane(jTable);
        scrollPane.setBounds(170, 40, 600, 230);
        //mengilangkan border
        scrollPane.setBorder(new LineBorder(new Color(0, 0, 0, 0)));

        JTableHeader header = jTable.getTableHeader();
        header.setBackground(Color.gray);
        header.setForeground(Color.white);
        header.setFont(new Font("Times New Roman", Font.BOLD, 12));
        scrollPane.setColumnHeaderView(header);

        cetakStruk = new JLabel("Cetak Struk");
        cetakStruk.setBounds(200, 310, 100, 20);
        cetakStruk.setFont(new Font("Times New Roman", Font.BOLD, 14));
        platLabel = new JLabel("Pilih Plat Nomor : ");
        platLabel.setBounds(200, 330, 130, 20);
        platLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
        platField = new JTextField();
        platField.setBounds(200, 350, 120, 30);
        platField.setFont(new Font("Times New Roman", Font.BOLD, 12));
        cetakButton = new JButton("Cetak");
        cetakButton.setBounds(340, 350, 70, 30);
        cetakButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        cetakButton.addActionListener(this);
        cetakAllButton = new JButton("Cetak All");
        cetakAllButton.setBounds(430, 350, 100, 30);
        cetakAllButton.setFont(new Font("Times New Roman", Font.BOLD, 12));
        cetakAllButton.addActionListener(this);

        ImageIcon back = new ImageIcon("src/res/back4.png");
        JLabel labelBack = new JLabel(back);
        labelBack.setBounds(150, 120, 650, 480);

        add(panel);
        add(jam);
        add(scrollPane);
        add(listLabel);
        add(cetakStruk);
        add(platLabel);
        add(platField);
        add(cetakAllButton);
        add(cetakButton);
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
            login.main(null);
        }

        if(event.getSource() == cetakAllButton){
            if(checkoutController.cetakStruk("", "all")){
                JOptionPane.showMessageDialog(this, "Struk berhasil dibuat!");
            }else{
                JOptionPane.showMessageDialog(this, "Struk gagal dibuat!");
            }
        }

        if(event.getSource() == cetakButton){
            if(platField.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Lengkapi input terlebih dahulu!");
            }else{
                if(checkoutController.cetakStruk(platField.getText(), "single")){
                    JOptionPane.showMessageDialog(this, "Struk berhasil dibuat!");
                }else{
                    JOptionPane.showMessageDialog(this, "Plat Nomor Tidak ada!");
                }
            }
        }
        updateWaktu();
    }

    private static class CheckoutTableModel extends AbstractTableModel {
        private final String[] COLOUMS = {"Nomor", "Plat Nomor", "Tanggal Masuk", "Jam Masuk", "Tanggal Keluar", "Jam Keluar", "Harga", "Admin"};
        private final Class[] COLOUMS_CLASS = {Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, String.class};
        private ArrayList<Checkout> checkoutArrayList;
        private CheckoutTableModel(ArrayList<Checkout> list){
            this.checkoutArrayList = list;
        }
        @Override
        public int getRowCount() {
            return checkoutArrayList.size();
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
                    return checkoutArrayList.get(rowIndex).getKendaraan().getPlat_nomer();
                case 2 :
                    return checkoutArrayList.get(rowIndex).getTanggalMasuk();
                case 3:
                    return checkoutArrayList.get(rowIndex).getJamMasuk();
                case 4:
                    return checkoutArrayList.get(rowIndex).getTanggalKeluar();
                case 5:
                    return checkoutArrayList.get(rowIndex).getJamKeluar();
                case 6:
                    return checkoutArrayList.get(rowIndex).getHarga();
                case 7:
                    return checkoutArrayList.get(rowIndex).getAdmin().getNama();
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
        ListCheckout listCheckout = new ListCheckout();
        listCheckout.setFrame();
    }
}