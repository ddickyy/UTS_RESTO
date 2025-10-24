// Kasir.java
public class Kasir extends Karyawan {

    public Kasir(String id, String nama, double gaji) {
        super(id, nama, gaji);
    }

    // overriding
    @Override
    public void kerja() {
        System.out.println(getNama() + " sedang melayani kasir.");
    }

    // proses pembayaran sederhana
    public boolean prosesPembayaran(Pelanggan p, Pesanan pesanan, Restoran restoran) {
        double total = pesanan.hitungTotal();
        System.out.printf("%s membayar Rp %.0f untuk pesanan.\n", p.getNama(), total);
        if (p.getUang() >= total) {
            p.kurangiUang(total);
            restoran.tambahKeuangan(total);
            pesanan.selesaikan();
            System.out.println("Pembayaran berhasil.");
            return true;
        } else {
            System.out.println("Uang tidak cukup. Pembayaran gagal.");
            return false;
        }
    }
}
