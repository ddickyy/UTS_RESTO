public class Koki extends Karyawan implements DapatDiNilai {
    private int ratingMemasak = 0;

    public Koki(String nama) {
        super(nama, "Memasak");
    }

    // Penerapan Overriding dari abstract method di kelas Karyawan
    @Override
    public void bekerja() {
        System.out.println("Koki " + nama + " sedang menyiapkan pesanan di dapur.");
    }

    // Metode dasar
    public void masak(Makanan makanan) {
        System.out.println(nama + " mulai memasak " + makanan.getNama() + ".");
        // Simulasi waktu memasak
        try {
            Thread.sleep(makanan.waktuMasak * 100); // Percepat simulasi
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-> " + makanan.getNama() + " selesai dimasak!");
    }

    // Penerapan Overloading: nama method sama, parameter berbeda
    public void masak(Makanan makanan, String instruksiKhusus) {
        System.out.println(nama + " mulai memasak " + makanan.getNama() + " dengan instruksi khusus: '" + instruksiKhusus + "'.");
        try {
            Thread.sleep(makanan.waktuMasak * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-> " + makanan.getNama() + " (custom) selesai dimasak!");
    }

    // Penerapan implementasi dari interface DapatDiNilai
    @Override
    public void beriRating(int rating) {
        this.ratingMemasak += rating;
        System.out.println("Koki " + nama + " mendapatkan rating. Total rating sekarang: " + this.ratingMemasak);
    }
}