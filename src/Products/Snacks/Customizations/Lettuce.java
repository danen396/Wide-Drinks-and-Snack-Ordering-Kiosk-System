package Products.Snacks.Customizations;

import Products.Snacks.Snack;
import java.math.BigDecimal;

public class Lettuce extends SnackDecorator {
    
    private BigDecimal price = new BigDecimal(0.2);
    private String description = "Lettuce";
    
    public Lettuce(Snack snack) {
        super(snack);
    }
    
    @Override
    public BigDecimal getPrice()
    {
        return snack.getPrice().add(price);
    }
    
    @Override
    public String getDescription()
    {
        return snack.getDescription() + CUSTOM_SPLIT + description;
    }
    
}
