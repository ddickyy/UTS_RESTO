
// Restoran.java
import java.util.*;

public class Restoran {
    private String nama;
    private double keuangan;
    private int reputasi;
    private List<Karyawan> daftarKaryawan = new ArrayList<>();
    private List<Makanan> menu = new ArrayList<>();
    private Map<Makanan, Integer> stokMenu = new HashMap<>();

    public Restoran(String nama, double modalAwal) {
        this.nama = nama;
        this.keuangan = modalAwal;
        this.reputasi = 50;
    }

    // Manajemen karyawan
    public void tambahKaryawan(Karyawan k) {
        daftarKaryawan.add(k);
    }

    public List<Karyawan> getKaryawan() {
        return daftarKaryawan;
    }

    // ======== MENU & STOK ========

    public void tambahMenu(Makanan m, int stokAwal) {
        menu.add(m);
        stokMenu.put(m, stokAwal);
    }

    public List<Makanan> getMenu() {
        return menu;
    }

    public int getStok(Makanan m) {
        return stokMenu.getOrDefault(m, 0);
    }

    public void tambahStok(Makanan m, int jumlah) {
        stokMenu.put(m, getStok(m) + jumlah);
    }

    public boolean kurangiStok(Makanan m, int jumlah) {
        int sisa = getStok(m) - jumlah;
        if (sisa < 0)
            return false;
        stokMenu.put(m, sisa);
        return true;
    }

    public double getKeuangan() {
        return keuangan;
    }

    public void tambahKeuangan(double jumlah) {
        keuangan += jumlah;
    }

    public void kurangiKeuangan(double jumlah) {
        keuangan -= jumlah;
    }

    public void prosesPesanan(Pesanan pesanan) {
        Koki koki = null;
        Kasir kasir = null;
        for (Karyawan k : daftarKaryawan) {
            if (k instanceof Koki && koki == null)
                koki = (Koki) k;
            if (k instanceof Kasir && kasir == null)
                kasir = (Kasir) k;
        }

        if (koki == null || kasir == null) {
            System.out.println("❌ Tidak ada staf lengkap (koki/kasir)!");
            return;
        }

        // Cek stok semua item sebelum diproses
        for (Makanan m : pesanan.getDaftar()) {
            if (getStok(m) <= 0) {
                System.out.println("⚠️ Stok habis untuk: " + m.getNama());
                return;
            }
        }

        // Kurangi stok
        for (Makanan m : pesanan.getDaftar()) {
            kurangiStok(m, 1);
            koki.siapkan(m);
        }

        // Kasir memproses pembayaran
        boolean berhasil = kasir.prosesPembayaran(pesanan.getPelanggan(), pesanan, this);

        if (berhasil) {
            reputasi++;
        } else {
            reputasi--;
        }
    }

    @Override
    public String toString() {
        return String.format("%s - Keuangan: Rp %.0f | Reputasi: %d | Menu: %d",
                nama, keuangan, reputasi, menu.size());
    }

    public void tampilkanStok() {
        System.out.println("\n=== STOK MENU ===");
        for (Makanan m : menu) {
            System.out.printf("%s : %d porsi\n", m.getNama(), getStok(m));
        }
    }
}
