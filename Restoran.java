import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restoran {
    private double keuangan;
    private int reputasi;
    private List<Karyawan> daftarKaryawan = new ArrayList<>();
    public List<Makanan> menu = new ArrayList<>();
    private Map<String, Integer> inventoriBahan = new HashMap<>();

    public Restoran(double modalAwal) {
        this.keuangan = modalAwal;
        this.reputasi = 100; // Reputasi awal
    }
    
    public void tambahKaryawan(Karyawan karyawan) {
        daftarKaryawan.add(karyawan);
        System.out.println(karyawan.getNama() + " direkrut sebagai " + karyawan.skill);
    }

    public void tambahMenu(Makanan makanan) {
        menu.add(makanan);
    }
    
    public void tambahStok(String bahan, int jumlah) {
        inventoriBahan.put(bahan, inventoriBahan.getOrDefault(bahan, 0) + jumlah);
        System.out.println("Stok " + bahan + " ditambah sebanyak " + jumlah + ". Total: " + inventoriBahan.get(bahan));
    }

    public boolean cekDanAmbilBahan(Makanan makanan) {
        for (String bahan : makanan.getBahan()) {
            if (inventoriBahan.getOrDefault(bahan, 0) < 1) {
                System.out.println("!!! STOK HABIS: " + bahan + " untuk membuat " + makanan.getNama());
                reputasi -= 5; // Reputasi turun karena stok habis
                return false;
            }
        }
        // Jika semua bahan ada, kurangi stok
        for (String bahan : makanan.getBahan()) {
            inventoriBahan.put(bahan, inventoriBahan.get(bahan) - 1);
        }
        return true;
    }

    public void prosesPesanan(Pesanan pesanan) {
        System.out.println("\n--- Memproses Pesanan untuk " + pesanan.getPelanggan().getNama() + " ---");
        
        // Cek stok bahan
        if (!cekDanAmbilBahan(pesanan.getMakanan())) {
            System.out.println("Pesanan tidak dapat diproses karena bahan tidak cukup.");
            return;
        }

        // Cari Koki yang tersedia (penerapan Polimorfisme)
        Koki koki = null;
        for (Karyawan k : daftarKaryawan) {
            if (k instanceof Koki) { // Cek apakah karyawan ini adalah seorang Koki
                koki = (Koki) k;
                koki.bekerja();
                break;
            }
        }

        if (koki != null) {
            koki.masak(pesanan.getMakanan()); // Memasak pesanan
            pesanan.selesaikan();
            
            // Proses pembayaran
            this.keuangan += pesanan.getMakanan().getHarga();
            System.out.println("Pembayaran diterima sebesar Rp" + pesanan.getMakanan().getHarga());
            
            // Kepuasan pelanggan
            if (pesanan.getPelanggan().apakahSabar()) {
                System.out.println(pesanan.getPelanggan().getNama() + " puas dengan pelayanan!");
                
                // Tambahkan 2 poin ke reputasi
                reputasi += 2;                 
                if (reputasi > 100) {
                    reputasi = 100;
                }
                
                koki.beriRating(1); 
            } else {
                System.out.println(pesanan.getPelanggan().getNama() + " tidak sabar dan sedikit kecewa.");
                reputasi -= 3;
            }
        } else {
            System.out.println("Tidak ada koki yang tersedia untuk memasak!");
            reputasi -= 10;
        }
    }
    
    public void tampilkanStatus() {
        System.out.println("\n===== STATUS RESTORAN =====");
        System.out.println("Keuangan: Rp" + keuangan);
        System.out.println("Reputasi: " + reputasi);
        System.out.println("Stok Bahan: " + inventoriBahan);
        System.out.println("===========================\n");
    }

    public void tampilkanRatingMenu() {
        System.out.println("\n===== RATING MENU MAKANAN =====");
        if (menu.isEmpty()) {
            System.out.println("Belum ada makanan di menu.");
            return;
        }
        
        for (Makanan makanan : menu) {
            // String.format("%.2f", ...) digunakan untuk membulatkan menjadi 2 angka desimal
            System.out.printf("- %s: Rata-rata Rating %.2f\n", makanan.getNama(), makanan.getRataRataRating());
        }
        System.out.println("===============================\n");
    }
    
    // Metode getMenu() yang sudah ada sebelumnya
    public List<Makanan> getMenu() {
        return this.menu;
    }
    // Tambahkan metode ini
}