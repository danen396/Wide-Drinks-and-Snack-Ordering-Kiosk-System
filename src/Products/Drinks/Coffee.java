package Products.Drinks;

import java.math.BigDecimal;

public class Coffee extends Drinks {

    private BigDecimal price = new BigDecimal(1);
    private String description = "Coffee";
    
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
}
