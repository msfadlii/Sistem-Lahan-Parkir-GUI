package Model;

import Entity.Checkout;
import Entity.Parkir;
import Model.JSON.ModelJSON;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CheckoutModel {
    ModelJSON modelJSONCheckout;
    ParkirModel parkirModel;

    private ArrayList<Checkout> listCheckout;
    public CheckoutModel(){
        parkirModel = new ParkirModel();
        modelJSONCheckout = new ModelJSON<>("src/Database/checkout.json");
        listCheckout = new ArrayList<>();

        listCheckout = modelJSONCheckout.readFromFile(new TypeToken<ArrayList<Checkout>>() {}.getType());
    }

    public void checkout(String plat, String tanggal, String jam, int harga, String petugas){
        Parkir parkir = parkirModel.getParkir(plat);
        listCheckout.add(new Checkout(plat, parkir.getTanggalMasuk(), parkir.getJamMasuk(), tanggal, jam, harga, petugas));
        modelJSONCheckout.writeToFile(listCheckout);
    }

    public ArrayList<Checkout> getListCheckout(){
        return listCheckout;
    }
}
