package Controller;

import Entity.Admin;
import Model.LoginModel;

import java.util.ArrayList;

public class LoginController {
    LoginModel loginModel;

    public LoginController(){
        loginModel = new LoginModel();
    }

    public boolean cekLogin(String username, String password){
        ArrayList<Admin> list = loginModel.listAdmin();

        for (Admin admin:list) {
            if(username.equals(admin.getUsername())){
                if(password.equals(admin.getPassword())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean insertAdmin(String username, String password, String nama){
        if(loginModel.searchAdmin(username) != -1){
            return false;
        }else{
            loginModel.insert(username, password, nama);
            return true;
        }
    }

    public boolean deleteAdmin(String username){
        if(loginModel.searchAdmin(username) != -1){
            return false;
        }else{
            loginModel.delete(username);
            return true;
        }
    }

    public String getNama(String username){
       ArrayList<Admin> list = loginModel.listAdmin();
        for (Admin admin:list) {
            if (admin.getUsername().equals(username)) {
                return admin.getNama();
            }
        }
        return "";
    }
}
