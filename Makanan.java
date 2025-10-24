import java.util.ArrayList; // <-- TAMBAHKAN IMPORT INI
import java.util.List;

public abstract class Makanan {
    protected String nama;
    protected double harga;
    protected int waktuMasak;

    // TAMBAHKAN ATRIBUT BARU UNTUK MENYIMPAN RATING
    private List<Integer> daftarRating = new ArrayList<>();

    public Makanan(String nama, double harga, int waktuMasak) {
        this.nama = nama;
        this.harga = harga;
        this.waktuMasak = waktuMasak;
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
        if (daftarRating.isEmpty()) {
            return 0.0; // Kembalikan 0 jika belum ada yang memberi rating
        }
        
        double total = 0;
        for (int rating : daftarRating) {
            total += rating;
        }
        return total / daftarRating.size();
    }

    @Override
    public String toString() {
        return nama + " (Rp" + harga + ")";
    }
}