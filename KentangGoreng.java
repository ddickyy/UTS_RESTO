// KentangGoreng.java
public class KentangGoreng extends Makanan {
    private String ukuran; // kecil/normal/besar

    public KentangGoreng(String ukuran, double harga) {
        super("Kentang Goreng (" + ukuran + ")", harga);
        this.ukuran = ukuran;
    }

    @Override
    public String deskripsi() {
        return getNama() + " ukuran " + ukuran;
    }
}
