// Pelanggan.java
public class Pelanggan {
    private String nama;
    private double uang;

    public Pelanggan(String nama, double uang) {
        this.nama = nama;
        this.uang = uang;
    }

    public String getNama() {
        return nama;
    }

    public double getUang() {
        return uang;
    }

    public void kurangiUang(double jumlah) {
        this.uang -= jumlah;
    }

    @Override
    public String toString() {
        return nama + " (Rp " + (int) uang + ")";
    }
}
