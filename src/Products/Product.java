package Products;

import java.math.BigDecimal;

public interface Product {

    public static final String PRODUCT_TITLE = "Product";
    public static final String AVAILABILITY_TITLE = "Availability";
    public static final String PRODUCT_FILE = "src/products.txt";
    public static final String CUSTOM_SPLIT = ", ";
    
    public BigDecimal getPrice();
    public String getDescription();
}
