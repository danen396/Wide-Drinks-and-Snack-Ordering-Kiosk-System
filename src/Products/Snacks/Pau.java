package Products.Snacks;

import java.math.BigDecimal;

public class Pau extends Snack {

    private BigDecimal price = new BigDecimal(1);
    private String description = "Pau";
    
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
}
