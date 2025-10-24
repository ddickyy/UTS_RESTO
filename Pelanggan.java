public class Pelanggan {
    private String nama;
    private int tingkatKesabaran; // misal: 1-10

    public Pelanggan(String nama, int tingkatKesabaran) {
        this.nama = nama;
        this.tingkatKesabaran = tingkatKesabaran;
    }

    public String getNama() {
        return nama;
    }

    public void kurangiKesabaran() {
        this.tingkatKesabaran--;
    }
    
    public boolean apakahSabar() {
        return tingkatKesabaran > 0;
    }
}