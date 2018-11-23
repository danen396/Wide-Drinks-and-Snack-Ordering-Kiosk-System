package Products;

import java.util.ArrayList;

public abstract class ProductFactory {
    
    private static ProductFactory productFactory;
    
    public static ProductFactory getFactory(String category)
    {
        if (category.equals(ProductCategory.Drinks.toString()))
        {
            productFactory = new DrinksFactory();
        }
        else if (category.equals(ProductCategory.Snack.toString()))
        {
            productFactory = new SnackFactory();
        }
        
        return productFactory;
    }
    
    abstract public Product getProduct(String itemName, ArrayList<String> customizations);
}
