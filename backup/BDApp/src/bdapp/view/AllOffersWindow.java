package bdapp.view;

import bdapp.AppWindow;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

public class AllOffersWindow extends AppPageView {

    private AddToCartDialog addToCartDialog;

    public AllOffersWindow(AppWindow parent) {
        super(parent);
        initComponents();

        addToCartDialog = new AddToCartDialog(parent, false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        offerTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        addToCartButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        offerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        offerTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(offerTable);
        offerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        backButton.setText("Wróć");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        addToCartButton.setText("Do koszyka");
        addToCartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToCartButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Oferty");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addComponent(addToCartButton))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addToCartButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        getWindow().changeView(NavigateWindow.class);
    }//GEN-LAST:event_backButtonActionPerformed

    private void addToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToCartButtonActionPerformed
        int i = offerTable.getSelectedRow();
        TableModel model = offerTable.getModel();
        int wareID = Integer.parseInt(model.getValueAt(i, 0).toString());
        addToCartDialog.show(wareID);
    }//GEN-LAST:event_addToCartButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToCartButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable offerTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refresh() {
        refreshOfferTable();
    }

    private void refreshOfferTable() {
        try {
            offerTable.setModel(getSession().getAllOffers(true));
            offerTable.getColumnModel().removeColumn(offerTable.getColumnModel().getColumn(0)); // chowa kolumne z identyfikatorami
        } catch (SQLException ex) {
            Logger.getLogger(WareManagementWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
