package Products.Drinks.Customizations;

import Products.Drinks.Drinks;
import Products.Size;
import java.math.BigDecimal;

public class DrinkSize extends DrinksDecorator {
    
    private BigDecimal price = new BigDecimal(0.5);
    private String description = "Size";
    private Size size = Size.Regular;
    
    public DrinkSize(Drinks drinks, Size size) {
        super(drinks);
        this.size = size;
    }
    
    @Override
    public BigDecimal getPrice()
    {
        return drinks.getPrice().add(price.multiply(new BigDecimal(size.ordinal() + 1)));
    }
    
    @Override
    public String getDescription()
    {
        return drinks.getDescription() + CUSTOM_SPLIT + size.toString() + " " + description;
    }
}
