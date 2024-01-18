package Entity;
public class Parkir {
    private String tanggalMasuk;
    private String jamMasuk;
    private String tanggal_keluar;
    private Mobil mobil;
    private Admin admin;

    public Parkir(String plat, String tanggalMasuk, String jamMasuk, String petugas){
        this.mobil = new Mobil(plat);
        this.tanggalMasuk = tanggalMasuk;
        this.jamMasuk = jamMasuk;
        this.admin = new Admin(petugas);
    }

    public void setTanggalMasuk(String tanggalMasuk) {
        this.tanggalMasuk = tanggalMasuk;
    }

    public void setMobil(String plat) {
        this.mobil = new Mobil(plat);
    }

    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public String getTanggal_keluar() {
        return tanggal_keluar;
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
