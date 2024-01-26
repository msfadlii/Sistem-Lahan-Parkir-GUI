package Entity;
public class Parkir {
    protected String tanggalMasuk;
    protected String jamMasuk;
    protected Kendaraan kendaraan;
    protected Admin admin;

    public Parkir(String plat, String tanggalMasuk, String jamMasuk, String petugas){
        this.kendaraan = new Kendaraan(plat);
        this.tanggalMasuk = tanggalMasuk;
        this.jamMasuk = jamMasuk;
        this.admin = new Admin(petugas);
    }
    public String getTanggalMasuk() {
        return tanggalMasuk;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Kendaraan getKendaraan() {
        return kendaraan;
    }
}