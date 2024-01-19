package View.Parkir;

import Controller.CheckoutController;
import Controller.ParkirController;
import Entity.Parkir;
import View.Login.Login;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListParkir extends JFrame implements ActionListener {
    JLabel menu, namaAdmin, pilihLabel, tglLabel, checkoutLabel;
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
        setVisible(true);
    }

    public void component(){

        panel = new JPanel();
        panel.setBounds(0,0, 150, 500);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);

        menu = new JLabel("=== Menu ===");
        menu.setBounds(35, 10, 100, 20);

        namaAdmin = new JLabel("Halo, Admin " + Login.nama);
        namaAdmin.setBounds(25, 40, 150, 20);

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

        JLabel listLabel = new JLabel("List Parkir");
        listLabel.setBounds(380, 10, 100, 25);

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
        scrollPane.setBounds(200, 40, 500, 250);
        //mengilangkan border
        scrollPane.setBorder(new LineBorder(new Color(0, 0, 0, 0)));

        JTableHeader header = jTable.getTableHeader();
        header.setBackground(Color.gray);
        header.setForeground(Color.white);
        scrollPane.setColumnHeaderView(header);
        for (int i = 0; i < jTable.getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        //untuk memilih salah satu plat
        checkoutLabel = new JLabel("Checkout Plat Nomor");
        checkoutLabel.setBounds(200, 320, 150, 20);
        pilihLabel = new JLabel("Pilih Plat Nomor : ");
        pilihLabel.setBounds(200, 340, 100, 20);
        pilihField = new JTextField();
        pilihField.setBounds(200, 360, 150, 30);
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(390, 360, 100, 20);
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(this);
        btnCheckout = new JButton("Checkout");
        btnCheckout.setBounds(500, 360, 100, 20);
        btnCheckout.setFocusPainted(false);
        btnCheckout.addActionListener(this);

        tglLabel = new JLabel("Tanggal dan Jam : ");
        tglLabel.setBounds(200, 390, 150, 20);
        datePicker = new DatePicker();
        datePicker.setBounds(200, 410, 200, 30);
        jamField = new JTextField();
        jamField.setBounds(410, 410, 100, 30);

        add(panel);
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
            parkirController.delete(data);
        }

        if(event.getSource() == btnCheckout){
            String data = pilihField.getText();
            tgl = datePicker.getDateStringOrEmptyString();
            jam = jamField.getText();
            boolean cek = checkoutController.checkout(data, tgl, jam, Login.nama);
            if(cek){
                JOptionPane.showMessageDialog(this, "Mobil Berhasil di Checkout !");
            }else{
                JOptionPane.showMessageDialog(this, "Plat Nomor yang dicari tidak ada !");
            }
        }
    }

    private static class ParkirTableModel extends AbstractTableModel{
        JCheckBox checkBox;
        private final String[] COLOUMS = {"Nomor", "Plat Nomor", "Tanggal", "Jam", "Petugas"};
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
                    return parkirArrayList.get(rowIndex).getMobil().getPlat_nomer();
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
