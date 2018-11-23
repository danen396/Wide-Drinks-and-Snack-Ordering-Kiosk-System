package GUI;

import Accounts.KitchenEndUserProxy;
import Kiosks.OrderFacade;
import Kiosks.PrepareState;
import Kiosks.TransactionLogger;
import Products.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Kitchen extends javax.swing.JFrame {

    public Kitchen() {
        initComponents();
        updateProduct();
        updateOrder();
    }

    public static void updateProduct()
    {
        DefaultTableModel productTable = new DefaultTableModel
        (
                new Object[][] {},
                new String[] {Product.PRODUCT_TITLE, Product.AVAILABILITY_TITLE}
        )
        {
            private Class[] columns = new Class[]
            {
                String.class, String.class
            };
            
            private boolean[] editable = new boolean[]
            {
                false, false
            };

            @Override
            public Class getColumnClass(int columnIndex)
            {
                return columns[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return editable[columnIndex];
            }

        };
        
        try (Scanner sc = new Scanner(new File(Product.PRODUCT_FILE)))
        {
            while (sc.hasNext())
            {
                try
                {
                    String[] details = sc.nextLine().split(TransactionLogger.DELIMINATOR);
                    productTable.addRow(new Object[] { details[0], details[1] });
                } catch (Exception ex) {
                    Logger.getLogger(Kitchen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kitchen.class.getName()).log(Level.SEVERE, null, ex);
        }

        tblProduct.setModel(productTable);
    }

    public static void updateOrder()
    {
        DefaultTableModel orderTable = new DefaultTableModel
        (
                new Object[][] {},
                new String[] {Product.PRODUCT_TITLE, OrderFacade.COST_TITLE, OrderFacade.ID_TITLE, OrderFacade.DATE_TITLE}
        )
        {
            private Class[] columns = new Class[]
            {
                String.class, String.class, String.class, String.class
            };
            
            boolean[] editable = new boolean[]
            {
                false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return columns[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return editable[columnIndex];
            }

        };
        
        try (Scanner sc = new Scanner(new File(TransactionLogger.TRANSACTION_FILE)))
        {
            while (sc.hasNext())
            {
                try
                {
                    String[] details = sc.nextLine().split(TransactionLogger.DELIMINATOR);

                    if (details[5].equals(PrepareState.OrderInProgress.toString()))
                    {
                        orderTable.addRow(new Object[] { details[1], details[3], details[2], details[4] });
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Kitchen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kitchen.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tblOrder.setModel(orderTable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblProduct);

        jTabbedPane2.addTab("Update Product", jScrollPane2);

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblOrder);

        jTabbedPane2.addTab("FulFill Order", jScrollPane3);

        jLabel1.setText("Kitchen");

        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(414, 414, 414)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(395, 395, 395)
                        .addComponent(btnLogout)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLogout)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        if (evt.getClickCount() == 2)
        {
            int confirmMsg = JOptionPane.showConfirmDialog(null, "Are you sure that you want to update product availability?");
            
            if (confirmMsg == JOptionPane.YES_OPTION)
            {
                new KitchenEndUserProxy().switchProductAvailability(tblProduct.getValueAt(tblProduct.getSelectedRow(), 0).toString());
                updateProduct();
            }
        }
    }//GEN-LAST:event_tblProductMouseClicked

    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseClicked
        if (evt.getClickCount() == 2)
        {
            int confirmMsg = JOptionPane.showConfirmDialog(null, "Are you sure that you want to change this order to 'Prepared'?");
            
            if (confirmMsg == JOptionPane.YES_OPTION)
            {
                new KitchenEndUserProxy().prepareOrder
                (
                        tblOrder.getValueAt(tblOrder.getSelectedRow(), 0).toString(),
                        tblOrder.getValueAt(tblOrder.getSelectedRow(), 1).toString(),
                        tblOrder.getValueAt(tblOrder.getSelectedRow(), 2).toString(),
                        tblOrder.getValueAt(tblOrder.getSelectedRow(), 3).toString()
                );
                
                updateOrder();
            }
        }
    }//GEN-LAST:event_tblOrderMouseClicked

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        new UserLogin().setVisible(true);
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Kitchen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kitchen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kitchen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kitchen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Kitchen().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private static javax.swing.JTable tblOrder;
    private static javax.swing.JTable tblProduct;
    // End of variables declaration//GEN-END:variables
}