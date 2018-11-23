package Accounts;

public interface KitchenEndUser {
    public void switchProductAvailability(String itemName);
    public void prepareOrder(String username, String product, String cost, String date);
}
