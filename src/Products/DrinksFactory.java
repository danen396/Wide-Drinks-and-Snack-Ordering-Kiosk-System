package Products;

import Products.Drinks.Coffee;
import Products.Drinks.Customizations.Cream;
import Products.Drinks.Customizations.DrinkSize;
import Products.Drinks.Drinks;
import Products.Drinks.Customizations.DrinksCustomizations;
import Products.Drinks.Customizations.Ice;
import Products.Drinks.Customizations.Milk;
import Products.Drinks.Customizations.Sprinkle;
import Products.Drinks.Customizations.Sugar;
import Products.Drinks.DrinksCategory;
import java.util.ArrayList;

public class DrinksFactory extends ProductFactory {
    private Drinks drinks;
    
    public Product getProduct(String itemName, ArrayList<String> customizations)
    {
        if (itemName.equals(DrinksCategory.Coffee.toString()))
        {
            drinks = new Coffee();
        }
        
        for (String c : customizations)
        {
            if (c.equals(DrinksCustomizations.Cream.toString()))
            {
                drinks = new Cream(drinks);
            }
            else if (c.equals(DrinksCustomizations.Sprinkle.toString()))
            {
                drinks = new Sprinkle(drinks);
            }
            else if (c.equals(DrinksCustomizations.LessIce.toString()))
            {
                drinks = new Ice(drinks, Amount.Less);
            }
            else if (c.equals(DrinksCustomizations.RegularIce.toString()))
            {
                drinks = new Ice(drinks, Amount.Regular);
            }
            else if (c.equals(DrinksCustomizations.MoreIce.toString()))
            {
                drinks = new Ice(drinks, Amount.More);
            }
            else if (c.equals(DrinksCustomizations.LessIce.toString()))
            {
                drinks = new Milk(drinks, Amount.Less);
            }
            else if (c.equals(DrinksCustomizations.RegularMilk.toString()))
            {
                drinks = new Milk(drinks, Amount.Regular);
            }
            else if (c.equals(DrinksCustomizations.MoreMilk.toString()))
            {
                drinks = new Milk(drinks, Amount.More);
            }
            else if (c.equals(DrinksCustomizations.LessSugar.toString()))
            {
                drinks = new Sugar(drinks, Amount.Less);
            }
            else if (c.equals(DrinksCustomizations.RegularSugar.toString()))
            {
                drinks = new Sugar(drinks, Amount.Regular);
            }
            else if (c.equals(DrinksCustomizations.MoreSugar.toString()))
            {
                drinks = new Sugar(drinks, Amount.More);
            }
            else if (c.equals(DrinksCustomizations.Regular.toString()))
            {
                drinks = new DrinkSize(drinks, Size.Regular);
            }
            else if (c.equals(DrinksCustomizations.Medium.toString()))
            {
                drinks = new DrinkSize(drinks, Size.Medium);
            }
            else if (c.equals(DrinksCustomizations.Large.toString()))
            {
                drinks = new DrinkSize(drinks, Size.Large);
            }
        }
        
        return drinks;
    }
}
