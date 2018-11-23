package Products.Drinks.Customizations;

import Products.Drinks.Drinks;
import java.math.BigDecimal;

public class DrinksDecorator extends Drinks {

    protected Drinks drinks;
    
    public DrinksDecorator(Drinks drinks)
    {
        this.drinks = drinks;
    }
    
    @Override
    public BigDecimal getPrice() {
        return drinks.getPrice();
    }

    @Override
    public String getDescription() {
        return drinks.getDescription();
    }
    
}
