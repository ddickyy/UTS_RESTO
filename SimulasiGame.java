
// SimulasiGame.java
import java.util.Scanner;

public class SimulasiGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Restoran r = new Restoran("Restoran Kita", 1_000_000);
        // tambahkan menu beserta stok awal
        Burger burgerReg = new Burger("Burger Sederhana", 25000);
        Burger burgerKeju = new Burger("Burger Keju", 30000, true);
        KentangGoreng kentang = new KentangGoreng("Normal", 12000);
        r.tambahMenu(burgerReg, 5);
        r.tambahMenu(burgerKeju, 5);
        r.tambahMenu(kentang, 8);

        // tambah staf
        r.tambahKaryawan(new Koki("K1", "Andi (Koki)", 3_000_000));
        r.tambahKaryawan(new Kasir("C1", "Siti (Kasir)", 2_500_000));

        // pelanggan
        System.out.print("Masukkan nama pelanggan: ");
        String namaPelanggan = sc.nextLine();
        Pelanggan pelanggan = new Pelanggan(namaPelanggan, 100_000);
        Pesanan pesanan = new Pesanan(pelanggan);

        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Lihat Menu");
            System.out.println("2. Tambah Pesanan");
            System.out.println("3. Lihat Pesanan");
            System.out.println("4. Proses Pembayaran");
            System.out.println("5. Tambah Stok");
            System.out.println("6. Beri Rating");
            System.out.println("7. Lihat Stok");
            System.out.println("8. Keluar");
            System.out.print("Pilih: ");
            int pilih = sc.nextInt();
            sc.nextLine();

            switch (pilih) {
                case 1:
                    int i = 1;
                    System.out.println("\n=== MENU MAKANAN ===");
                    for (Makanan m : r.getMenu()) {
                        System.out.printf("%d. %s | Stok: %d\n", i++, m, r.getStok(m));
                    }
                    break;

                case 2:
                    System.out.println("\nPilih nomor menu yang ingin dipesan:");
                    int no = sc.nextInt();
                    sc.nextLine();
                    if (no < 1 || no > r.getMenu().size()) {
                        System.out.println("Nomor tidak valid.");
                        break;
                    }
                    Makanan dipilih = r.getMenu().get(no - 1);
                    if (r.getStok(dipilih) <= 0) {
                        System.out.println("Stok habis!");
                    } else {
                        pesanan.tambah(dipilih);
                        System.out.println(dipilih.getNama() + " ditambahkan ke pesanan.");
                    }
                    break;

                case 3:
                    System.out.println(pesanan);
                    break;

                case 4:
                    r.prosesPesanan(pesanan);
                    break;

                case 5:
                    System.out.println("\n=== TAMBAH STOK MENU ===");
                    int j = 1;
                    for (Makanan m : r.getMenu()) {
                        System.out.printf("%d. %s (stok: %d)\n", j++, m.getNama(), r.getStok(m));
                    }
                    System.out.print("Pilih menu untuk ditambah stok: ");
                    int pilihMenu = sc.nextInt();
                    System.out.print("Jumlah stok yang ingin ditambah: ");
                    int jumlah = sc.nextInt();
                    sc.nextLine();
                    if (pilihMenu >= 1 && pilihMenu <= r.getMenu().size()) {
                        Makanan m = r.getMenu().get(pilihMenu - 1);
                        r.tambahStok(m, jumlah);
                        System.out.println("Stok " + m.getNama() + " bertambah " + jumlah);
                    }
                    break;

                case 6:
                    System.out.println("\n=== BERIKAN RATING ===");
                    int k = 1;
                    for (Makanan m : r.getMenu()) {
                        System.out.printf("%d. %s (rating: %.2f)\n", k++, m.getNama(), m.getRataRataRating());
                    }
                    System.out.print("Pilih menu yang ingin diberi rating: ");
                    int menuRate = sc.nextInt();
                    System.out.print("Masukkan rating (1-5): ");
                    int nilai = sc.nextInt();
                    sc.nextLine();
                    if (menuRate >= 1 && menuRate <= r.getMenu().size()) {
                        Makanan m = r.getMenu().get(menuRate - 1);
                        double rata = m.beriRating(nilai);
                        System.out.printf("Terima kasih! Rata-rata rating %s kini: %.2f\n", m.getNama(), rata);
                    }
                    break;

                case 7:
                    r.tampilkanStok();
                    break;

                case 8:
                    System.out.println("Keluar dari program...");
                    running = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
