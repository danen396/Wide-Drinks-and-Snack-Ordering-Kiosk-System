
package Kiosks;

import Products.Product;
import Products.ProductCategory;
import Products.ProductFactory;
import java.math.BigDecimal;
import java.util.ArrayList;

public class OrderFacade {
    
    public static final String COST_TITLE = "Cost";
    public static final String ID_TITLE = "ID";
    public static final String DATE_TITLE = "Date";
    
    private Product product;
    
    public Product getProduct()
    {
        return product;
    }
    
    public BigDecimal orderProduct(String category, String itemName, ArrayList<String> customizations)
    {
        BigDecimal cost = new BigDecimal(0);
        
        if (category.equals(ProductCategory.Drinks.toString()))
        {
            product = ProductFactory.getFactory(ProductCategory.Drinks.toString()).getProduct(itemName, customizations);
        }
        else if (category.equals(ProductCategory.Snack.toString()))
        {
            product = ProductFactory.getFactory(ProductCategory.Snack.toString()).getProduct(itemName, customizations);
        }
        
        if (product != null)
        {
            cost = product.getPrice();
        }
        
        return cost;
    }
}
