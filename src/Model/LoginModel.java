package Model;

import Entity.Admin;
import Model.JSON.JSONModel;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LoginModel{
   JSONModel jsonModel;
    ArrayList<Admin> adminArrayList;
    public LoginModel(){
        jsonModel = new JSONModel("src/Database/admin.json");
        adminArrayList = jsonModel.readFromFile(new TypeToken<ArrayList<Admin>>() {}.getType());
//        init();
    }
//    public void init(){
//        adminArrayList.add(new Admin("123", "123", "ani"));
//        modelJSON.writeToFile(adminArrayList);
//    }

    public void insert(String username, String password, String nama){
        adminArrayList.add(new Admin(username, password, nama));
        jsonModel.writeToFile(adminArrayList);
    }

    public void delete(String username){
        adminArrayList.remove(searchAdmin(username));
        jsonModel.writeToFile(adminArrayList);
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