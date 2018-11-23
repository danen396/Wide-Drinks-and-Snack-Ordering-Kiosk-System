package Products.Snacks;

import java.math.BigDecimal;

public class Sandwich extends Snack {

    private BigDecimal price = new BigDecimal(1);
    private String description = "Sandwich";
    
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
}
