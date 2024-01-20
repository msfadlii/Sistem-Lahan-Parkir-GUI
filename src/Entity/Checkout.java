package Entity;

public class Checkout extends Parkir{
    String tanggalKeluar;
    String jamKeluar;
    int harga;
    public Checkout(String plat, String tanggalMasuk, String jamMasuk, String tanggalKeluar, String jamKeluar, int harga, String petugas) {
        super(plat, tanggalMasuk, jamMasuk, petugas);
        this.tanggalKeluar = tanggalKeluar;
        this.jamKeluar = jamKeluar;
        this.harga = harga;
    }

    public int getHarga() {
        return harga;
    }

    public String getTanggalKeluar() {
        return tanggalKeluar;
    }

    public String getJamKeluar() {
        return jamKeluar;
    }
}
