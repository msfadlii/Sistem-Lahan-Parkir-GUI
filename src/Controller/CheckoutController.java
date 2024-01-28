package Controller;

import Entity.Checkout;
import Entity.Parkir;
import Model.CheckoutModel;
import Model.ParkirModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckoutController {
    CheckoutModel checkoutModel;
    ParkirModel parkirModel;
    DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter formatJam = DateTimeFormatter.ofPattern("HH:mm");
    public CheckoutController(){
        checkoutModel = new CheckoutModel();
        parkirModel = new ParkirModel();
    }

    public boolean checkout(String plat, String tanggal, String jam, String petugas){
        long harga=0;
        long totalHarga=0;
        if (parkirModel.findPlat(plat)){
            Parkir parkir = parkirModel.getParkir(plat);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateMasuk = parkir.getTanggalMasuk() + " " + parkir.getJamMasuk();
            String dateKeluar = tanggal + " " + jam;
            try {
                Date date1 = sdf.parse(dateMasuk);
                Date date2 = sdf.parse(dateKeluar);
                long diff = date2.getTime() - date1.getTime();
                long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                long diffMinutes = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);

                if(diffMinutes <= 10){
                    harga = 0;
                }else{
                    harga = 10000;
                    long sisaMenit = diffMinutes-10;
//                    int hargaPerHari = (int) Math.ceil((double) sisaMenit/(24 * 60)) * 5000;
                    int hargaPerHari = (int)diffDays * 5000;
                    totalHarga = harga + hargaPerHari;
                    System.out.println(totalHarga);
                }
            }catch (ParseException e){
                e.printStackTrace();
            }

            checkoutModel.checkout(plat, tanggal, jam, (int) totalHarga, petugas);
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Checkout> getListCheckout(){
        return checkoutModel.getListCheckout();
    }

    public boolean cetakStruk(String plat, String jenis_cetak){
        LocalDateTime now = LocalDateTime.now();
        String tanggal = formatTanggal.format(now);
        String jam = formatJam.format(now);
        String path = "src/Struk/";
        String fileName = "";
        if(jenis_cetak.equals("all")){
            fileName = tanggal +"-"+ now.getHour() + now.getMinute() + now.getSecond() + "Laporan" + ".txt";
            checkoutModel.buatFileStruk(path+fileName, plat, jenis_cetak);
            return true;
        }else if(jenis_cetak.equals("single")){
            if(checkoutModel.searchPlat(plat) != -1){
                fileName = tanggal +"-"+ now.getHour() + now.getMinute() + now.getSecond() + "Struk" + ".txt";
                checkoutModel.buatFileStruk(path+fileName, plat, jenis_cetak);
                return true;
            }
            return false;
        }
        return false;
    }
}
