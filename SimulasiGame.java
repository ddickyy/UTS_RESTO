import java.util.Random;
import java.util.Scanner;

public class SimulasiGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        // --- SETUP AWAL RESTORAN ---
        Restoran r = new Restoran("Restoran Kita", 1_000_000);
        Burger burgerReg = new Burger("Burger Sederhana", 25000);
        Burger burgerKeju = new Burger("Burger Keju", 30000, true);
        KentangGoreng kentang = new KentangGoreng("Normal", 12000);
        r.tambahMenu(burgerReg, 5);
        r.tambahMenu(burgerKeju, 5);
        r.tambahMenu(kentang, 8);
        r.tambahKaryawan(new Koki("K1", "Andi (Koki)", 3_000_000));
        r.tambahKaryawan(new Kasir("C1", "Siti (Kasir)", 2_500_000));

        // --- GAME LOOP UTAMA (MENU MANAJER) ---
        int hari = 1;
        boolean running = true;
        while (running) {
            System.out.println("\n========================================");
            System.out.println("||       MENU MANAJER - HARI KE-" + hari + "      ||");
            System.out.println("========================================");
            // r.(); // Tampilkan status di awal
            System.out.println("\n--- AKSI TERSEDIA ---");
            System.out.println("1. Tunggu Pelanggan Berikutnya (Mulai Simulasi)");
            System.out.println("2. Tambah Stok");
            System.out.println("3. Lihat Menu Restoran");
            System.out.println("4. Beri Rating Makanan");
            System.out.println("5. Keluar dari Game");
            System.out.print("Pilih Aksi Manajer: ");
            int pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {
                case 1:
                    // --- FASE SIMULASI: KEDATANGAN PELANGGAN ---
                    System.out.println("\nAnda membuka pintu restoran, menunggu pelanggan...");
                    
                    // Peluang 75% pelanggan akan datang
                    if (random.nextInt(100) < 75) {
                        System.out.println("Seorang pelanggan baru telah datang!");
                        
                        String namaPelanggan = "Pelanggan Hari ke-" + hari;
                        Pelanggan pelanggan = new Pelanggan(namaPelanggan, 100_000);
                        Pesanan pesanan = new Pesanan(pelanggan);
                        boolean sesiPelanggan = true;

                        // Loop khusus untuk sesi pemesanan pelanggan ini
                        while (sesiPelanggan) {
                            System.out.println("\n--- Sesi Pelanggan: " + namaPelanggan + " ---");
                            System.out.println("1. Tambah Pesanan");
                            System.out.println("2. Lihat Pesanan Saat Ini");
                            System.out.println("3. Lakukan Pembayaran");
                            System.out.print("Pilih: ");
                            int pilihAksi = sc.nextInt();
                            sc.nextLine();

                            switch (pilihAksi) {
                                case 1:
                                    r.getMenu();
                                    r.tampilkanMakan();
                                    System.out.print("Pilih nomor menu yang ingin dipesan: ");
                                    int no = sc.nextInt();
                                    sc.nextLine();
                                    if (no >= 1 && no <= r.getMenu().size()) {
                                        Makanan dipilih = r.getMenu().get(no - 1);
                                        if (r.getStok(dipilih) > 0) {
                                            pesanan.tambah(dipilih);
                                            System.out.println("-> " + dipilih.getNama() + " ditambahkan.");
                                        } else {
                                            System.out.println("Stok habis!");
                                        }
                                    } else {
                                        System.out.println("Nomor tidak valid.");
                                    }
                                    break;
                                case 2:
                                    System.out.println(pesanan);
                                    break;
                                case 3:
                                    r.prosesPesanan(pesanan);
                                    sesiPelanggan = false; // Sesi pelanggan ini selesai
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid.");
                            }
                        }
                    } else {
                        System.out.println("Restoran sepi, tidak ada pelanggan yang datang saat ini.");
                    }
                    hari++; // Hari bertambah hanya setelah Anda mencoba menunggu pelanggan
                    break;

                case 2:
                    r.tampilkanStok();
                    System.out.print("Pilih menu untuk ditambah stok: ");
                    int pilihMenu = sc.nextInt();
                    System.out.print("Jumlah stok yang ingin ditambah: ");
                    int jumlah = sc.nextInt();
                    sc.nextLine();
                    if (pilihMenu >= 1 && pilihMenu <= r.getMenu().size()) {
                        Makanan m = r.getMenu().get(pilihMenu - 1);
                        r.tambahStok(m, jumlah);
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                    break;
                case 3:
                    r.tampilkanStok();
                    break;
                case 4:
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
                case 5:
                    System.out.println("Keluar dari program...");
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
        sc.close();
    }
}