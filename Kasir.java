public class Kasir extends Karyawan {
    public Kasir(String nama) {
        super(nama, "Melayani Pelanggan");
    }

    // Penerapan Overriding
    @Override
    public void bekerja() {
        System.out.println("Kasir " + nama + " siap di konter untuk melayani pelanggan.");
    }

    public Pesanan terimaPesanan(Pelanggan pelanggan, Makanan makanan) {
        System.out.println(nama + " menerima pesanan dari " + pelanggan.getNama() + ": 1 " + makanan.getNama());
        return new Pesanan(pelanggan, makanan);
    }
}