import java.util.List;
import java.util.Scanner;

public class SimulasiGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat Datang di Game Simulasi Restoran Cepat Saji!");

        Restoran restoran = new Restoran(1000000);

        System.out.println("Merekrut karyawan awal...");
        restoran.tambahKaryawan(new Koki("Budi"));
        restoran.tambahKaryawan(new Kasir("Citra"));
        Kasir kasir = new Kasir("Citra");

        restoran.tambahMenu(new Burger());
        restoran.tambahMenu(new KentangGoreng());

        System.out.println("\nMengisi stok awal...");
        restoran.tambahStok("Roti", 10);
        restoran.tambahStok("Daging", 10);
        restoran.tambahStok("Sayur", 10);
        restoran.tambahStok("Kentang", 15);
        restoran.tambahStok("Minyak", 15);

        boolean isGameRunning = true;
        while (isGameRunning) {
            System.out.println("\n--- MENU MANAJER ---");
            System.out.println("1. Tambah Pelanggan Baru");
            System.out.println("2. Tambah Stok Bahan");
            System.out.println("3. Tampilkan Status Restoran");
            System.out.println("4. Lihat Rating Makanan"); // <-- OPSI BARU
            System.out.println("5. Keluar dari Game");      // <-- Nomor Keluar diubah
            System.out.print("Pilih aksi (1-5): ");

            int pilihan = 0;
            try {
                pilihan = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                continue;
            }

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String namaPelanggan = scanner.nextLine();
                    Pelanggan pelanggan = new Pelanggan(namaPelanggan, 10);

                    List<Makanan> menu = restoran.getMenu();
                    System.out.println("Pilih makanan untuk " + namaPelanggan + ":");
                    for (int i = 0; i < menu.size(); i++) {
                        System.out.println((i + 1) + ". " + menu.get(i).getNama());
                    }
                    System.out.print("Pilih nomor makanan: ");
                    
                    int pilihanMakanan = 0;
                    try {
                        pilihanMakanan = Integer.parseInt(scanner.nextLine());
                        if (pilihanMakanan < 1 || pilihanMakanan > menu.size()) {
                            System.out.println("Nomor makanan tidak valid.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                         System.out.println("Input tidak valid! Harap masukkan angka.");
                         continue;
                    }

                    Makanan makananDipesan = menu.get(pilihanMakanan - 1);

                    System.out.println("\n<<<<< Pelanggan Baru Datang: " + namaPelanggan + " >>>>>");
                    Pesanan pesanan = kasir.terimaPesanan(pelanggan, makananDipesan);
                    restoran.prosesPesanan(pesanan);

                    // =================== LOGIKA RATING DIMULAI DI SINI ===================
                    System.out.print("\nBerikan rating untuk " + makananDipesan.getNama() + " (1-5): ");
                    try {
                        int ratingInput = Integer.parseInt(scanner.nextLine());
                        if (ratingInput >= 1 && ratingInput <= 5) {
                            // Panggil metode tambahRating pada objek makanan yang dipesan
                            makananDipesan.tambahRating(ratingInput);
                        } else {
                            System.out.println("Rating tidak valid (harus antara 1-5). Rating tidak disimpan.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Input bukan angka. Rating tidak disimpan.");
                    }
                    // ======================================================================
                    
                    break;

                case 2:
                    System.out.print("Masukkan nama bahan yang ingin ditambah: ");
                    String bahan = scanner.nextLine();
                    System.out.print("Masukkan jumlah stok yang ditambahkan: ");
                    
                    int jumlah = 0;
                    try {
                        jumlah = Integer.parseInt(scanner.nextLine());
                        if (jumlah < 0) {
                             System.out.println("Jumlah tidak boleh negatif.");
                             continue;
                        }
                    } catch (NumberFormatException e) {
                         System.out.println("Input tidak valid! Harap masukkan angka.");
                         continue;
                    }
                    
                    restoran.tambahStok(bahan, jumlah);
                    break;

                case 3:
                    restoran.tampilkanStatus();
                    break;

                case 4: // <-- KASUS BARU UNTUK MENAMPILKAN RATING
                    restoran.tampilkanRatingMenu();
                    break;

                case 5: // <-- KASUS KELUAR DIUBAH MENJADI 5
                    isGameRunning = false;
                    break;

                default:
                    System.out.println("Pilihan tidak valid! Silakan pilih antara 1-5.");
                    break;
            }
        }

        System.out.println("\nSIMULASI SELESAI. Terima kasih telah bermain!");
        scanner.close();
    }
}