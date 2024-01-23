package Model;

import Entity.Parkir;
import Model.JSON.JSONModel;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ParkirModel {
    JSONModel jsonModelParkir;

    private ArrayList<Parkir> listParkir;

    public ParkirModel(){
        jsonModelParkir = new JSONModel<>("src/Database/parkir.json");
        listParkir = new ArrayList<>();
        listParkir = jsonModelParkir.readFromFile(new TypeToken<ArrayList<Parkir>>() {}.getType());
    }

    public void initParkir(){
        listParkir.add(new Parkir("L 1234 AB", "2024-01-12", "12:10", "budi"));
        listParkir.add(new Parkir("W 5678 CD", "2024-01-01", "07:40", "budi"));
        listParkir.add(new Parkir("D 9876 EF", "2024-01-20", "21:15", "budi"));
        jsonModelParkir.writeToFile(listParkir);
    }

    public void insert(String plat, String tanggal, String jam, String petugas){
        listParkir.add(new Parkir(plat, tanggal, jam, petugas));
        jsonModelParkir.writeToFile(listParkir);
    }

    public void delete(String plat){
        listParkir.remove(getParkir(plat));
        jsonModelParkir.writeToFile(listParkir);
    }

    public ArrayList<Parkir> viewAllListParkir(){
        return listParkir;
    }

    public boolean findPlat(String plat){
        for (Parkir parkir:listParkir) {
            if(parkir.getKendaraan().getPlat_nomer().equals(plat)){
                return true;
            }
        }
        return false;
    }

    public Parkir getParkir(String plat){
        Parkir newParkir = null;
        for (Parkir parkir:listParkir) {
            if(parkir.getKendaraan().getPlat_nomer().equals(plat)){
                return parkir;
            }
        }
        return newParkir;
    }
}