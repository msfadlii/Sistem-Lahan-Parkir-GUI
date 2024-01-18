package Entity;

public class Admin {
    private String username;
    private String password;
    private String nama;

    public Admin(String username, String password, String nama){
        this.username = username;
        this.password = password;
        this.nama = nama;
    }

    public Admin(String nama){
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }
}
