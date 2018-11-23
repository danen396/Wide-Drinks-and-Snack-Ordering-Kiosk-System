package Accounts;

import Kiosks.Kiosk;

public class KitchenEndUserProxy implements KitchenEndUser {

    Kiosk kiosk = new Kiosk();
    
    @Override
    public void switchProductAvailability(String product) {
        kiosk.switchProductAvailability(product);
    }

    @Override
    public void prepareOrder(String username, String product, String cost, String date) {
        kiosk.prepareOrder(username, product, cost, date);
    }
    
}
