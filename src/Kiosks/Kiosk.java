package Kiosks;

import Accounts.BackEndUser;
import Accounts.BackEndUserProxy;
import Accounts.EndUser;
import Accounts.EndUserProxy;
import Accounts.KitchenEndUser;
import Accounts.UserCategory;
import Products.Availability;
import Products.Product;
import Products.ProductCategory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Kiosk implements EndUser, BackEndUser, KitchenEndUser {

    private OrderFacade order = new OrderFacade();
    private Observer observer = new TransactionLogger();
    
    public OrderFacade getOrder()
    {
        return order;
    }
    
    @Override
    public void order(String category, String itemName, ArrayList<String> customizations) {
        DecimalFormat DF = new DecimalFormat("0.00");
        order.orderProduct(category, itemName, customizations);
        BigDecimal price = order.getProduct().getPrice();
        
        if ((category.equals(ProductCategory.Drinks.toString())) || (category.equals(ProductCategory.Snack.toString())))
        {
            if (EndUserProxy.getBalance().compareTo(price) >= 0)
            {
                EndUserProxy.setBalance(EndUserProxy.getBalance().subtract(price));
                JOptionPane.showMessageDialog(null, "Total price: RM " + DF.format(order.getProduct().getPrice()) + ", purchase is successful!");
                observer.update(LogCategory.Transaction.toString(), order.getProduct().getDescription(), DF.format(order.getProduct().getPrice()), null);
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Insufficient balance! Please top up before ordering!");
            }
        }
    }

    @Override
    public ArrayList<String> getAvailableProducts() {
        ArrayList<String> products = new ArrayList<String>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(Product.PRODUCT_FILE)))
        {
            String line;
            
            while ((line = br.readLine()) != null)
            {
                String[] details = line.split(TransactionLogger.DELIMINATOR);
                
                if (details[1].equals(Availability.Available.toString()))
                {
                    products.add(details[0]);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Kiosk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return products;
    }

    @Override
    public UserCategory login(String id, String password) {
        try (Scanner sc = new Scanner(new File(TransactionLogger.USER_FILE)))
        {
            while (sc.hasNextLine())
            {
                String[] details = sc.nextLine().split(TransactionLogger.DELIMINATOR);
                
                if (details[0].equals(UserCategory.EndUser.toString()))
                {
                    if ((id.equals(details[1])) && (password.equals(details[2])))
                    {
                        EndUserProxy.setUsername(id);
                        EndUserProxy.setBalance(BigDecimal.valueOf(Double.parseDouble(details[3])));
                        return UserCategory.EndUser;
                    }
                }
                else if (details[0].equals(UserCategory.BackEndUser.toString()))
                {
                    if ((id.equals(details[1])) && (password.equals(details[2])))
                    {
                        BackEndUserProxy.setUsername(id);
                        return UserCategory.BackEndUser;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kiosk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return UserCategory.None;
    }

    @Override
    public void switchProductAvailability(String itemName) {
        String itemLine = "";
        
        try (Scanner sc = new Scanner(new File(Product.PRODUCT_FILE)))
        {
            while (sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] details = line.split(TransactionLogger.DELIMINATOR);
                
                if (details[0].equals(itemName))
                {
                    String status = (details[1].equals(Availability.Available.toString())) ? Availability.Unavailable.toString() : Availability.Available.toString();
                    itemLine += details[0] + TransactionLogger.DELIMINATOR + status + System.lineSeparator();
                }
                else
                {
                    itemLine += line + System.lineSeparator();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kiosk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(Product.PRODUCT_FILE)))
        {
            bw.write(itemLine);
            JOptionPane.showMessageDialog(null, "Product updated successfully!");
        } catch (IOException ex) {
            Logger.getLogger(Kiosk.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Product failed to update! Please try again!");
        }
    }

    @Override
    public void prepareOrder(String username, String product, String cost, String date) {
        String text = "";
        
        try (Scanner sc = new Scanner(new File(TransactionLogger.TRANSACTION_FILE)))
        {
            while (sc.hasNextLine())
            {
                String line = sc.nextLine();
                String[] details = line.split(TransactionLogger.DELIMINATOR);
                
                if ((details[1].equals(product)) && (details[2].equals(username)) && ((details[3].equals(cost))) && ((details[1].equals(date))))
                {
                    text += details[0] + TransactionLogger.DELIMINATOR + details[1] + TransactionLogger.DELIMINATOR + details[2] + TransactionLogger.DELIMINATOR + details[3] + TransactionLogger.DELIMINATOR + details[4] + TransactionLogger.DELIMINATOR + PrepareState.Prepared.toString() + System.lineSeparator();
                }
                else
                {
                    text += line + System.lineSeparator();
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kiosk.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TransactionLogger.TRANSACTION_FILE)))
        {
            bw.write(text);
            JOptionPane.showMessageDialog(null, "Order successfully updated!");
        } catch (IOException ex) {
            Logger.getLogger(Kiosk.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Order failed to update! Please try again!");
        }
    }
    
}
