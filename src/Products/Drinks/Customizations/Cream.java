package Products.Drinks.Customizations;

import Products.Drinks.Drinks;
import java.math.BigDecimal;

public class Cream extends DrinksDecorator {
    
    private BigDecimal price = new BigDecimal(0.1);
    private String description = "Cream";
    
    public Cream(Drinks drinks)
    {
        super(drinks);
    }
    
    @Override
    public BigDecimal getPrice()
    {
        return drinks.getPrice().add(price);
    }
    
    @Override
    public String getDescription()
    {
        return drinks.getDescription() + CUSTOM_SPLIT + description;
    }
    
}
