package Controller;

import Model.ParkirModel;

public class ParkirController {
    ParkirModel modelParkir;

    public ParkirController(){
        modelParkir = new ParkirModel();
    }
    public boolean insert(String plat, String tanggal, String petugas){
        boolean cek = modelParkir.findPlat(plat);
        if(cek){
            return true;
        }else{
            modelParkir.insert(plat, tanggal, petugas);
            return false;
        }
    }

    public void update(){

    }

    public void delete(){

    }
}
