package Products.Snacks.Customizations;

import Products.Snacks.Snack;
import java.math.BigDecimal;

public abstract class SnackDecorator extends Snack {
    
    Snack snack;
    
    public SnackDecorator(Snack snack)
    {
        this.snack = snack;
    }

    @Override
    public BigDecimal getPrice() {
        return snack.getPrice();
    }

    @Override
    public String getDescription() {
        return snack.getDescription();
    }
    
}
