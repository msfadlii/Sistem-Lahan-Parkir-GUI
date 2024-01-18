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
import java.util.ArrayList;

public class ListCheckout extends JFrame implements ActionListener {
    JLabel menu, namaAdmin;
    JButton menuParkir, menuListParkir, menuListCheckout, logoutButton;
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

        JLabel listLabel = new JLabel("List Checkout Parkir");
        listLabel.setBounds(380, 10, 150, 25);

        CheckoutTableModel checkoutTableModel = new CheckoutTableModel(checkoutArrayList);
        jTable = new JTable(checkoutTableModel);
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

        add(panel);
        add(scrollPane);
        add(listLabel);
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
    }

    private static class CheckoutTableModel extends AbstractTableModel {
        private final String[] COLOUMS = {"Nomor", "Plat Nomor", "Tanggal Masuk", "Tanggal Keluar", "Harga", "Petugas"};
        private final Class[] COLOUMS_CLASS = {Integer.class, String.class, String.class, String.class, Integer.class, String.class};
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
                    return checkoutArrayList.get(rowIndex).getMobil().getPlat_nomer();
                case 2 :
                    return checkoutArrayList.get(rowIndex).getTanggalMasuk();
                case 3:
                    return checkoutArrayList.get(rowIndex).getTanggalKeluar();
                case 4:
                    return checkoutArrayList.get(rowIndex).getHarga();
                case 5:
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
