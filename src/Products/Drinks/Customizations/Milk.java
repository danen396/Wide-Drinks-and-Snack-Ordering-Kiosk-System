package Products.Drinks.Customizations;

import Products.Amount;
import Products.Drinks.Drinks;
import java.math.BigDecimal;

public class Milk extends DrinksDecorator {
    
    private BigDecimal price = new BigDecimal(0.1);
    private String description = "Milk";
    private Amount amount = Amount.Regular;
    
    public Milk(Drinks drinks, Amount amount) {
        super(drinks);
        this.amount = amount;
    }
    
    @Override
    public BigDecimal getPrice()
    {
        return drinks.getPrice().add(price.multiply(new BigDecimal(amount.ordinal() + 1)));
    }
    
    @Override
    public String getDescription()
    {
        return drinks.getDescription() + CUSTOM_SPLIT + amount.toString() + " " + description;
    }
    
}
