import java.util.Arrays;
import java.util.List;

public class KentangGoreng extends Makanan {
    public KentangGoreng() {
        super("Kentang Goreng", 15000, 5);
    }

    @Override
    public List<String> getBahan() {
        return Arrays.asList("Kentang", "Minyak");
    }
}