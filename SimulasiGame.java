import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class SimulasiGame {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("Selamat Datang di Game Simulasi Restoran Cepat Saji!");

        // Setup awal
        Restoran restoran = new Restoran(500000); // Modal awal lebih realistis
        restoran.tambahMenu(new Burger());
        restoran.tambahMenu(new KentangGoreng());
        restoran.tambahKaryawan(new Koki("Budi"));
        Kasir kasir = new Kasir("Citra");
        
        int hari = 1;
        boolean isGameRunning = true;

        while (isGameRunning) {
            System.out.println("\n--- HARI KE-" + hari + " ---");
            restoran.tampilkanStatus();
            System.out.println("--- MENU MANAJER ---");
            System.out.println("1. Mulai Hari Berikutnya (Simulasi)");
            System.out.println("2. Tambah Stok Bahan");
            System.out.println("3. Lihat Rating Makanan");
            System.out.println("4. Keluar dari Game");
            System.out.print("Pilih aksi (1-4): ");

            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid!");
                continue;
            }

            switch (pilihan) {
                case 1:
                    // === SIMULASI SATU HARI DIMULAI ===
                    System.out.println("\nRestoran dibuka untuk hari ke-" + hari + "...");
                    
                    // Jumlah pelanggan potensial bergantung pada reputasi
                    // Reputasi 100 -> maks 10 pelanggan, Reputasi 50 -> maks 5 pelanggan
                    int maksPelanggan = restoran.getReputasi() / 10;
                    int jumlahPelangganDatang = random.nextInt(maksPelanggan + 1);

                    System.out.println("Berdasarkan reputasi, " + jumlahPelangganDatang + " pelanggan akan datang hari ini.");
                    Thread.sleep(2000);

                    if (jumlahPelangganDatang == 0) {
                        System.out.println("Sayang sekali, tidak ada pelanggan yang datang hari ini.");
                    }

                    for (int i = 0; i < jumlahPelangganDatang; i++) {
                        System.out.println("\n<<<<< Pelanggan ke-" + (i + 1) + " Datang >>>>>");
                        Pelanggan pelanggan = new Pelanggan("Pelanggan " + (i + 1), 5); // Kesabaran awal
                        
                        List<Makanan> menu = restoran.getMenu();
                        Makanan makananDipesan = menu.get(random.nextInt(menu.size()));

                        Pesanan pesanan = kasir.terimaPesanan(pelanggan, makananDipesan);
                        restoran.prosesPesanan(pesanan);
                        Thread.sleep(1000);
                    }
                    
                    hari++; // Lanjut ke hari berikutnya
                    break;

                case 2:
                    System.out.print("Masukkan nama bahan (Roti, Daging, Sayur, Kentang, Minyak): ");
                    String bahan = scanner.nextLine();
                    System.out.print("Masukkan jumlah: ");
                    int jumlah = Integer.parseInt(scanner.nextLine());
                    restoran.tambahStok(bahan, jumlah);
                    break;

                case 3:
                    restoran.tampilkanRatingMenu();
                    break;

                case 4:
                    isGameRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
        System.out.println("\nSIMULASI SELESAI. Terima kasih telah bermain!");
        scanner.close();
    }
}