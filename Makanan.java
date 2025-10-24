import java.util.ArrayList;
import java.util.List;

public abstract class Makanan {
    protected String nama;
    protected double harga;
    protected int waktuMasak;
    protected double biayaProduksi; // <-- ATRIBUT BARU: Biaya modal

    private List<Integer> daftarRating = new ArrayList<>();

    // Ubah constructor untuk menerima biayaProduksi
    public Makanan(String nama, double harga, int waktuMasak, double biayaProduksi) {
        this.nama = nama;
        this.harga = harga;
        this.waktuMasak = waktuMasak;
        this.biayaProduksi = biayaProduksi; // <-- Inisialisasi biaya
    }
    
    // Metode getter untuk biaya produksi
    public double getBiayaProduksi() {
        return biayaProduksi;
    }
    
    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public abstract List<String> getBahan();

    public void tambahRating(int rating) {
        this.daftarRating.add(rating);
        System.out.println("Terima kasih! Rating " + rating + " telah diberikan untuk " + this.nama);
    }
    
    public double getRataRataRating() {
        if (daftarRating.isEmpty()) return 0.0;
        double total = 0;
        for (int rating : daftarRating) total += rating;
        return total / daftarRating.size();
    }


    @Override
    public String toString() {
        return nama + " (Rp" + harga + ")";
    }

    
}