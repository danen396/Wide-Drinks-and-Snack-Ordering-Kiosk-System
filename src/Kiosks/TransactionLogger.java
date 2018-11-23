package Kiosks;

import Accounts.EndUserProxy;
import Accounts.UserCategory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class TransactionLogger implements Logger, Observer {

    public static final String TRANSACTION_FILE = "src/transactions.txt";
    public static final String USER_FILE = "src/users.txt";
    public static final String DELIMINATOR = "##";
    
    private static final String DATE_FORMAT = "dd/MM/yy HH:mm:ss";
    private Logger nextLog;
    
    @Override
    public void log(String category, String itemName, String cost, String state) {
        if (category.equals(LogCategory.Transaction.toString()))
        {
            Date today = new Date();
            SimpleDateFormat DF = new SimpleDateFormat(DATE_FORMAT);
            int i = 1;
            
            try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTION_FILE)))
            {
                for (; br.readLine() != null; i++) { }
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(TransactionLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String text = i + DELIMINATOR + itemName + DELIMINATOR + EndUserProxy.getUsername() + DELIMINATOR + cost + DELIMINATOR + DF.format(today) + DELIMINATOR + PrepareState.OrderInProgress + System.lineSeparator();
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true)))
            {
                bw.write(text);
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(TransactionLogger.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            text = "";
            
            try (Scanner sc = new Scanner(new File(USER_FILE)))
            {
                while (sc.hasNextLine())
                {
                    String line = sc.nextLine();
                    String[] details = line.split(DELIMINATOR);
                    
                    if (details[1].equals(EndUserProxy.getUsername()))
                    {
                        DecimalFormat df = new DecimalFormat("0.00");
                        text += UserCategory.EndUser.toString() + DELIMINATOR + EndUserProxy.getUsername() + DELIMINATOR + details[2] + DELIMINATOR + df.format(EndUserProxy.getBalance()) + System.lineSeparator();
                    }
                    else
                    {
                        text += line + System.lineSeparator();
                    }
                }
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(TransactionLogger.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "An error has occurred! Please try again!");
            }
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE)))
            {
                bw.write(text);
                JOptionPane.showMessageDialog(null, "Your balance has been successfully updated!");
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(TransactionLogger.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "An error has occurred! Please try again!");
            }
        }
        else
        {
            setNextLog(new BackEndLogger());
            nextLog.log(category, null, null, state);
        }
    }

    @Override
    public void setNextLog(Logger logger) {
        nextLog = logger;
    }

    @Override
    public void update(String category, String itemName, String cost, String state) {
        log(category, itemName, cost, state);
    }
    
}
