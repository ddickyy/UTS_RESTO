import java.util.Arrays;
import java.util.List;

public class KentangGoreng extends Makanan {
    public KentangGoreng() {
        // super(nama, hargaJual, waktuMasak, biayaProduksi)
        super("Kentang Goreng", 15000, 5, 6000); // Misal biaya modal kentang 6rb
    }

    @Override
    public List<String> getBahan() {
        return Arrays.asList("Kentang", "Minyak");
    }
}