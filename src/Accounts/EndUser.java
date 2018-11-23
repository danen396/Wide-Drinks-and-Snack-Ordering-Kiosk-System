package Accounts;

import java.util.ArrayList;

public interface EndUser extends Loginable {

    public void order(String category, String itemName, ArrayList<String> customizations);
    public ArrayList<String> getAvailableProducts();
}
