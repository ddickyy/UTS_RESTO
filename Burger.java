import java.util.Arrays;
import java.util.List;

public class Burger extends Makanan {
    public Burger() {
        // super(nama, hargaJual, waktuMasak, biayaProduksi)
        super("Burger", 25000, 10, 12000); // Misal biaya modal burger 12rb
    }

    @Override
    public List<String> getBahan() {
        return Arrays.asList("Roti", "Daging", "Sayur");
    }
}