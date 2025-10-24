// Karyawan.java
public abstract class Karyawan {
    private String nama;
    private String id;
    private double gaji;

    public Karyawan(String id, String nama, double gaji) {
        this.id = id;
        this.nama = nama;
        this.gaji = gaji;
    }

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public double getGaji() {
        return gaji;
    }

    // setiap karyawan punya tugas kerja yang spesifik
    public abstract void kerja();

    @Override
    public String toString() {
        return String.format("%s (%s) - gaji: Rp %.0f", nama, id, gaji);
    }
}
