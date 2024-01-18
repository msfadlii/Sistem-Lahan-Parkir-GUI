package Controller;

import Entity.Parkir;
import Model.ParkirModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public ArrayList<Parkir> getListParkir(){
        return modelParkir.viewAllListParkir();
    }
}
