package Model;

import Entity.Checkout;
import Entity.Parkir;
import Model.JSON.JSONModel;
import View.Login.Login;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CheckoutModel {
    JSONModel jsonModelCheckout;
    ParkirModel parkirModel;
    DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter formatJam = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime now = LocalDateTime.now();

    private ArrayList<Checkout> listCheckout;
    public CheckoutModel(){
        parkirModel = new ParkirModel();
        jsonModelCheckout = new JSONModel<>("src/Database/checkout.json");
        listCheckout = new ArrayList<>();

        listCheckout = jsonModelCheckout.readFromFile(new TypeToken<ArrayList<Checkout>>() {}.getType());
    }

    public void checkout(String plat, String tanggal, String jam, int harga, String petugas){
        Parkir parkir = parkirModel.getParkir(plat);
        listCheckout.add(new Checkout(plat, parkir.getTanggalMasuk(), parkir.getJamMasuk(), tanggal, jam, harga, petugas));
        parkirModel.delete(plat);
        jsonModelCheckout.writeToFile(listCheckout);
    }

    public ArrayList<Checkout> getListCheckout(){
        return listCheckout;
    }

    public int searchPlat(String plat){
        for (Checkout checkout:listCheckout) {
            if (checkout.getKendaraan().getPlat_nomer().equals(plat)){
                return listCheckout.indexOf(checkout);
            }
        }
        return -1;
    }

    public boolean buatFileStruk(String path, String plat, String jenis_cetak){
        try {
            File myObj = new File(path);
//            if (myObj.createNewFile()) {
//                //file berhasil dibuat
//            } else {
//                //file sudah ada
//            }
            myObj.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cetakStrukTxt(path, plat, jenis_cetak);
        return true;
    }

    public void cetakStrukTxt(String path, String plat, String jenis_cetak){
        DayOfWeek dow = now.getDayOfWeek();
        //Write File
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            if (jenis_cetak.equals("all")) {
                writer.write("------------------------------------------------------------------------------------------------------------------------------\n\t\t\t\t\t\t\t\t\t\t\tSISTEM LAHAN PARKIR\n" +
                        "\n------------------------------------------------------------------------------------------------------------------------------\n");
                writer.write("Tanggal : "+dow+", "+formatTanggal.format(now)+
                        "\nPetugas : "+ Login.nama +
                        "\n------------------------------------------------------------------------------------------------------------------------------\n");
                writer.write(" \t\t\t\t\t\t\t\t\t\t\tLAPORAN CHECKOUT"+
                        "\n------------------------------------------------------------------------------------------------------------------------------\n");
                writer.write("\nPlat Nomor \t\tTanggal Masuk \t\tJam Masuk \t\tTanggal Keluar \t\tJam Keluar \t\tHarga \t\t\tPetugas\n");
                if (listCheckout != null) {
                    for (Checkout checkout : listCheckout){
                        writer.write(
                                checkout.getKendaraan().getPlat_nomer()+" \t\t"+
                                        checkout.getTanggalMasuk()+" \t\t\t"+
                                        checkout.getJamMasuk()+" \t\t\t"+
                                        checkout.getTanggalKeluar()+" \t\t\t"+
                                        checkout.getJamKeluar()+" \t\t\t"+
                                        checkout.getHarga()+" \t\t\t"+
                                        checkout.getAdmin().getNama()+"\n"
                        );
                    }
                } else {
                    System.out.println("Tidak ada data!");
                }
            }else if(jenis_cetak.equals("single")){
                writer.write("===========================================\n");
                writer.write("\t\t\tSISTEM LAHAN PARKIR\n");
                writer.write("\t\t\tSTRUK PEMBAYARAN\n");
                writer.write("===========================================\n");
                int index = searchPlat(plat);
                writer.write("Plat Nomor     : "+listCheckout.get(index).getKendaraan().getPlat_nomer());
                writer.write("\nTanggal Masuk  : "+listCheckout.get(index).getTanggalMasuk());
                writer.write("\nJam Masuk      : "+listCheckout.get(index).getJamMasuk());
                writer.write("\nTanggal Keluar : "+listCheckout.get(index).getTanggalKeluar());
                writer.write("\nJam Keluar     : "+listCheckout.get(index).getJamKeluar());
                writer.write("\nHarga          : "+listCheckout.get(index).getHarga());
                writer.write("\n===========================================");
            }
            System.out.println("Berhasil Menulis File.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}