// SimulasiGame.java
import java.util.Scanner;

public class SimulasiGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inisialisasi restoran
        Restoran r = new Restoran("Restoran Kita", 1_000_000);
        r.tambahMenu(new Burger("Burger Sederhana", 25000));
        r.tambahMenu(new Burger("Burger Keju", 30000, true));
        r.tambahMenu(new KentangGoreng("Normal", 12000));

        Koki koki = new Koki("K1", "Andi (Koki)", 3_000_000);
        Kasir kasir = new Kasir("C1", "Siti (Kasir)", 2_500_000);
        r.tambahKaryawan(koki);
        r.tambahKaryawan(kasir);

        // Buat pelanggan
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = sc.nextLine();
        Pelanggan pelanggan = new Pelanggan(namaPelanggan, 100_000);

        Pesanan pesanan = new Pesanan(pelanggan);
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Lihat Menu Makanan");
            System.out.println("2. Tambah Pesanan");
            System.out.println("3. Lihat Pesanan");
            System.out.println("4. Proses Pembayaran");
            System.out.println("5. Keluar");
            System.out.print("Pilih (1-5): ");
            int pilih = sc.nextInt();
            sc.nextLine(); // buang newline

            switch (pilih) {
                case 1:
                    System.out.println("\n=== DAFTAR MENU ===");
                    int i = 1;
                    for (Makanan m : r.getMenu()) {
                        System.out.println(i++ + ". " + m);
                    }
                    break;

                case 2:
                    System.out.println("\nPilih nomor menu yang ingin dipesan:");
                    int no = sc.nextInt();
                    sc.nextLine();
                    if (no < 1 || no > r.getMenu().size()) {
                        System.out.println("Nomor menu tidak valid.");
                    } else {
                        Makanan m = r.getMenu().get(no - 1);
                        pesanan.tambah(m);
                        System.out.println(m.getNama() + " telah ditambahkan ke pesanan.");
                    }
                    break;

                case 3:
                    System.out.println("\n=== PESANAN SAAT INI ===");
                    System.out.println(pesanan);
                    break;

                case 4:
                    System.out.println("\n=== PEMBAYARAN ===");
                    if (pesanan.getDaftar().isEmpty()) {
                        System.out.println("Belum ada item dalam pesanan.");
                    } else {
                        r.prosesPesanan(pesanan);
                    }
                    break;

                case 5:
                    System.out.println("Terima kasih telah berkunjung!");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid, coba lagi.");
                    break;
            }
        }
    }
}
