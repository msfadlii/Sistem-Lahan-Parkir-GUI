package Entity;

public class Checkout extends Parkir{
    int harga;
    String tanggalKeluar;
    String jamKeluar;
    public Checkout(String plat, String tanggalMasuk, String jamMasuk, String tanggalKeluar, String jamKeluar, int harga, String petugas) {
        super(plat, tanggalMasuk, jamMasuk, petugas);
        this.harga = harga;
        this.tanggalKeluar = tanggalKeluar;
        this.jamKeluar = jamKeluar;
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
