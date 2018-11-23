package Accounts;

import Kiosks.Kiosk;
import java.math.BigDecimal;
import java.util.ArrayList;

public class EndUserProxy implements EndUser {

    private static String username;
    private static BigDecimal balance;
    Kiosk kiosk = new Kiosk();
    
    public static String getUsername()
    {
        return username;
    }
    
    public static BigDecimal getBalance()
    {
        return balance;
    }
    
    public static void setUsername(String username)
    {
        EndUserProxy.username = username;
    }
    
    public static void setBalance(BigDecimal balance)
    {
        EndUserProxy.balance = balance;
    }
    
    @Override
    public void order(String category, String itemName, ArrayList<String> customizations) {
        kiosk.order(category, itemName, customizations);
    }

    @Override
    public ArrayList<String> getAvailableProducts() {
        return kiosk.getAvailableProducts();
    }

    @Override
    public UserCategory login(String id, String password) {
        return kiosk.login(id, password);
    }
    
}
