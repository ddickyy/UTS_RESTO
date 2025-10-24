public class Pesanan {
    private Pelanggan pelanggan;
    private Makanan makanan;
    private boolean selesai = false;

    public Pesanan(Pelanggan pelanggan, Makanan makanan) {
        this.pelanggan = pelanggan;
        this.makanan = makanan;
    }
    
    public Makanan getMakanan() {
        return makanan;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void selesaikan() {
        this.selesai = true;
    }
}