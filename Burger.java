import java.util.Arrays;
import java.util.List;

public class Burger extends Makanan {
    public Burger() {
        super("Burger", 25000, 10);
    }

    @Override
    public List<String> getBahan() {
        return Arrays.asList("Roti", "Daging", "Sayur");
    }
    
}