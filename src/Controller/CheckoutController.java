package Controller;

import Entity.Checkout;
import Entity.Parkir;
import Model.CheckoutModel;
import Model.ParkirModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CheckoutController {
    CheckoutModel checkoutModel;
    ParkirModel parkirModel;
    public CheckoutController(){
        checkoutModel = new CheckoutModel();
        parkirModel = new ParkirModel();
    }

    public boolean checkout(String plat, String tanggal, String jam, String petugas){
        long harga=0;
        if (parkirModel.findPlat(plat)){
            Parkir parkir = parkirModel.getParkir(plat);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateMasuk = parkir.getTanggalMasuk() + " " + parkir.getJamMasuk();
            String dateKeluar = tanggal + " " + jam;
            try {
                Date date1 = sdf.parse(dateMasuk);
                Date date2 = sdf.parse(dateKeluar);
                long diff = date2.getTime() - date1.getTime();
                diff = diff - 10;
                long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                long diffMinutes = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);

                if(diffDays > 0){
                    harga = diffDays * 5000;
                }
                harga = harga+(diffMinutes*10000/10);

            }catch (ParseException e){
                e.printStackTrace();
            }

            checkoutModel.checkout(plat, tanggal, jam, (int) harga, petugas);
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Checkout> getListCheckout(){
        return checkoutModel.getListCheckout();
    }
}
