package Accounts;

import Kiosks.Kiosk;

public class BackEndUserProxy implements BackEndUser {
    
    private static String username;
    Kiosk kiosk = new Kiosk();
    
    public static String getUsername()
    {
        return username;
    }
    
    public static void setUsername(String username)
    {
        BackEndUserProxy.username = username;
    }

    @Override
    public UserCategory login(String id, String password) {
        return kiosk.login(username, username);
    }
    
}
