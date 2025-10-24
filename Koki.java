// Koki.java
public class Koki extends Karyawan {

    public Koki(String id, String nama, double gaji) {
        super(id, nama, gaji);
    }

    // overriding dari Karyawan
    @Override
    public void kerja() {
        System.out.println(getNama() + " sedang memasak di dapur.");
    }

    // polymorphism: menerima Makanan abstrak
    public void siapkan(Makanan makanan) {
        System.out.println(getNama() + " menyiapkan: " + makanan.deskripsi());
    }

    // overloading: siapkan dengan jumlah
    public void siapkan(Makanan makanan, int jumlah) {
        System.out.println(getNama() + " menyiapkan: " + jumlah + "x " + makanan.deskripsi());
    }
}
