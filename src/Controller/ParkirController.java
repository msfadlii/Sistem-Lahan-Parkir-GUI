package Controller;

import Entity.Parkir;
import Model.ParkirModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParkirController {
    ParkirModel modelParkir;

    public ParkirController(){
        modelParkir = new ParkirModel();
    }
    public boolean insert(String plat, String tanggal, String jam, String petugas){
        boolean cek = modelParkir.findPlat(plat);
        if(cek){
            return true;
        }else{
            modelParkir.insert(plat, tanggal, jam, petugas);
            return false;
        }
    }

    public boolean delete(String plat){
        if(modelParkir.findPlat(plat)){
            modelParkir.delete(plat);
            return true;
        }else{
            return false;
        }
    }

    public boolean cekTanggal(String plat, String tanggal){
        Parkir parkir = modelParkir.getParkir(plat);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(parkir != null){
            try {
                Date date1 = sdf.parse(parkir.getTanggalMasuk());
                Date date2 = sdf.parse(tanggal);
                long diff = date2.getTime() - date1.getTime();
                long diffDays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                if(diffDays >= 0){
                    return true;
                }
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean cekJam(String jam){
        String pola = "\\d{1,2}:\\d{2}"; //angka:angka
        Pattern pattern = Pattern.compile(pola);
        // mencocokan input dengan pola
        Matcher matcher = pattern.matcher(jam);

        if(matcher.matches()){
            return true;
        }else{
            return false;
        }

    }

    public ArrayList<Parkir> getListParkir(){
        return modelParkir.viewAllListParkir();
    }
}
