import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restoran {
    private double keuangan;
    private int reputasi;
    private List<Karyawan> daftarKaryawan = new ArrayList<>();
    private List<Makanan> menu = new ArrayList<>();
    private Map<String, Integer> inventoriBahan = new HashMap<>();
    // Atribut baru untuk menyimpan harga setiap bahan baku
    private Map<String, Double> hargaBahan = new HashMap<>();

    public Restoran(double modalAwal) {
        this.keuangan = modalAwal;
        this.reputasi = 100;
        // Panggil metode untuk mengisi harga bahan saat restoran dibuat
        inisialisasiHargaBahan();
    }

    /**
     * Metode internal untuk menetapkan harga dasar bahan baku.
     */
    private void inisialisasiHargaBahan() {
        hargaBahan.put("Roti", 3000.0);
        hargaBahan.put("Daging", 5000.0);
        hargaBahan.put("Sayur", 2000.0);
        hargaBahan.put("Kentang", 4000.0);
        hargaBahan.put("Minyak", 2500.0);
    }

    /**
     * Merekrut karyawan baru dan menambahkannya ke daftar.
     * @param karyawan Karyawan yang akan ditambahkan.
     */
    public void tambahKaryawan(Karyawan karyawan) {
        daftarKaryawan.add(karyawan);
        System.out.println(karyawan.getNama() + " direkrut sebagai " + karyawan.skill);
    }

    /**
     * Menambahkan item makanan baru ke menu restoran.
     * @param makanan Makanan yang akan ditambahkan.
     */
    public void tambahMenu(Makanan makanan) {
        menu.add(makanan);
    }

    /**
     * Membeli stok bahan baru. Metode ini sekarang mengurangi keuangan restoran.
     * @param bahan Nama bahan yang akan dibeli.
     * @param jumlah Jumlah yang akan dibeli.
     */
    public void tambahStok(String bahan, int jumlah) {
        if (!hargaBahan.containsKey(bahan)) {
            System.out.println("Bahan '" + bahan + "' tidak dikenal.");
            return;
        }

        double totalBiaya = hargaBahan.get(bahan) * jumlah;
        if (this.keuangan < totalBiaya) {
            System.out.println("!!! Uang tidak cukup untuk membeli stok " + bahan + ". Butuh Rp" + totalBiaya);
            return;
        }

        this.keuangan -= totalBiaya; // Mengurangi uang restoran sesuai biaya pembelian
        inventoriBahan.put(bahan, inventoriBahan.getOrDefault(bahan, 0) + jumlah);
        System.out.println("Berhasil membeli " + jumlah + " " + bahan + " seharga Rp" + totalBiaya);
        System.out.println("Sisa uang: Rp" + this.keuangan);
    }

    /**
     * Memeriksa ketersediaan bahan, dan jika ada, menguranginya dari inventori.
     * @param makanan Makanan yang akan dibuat.
     * @return true jika bahan cukup, false jika tidak.
     */
    public boolean cekDanAmbilBahan(Makanan makanan) {
        for (String bahan : makanan.getBahan()) {
            if (inventoriBahan.getOrDefault(bahan, 0) < 1) {
                System.out.println("!!! STOK HABIS: " + bahan + " untuk membuat " + makanan.getNama());
                reputasi -= 5;
                return false;
            }
        }
        for (String bahan : makanan.getBahan()) {
            inventoriBahan.put(bahan, inventoriBahan.get(bahan) - 1);
        }
        return true;
    }

    /**
     * Memproses pesanan dari pelanggan, dari antrian hingga pembayaran.
     * @param pesanan Objek pesanan yang berisi info pelanggan dan makanan.
     */
    public void prosesPesanan(Pesanan pesanan) {
        System.out.println("\n--- Memproses Pesanan untuk " + pesanan.getPelanggan().getNama() + " ---");

        // Simulasi pelanggan menunggu, yang mengurangi kesabaran mereka
        System.out.println(pesanan.getPelanggan().getNama() + " sedang menunggu...");
        try {
            Thread.sleep(1500); // Jeda untuk simulasi waktu tunggu
            pesanan.getPelanggan().kurangiKesabaran();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (!cekDanAmbilBahan(pesanan.getMakanan())) {
            System.out.println("Pesanan tidak dapat diproses karena bahan tidak cukup.");
            return;
        }

        Koki koki = null;
        for (Karyawan k : daftarKaryawan) {
            if (k instanceof Koki) {
                koki = (Koki) k;
                koki.bekerja();
                break;
            }
        }

        if (koki != null) {
            koki.masak(pesanan.getMakanan());
            pesanan.selesaikan();

            this.keuangan += pesanan.getMakanan().getHarga();
            System.out.println("Pembayaran diterima sebesar Rp" + pesanan.getMakanan().getHarga());

            if (pesanan.getPelanggan().apakahSabar()) {
                System.out.println(pesanan.getPelanggan().getNama() + " puas dengan pelayanan!");
                reputasi += 2;
                if (reputasi > 100) {
                    reputasi = 100; // Membatasi reputasi maksimal 100
                }
                koki.beriRating(1);
            } else {
                System.out.println(pesanan.getPelanggan().getNama() + " tidak sabar dan sedikit kecewa karena menunggu.");
                reputasi -= 3;
            }
        } else {
            System.out.println("Tidak ada koki yang tersedia untuk memasak!");
            reputasi -= 10;
        }
    }

    /**
     * Menampilkan status terkini dari restoran (keuangan, reputasi, dan stok).
     */
    public void tampilkanStatus() {
        System.out.println("\n===== STATUS RESTORAN =====");
        System.out.printf("Keuangan: Rp%,.2f\n", keuangan); // Format angka agar lebih mudah dibaca
        System.out.println("Reputasi: " + reputasi + "/100");
        System.out.println("Stok Bahan: " + inventoriBahan);
        System.out.println("===========================");
    }

    /**
     * Menampilkan rata-rata rating untuk setiap item di menu.
     */
    public void tampilkanRatingMenu() {
        System.out.println("\n===== RATING MENU MAKANAN =====");
        if (menu.isEmpty()) {
            System.out.println("Belum ada makanan di menu.");
            return;
        }
        
        for (Makanan makanan : menu) {
            System.out.printf("- %s: Rata-rata Rating %.2f\n", makanan.getNama(), makanan.getRataRataRating());
        }
        System.out.println("===============================");
    }
    
    // === GETTER METHODS ===

    /**
     * Mengembalikan daftar menu makanan.
     * @return List Makanan
     */
    public List<Makanan> getMenu() {
        return this.menu;
    }

    /**
     * Mengembalikan nilai reputasi saat ini.
     * @return int reputasi
     */
    public int getReputasi() {
        return this.reputasi;
    }
}