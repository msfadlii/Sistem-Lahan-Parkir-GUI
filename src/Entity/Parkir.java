package Entity;

public class Parkir {
    private int harga;
    private String tanggal;
    private String tanggal_keluar;
    private Mobil mobil;
    private Admin admin;

    public Parkir(String plat, String tanggal, String petugas){
        this.mobil = new Mobil(plat);
        this.tanggal = tanggal;
        this.harga = harga;
        this.admin = new Admin(petugas);
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setMobil(String plat) {
        this.mobil = new Mobil(plat);
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getTanggal_keluar() {
        return tanggal_keluar;
    }

    public int getHarga() {
        return harga;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Mobil getMobil() {
        return mobil;
    }

    public void setTanggal_keluar(String tanggal_keluar) {
        this.tanggal_keluar = tanggal_keluar;
    }
}
