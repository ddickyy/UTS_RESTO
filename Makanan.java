// Makanan.java
public abstract class Makanan implements DapatDiNilai {
    private String nama;
    private double harga;
    private int totalRating = 0;
    private int jumlahRating = 0;

    public Makanan(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    // subclass dapat override untuk detail pembuatan
    public abstract String deskripsi();

    // implementasi interface: menghitung rata-rata rating
    @Override
    public double beriRating(int rating) {
        if (rating < 1) rating = 1;
        if (rating > 5) rating = 5;
        totalRating += rating;
        jumlahRating++;
        return getRataRataRating();
    }

    public double getRataRataRating() {
        if (jumlahRating == 0) return 0.0;
        return (double) totalRating / jumlahRating;
    }

    @Override
    public String toString() {
        return String.format("%s (Rp %.0f) - rating: %.2f", nama, harga, getRataRataRating());
    }
}
