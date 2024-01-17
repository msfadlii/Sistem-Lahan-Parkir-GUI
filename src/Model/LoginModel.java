package Model;

import Entity.Admin;
import Model.JSON.ModelJSON;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LoginModel {
    ModelJSON modelJSON;
    ArrayList<Admin> adminArrayList;
    public LoginModel(){
        modelJSON = new ModelJSON<>("src/Database/admin.json");
        adminArrayList = modelJSON.readFromFile(new TypeToken<ArrayList<Admin>>() {}.getType());
        init();
    }
    public void init(){
        adminArrayList.add(new Admin("admin", "admin", "budi"));
    }

    public void insert(String username, String password, String nama){
        adminArrayList.add(new Admin(username, password, nama));
        modelJSON.writeToFile(adminArrayList);
    }

    public void delete(String username){
        adminArrayList.remove(searchAdmin(username));
        modelJSON.writeToFile(adminArrayList);
    }

    public ArrayList<Admin> listAdmin(){
        return adminArrayList;
    }

    public int searchAdmin(String username) {
        for (Admin admin : adminArrayList) {
            if (admin.getUsername().equals(username)) {
                return adminArrayList.indexOf(admin);
            }
        }
        return -1;
    }
}