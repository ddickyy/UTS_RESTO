// Pesanan.java
import java.util.ArrayList;
import java.util.List;

public class Pesanan {
    private Pelanggan pelanggan;
    private List<Makanan> daftar = new ArrayList<>();
    private boolean selesai = false;

    public Pesanan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public void tambah(Makanan m) {
        daftar.add(m);
    }

    // hitung total harga
    public double hitungTotal() {
        double total = 0;
        for (Makanan m : daftar) total += m.getHarga();
        return total;
    }

    public void selesaikan() {
        this.selesai = true;
    }

    public boolean isSelesai() {
        return selesai;
    }

    public List<Makanan> getDaftar() {
        return daftar;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pesanan untuk ").append(pelanggan.getNama()).append(":\n");
        for (Makanan m : daftar) sb.append(" - ").append(m.deskripsi()).append(" (Rp ").append((int)m.getHarga()).append(")\n");
        sb.append("Total: Rp ").append((int)hitungTotal()).append("\n");
        sb.append("Status: ").append(selesai ? "Selesai" : "Belum selesai");
        return sb.toString();
    }
}
