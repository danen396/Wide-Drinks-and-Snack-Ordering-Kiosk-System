package Products;

import Products.Snacks.CurryPuff;
import Products.Snacks.Customizations.Cheese;
import Products.Snacks.Customizations.Lettuce;
import Products.Snacks.Customizations.Onion;
import Products.Snacks.Kuih;
import Products.Snacks.Pau;
import Products.Snacks.Sandwich;
import Products.Snacks.Snack;
import Products.Snacks.SnackCategory;
import Products.Snacks.Customizations.SnackCustomizations;
import Products.Snacks.Customizations.Tuna;
import java.util.ArrayList;

public class SnackFactory extends ProductFactory {
    
    Snack snack;
    
    public Product getProduct(String itemName, ArrayList<String> customizations)
    {
        if (itemName.equals(SnackCategory.CurryPuff.toString()))
        {
            snack = new CurryPuff();
        }
        else if (itemName.equals(SnackCategory.Kuih.toString()))
        {
            snack = new Kuih();
        }
        else if (itemName.equals(SnackCategory.Pau.toString()))
        {
            snack = new Pau();
        }
        else if (itemName.equals(SnackCategory.Sandwich.toString()))
        {
            snack = new Sandwich();
        }
        
        for (String c : customizations)
        {
            if (c.equals(SnackCustomizations.Cheese.toString()))
            {
                snack = new Cheese(snack);
            }
            else if (c.equals(SnackCustomizations.Onion.toString()))
            {
                snack = new Onion(snack);
            }
            else if (c.equals(SnackCustomizations.Lettuce.toString()))
            {
                snack = new Lettuce(snack);
            }
            else if (c.equals(SnackCustomizations.Tuna.toString()))
            {
                snack = new Tuna(snack);
            }
        }
        
        return snack;
    }
    
}
