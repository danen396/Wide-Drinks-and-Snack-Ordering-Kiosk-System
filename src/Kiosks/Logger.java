package Kiosks;

public interface Logger {
    
    public void log(String category, String itemName, String cost, String state);
    public void setNextLog(Logger logger);
    
}
