// Abstract class sebagai blueprint untuk semua jenis karyawan
public abstract class Karyawan {
    protected String nama;
    protected String skill;

    public Karyawan(String nama, String skill) {
        this.nama = nama;
        this.skill = skill;
    }
    
    public String getNama() {
        return nama;
    }

    // Metode abstract, setiap jenis karyawan punya cara kerja yang berbeda
    public abstract void bekerja();
}