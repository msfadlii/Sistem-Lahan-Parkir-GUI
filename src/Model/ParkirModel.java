package Model;

import Entity.Mobil;
import Entity.Parkir;
import Model.JSON.ModelJSON;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ParkirModel {
    ModelJSON modelJSONParkir;

    private ArrayList<Mobil> listMobil;
    private ArrayList<Parkir> listParkir;

    public ParkirModel(){
        modelJSONParkir = new ModelJSON<>("src/Database/parkir.json");
        listParkir = new ArrayList<>();
        listParkir = modelJSONParkir.readFromFile(new TypeToken<ArrayList<Parkir>>() {}.getType());
    }

    public void insert(String plat, String tanggal, String petugas){
        listParkir.add(new Parkir(plat, tanggal, petugas));
        modelJSONParkir.writeToFile(listParkir);
    }

    public void update(Parkir parkir){
        int index = listParkir.indexOf(parkir);
        listParkir.get(index).setTanggal(parkir.getTanggal());
        listParkir.get(index).setMobil(parkir.getMobil().getPlat_nomer());

        modelJSONParkir.writeToFile(listParkir);
    }

    public void delete(Parkir parkir){
        listParkir.remove(parkir);
        modelJSONParkir.writeToFile(listParkir);
    }

    public ArrayList<Parkir> viewAllListParkir(){
        return listParkir;
    }

    public boolean findPlat(String plat){
        for (Parkir parkir:listParkir) {
            if(parkir.getMobil().getPlat_nomer().equals(plat)){
                return true;
            }
        }
        return false;
    }
}
