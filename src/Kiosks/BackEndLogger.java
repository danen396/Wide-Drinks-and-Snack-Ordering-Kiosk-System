package Kiosks;

import Accounts.BackEndUserProxy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class BackEndLogger implements Logger {

    private static final String BACKEND_FILE = "src/backend.txt";
    private static final String DATE_FORMAT = "dd/MM/yy HH:mm:ss";
    
    Logger nextLog;
    
    @Override
    public void log(String category, String itemName, String cost, String state) {
        if (category.equals(LogCategory.BackEnd))
        {
            Date today = new Date();
            SimpleDateFormat DF = new SimpleDateFormat(DATE_FORMAT);
            int i = 1;
            
            try (BufferedReader br = new BufferedReader(new FileReader(BACKEND_FILE)))
            {
                for (; br.readLine() != null; i++) { }
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(TransactionLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String line = i + TransactionLogger.DELIMINATOR + BackEndUserProxy.getUsername() + TransactionLogger.DELIMINATOR + DF.format(today) + TransactionLogger.DELIMINATOR + state + System.lineSeparator();
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BACKEND_FILE, true)))
            {
                bw.write(line);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(BackEndLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void setNextLog(Logger logger) {
        nextLog = logger;
    }
    
}
