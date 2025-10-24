// Burger.java
public class Burger extends Makanan {
    private boolean extraKeju;

    // overloading constructor (contoh overloading)
    public Burger(String nama, double harga) {
        this(nama, harga, false);
    }

    public Burger(String nama, double harga, boolean extraKeju) {
        super(nama, harga + (extraKeju ? 5000 : 0));
        this.extraKeju = extraKeju;
    }

    @Override
    public String deskripsi() {
        return getNama() + (extraKeju ? " dengan ekstra keju" : "");
    }

    // contoh method overloading: membuat string untuk pesanan
    public String buat(int jumlah) {
        return "Membuat " + jumlah + "x " + deskripsi();
    }
}
