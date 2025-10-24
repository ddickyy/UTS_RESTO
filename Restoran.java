// Restoran.java
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restoran {
    private String nama;
    private double keuangan;
    private int reputasi;
    private List<Karyawan> daftarKaryawan = new ArrayList<>();
    private List<Makanan> menu = new ArrayList<>();
    private Map<String, Integer> inventori = new HashMap<>();

    public Restoran(String nama, double modalAwal) {
        this.nama = nama;
        this.keuangan = modalAwal;
        this.reputasi = 50; // awalan
    }

    // manajemen staff
    public void tambahKaryawan(Karyawan k) {
        daftarKaryawan.add(k);
    }

    public List<Karyawan> getKaryawan() {
        return daftarKaryawan;
    }

    // manajemen menu
    public void tambahMenu(Makanan m) {
        menu.add(m);
    }

    public List<Makanan> getMenu() {
        return menu;
    }

    // inventori sederhana
    public void tambahInventori(String bahan, int qty) {
        inventori.put(bahan, inventori.getOrDefault(bahan, 0) + qty);
    }

    public int getStok(String bahan) {
        return inventori.getOrDefault(bahan, 0);
    }

    // keuangan
    public double getKeuangan() {
        return keuangan;
    }

    public void tambahKeuangan(double jumlah) {
        this.keuangan += jumlah;
    }

    public void kurangiKeuangan(double jumlah) {
        this.keuangan -= jumlah;
    }

    // contoh proses pesanan: koki menyiapkan -> kasir memproses pembayaran
    public void prosesPesanan(Pesanan pesanan) {
        // cari koki & kasir (contoh: ambil yang pertama)
        Koki koki = null;
        Kasir kasir = null;
        for (Karyawan k : daftarKaryawan) {
            if (k instanceof Koki && koki == null) koki = (Koki) k;
            if (k instanceof Kasir && kasir == null) kasir = (Kasir) k;
        }

        if (koki == null || kasir == null) {
            System.out.println("Tidak ada staf cukup (koki/ kasir) untuk memproses pesanan.");
            return;
        }

        // koki siapkan semua item
        for (Makanan m : pesanan.getDaftar()) {
            koki.siapkan(m);
        }

        // kasir proses pembayaran
        boolean bayar = kasir.prosesPembayaran(pesanan.getPelanggan(), pesanan, this);
        if (bayar) {
            // meningkatkan reputasi sedikit
            reputasi += 1;
        } else {
            reputasi -= 1;
        }
    }

    @Override
    public String toString() {
        return String.format("%s - Keuangan: Rp %.0f - Reputasi: %d - Karyawan: %d - Menu: %d",
                nama, keuangan, reputasi, daftarKaryawan.size(), menu.size());
    }
}
