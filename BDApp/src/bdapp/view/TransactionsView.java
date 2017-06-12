package bdapp.view;

import bdapp.AppWindow;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

public class TransactionsView extends AppPageView {
    
    private TransactionDetailsDialog trDialog;

    public TransactionsView(AppWindow parent) {
        super(parent);
        trDialog = new TransactionDetailsDialog(parent, true);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        waitToConfTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        toConfirmTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        confirmButton = new javax.swing.JToggleButton();
        detailsRButton = new javax.swing.JButton();
        detailsLButton = new javax.swing.JButton();

        waitToConfTable.setModel(new javax.swing.table.DefaultTableModel(
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
        waitToConfTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(waitToConfTable);

        toConfirmTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(toConfirmTable);

        jLabel1.setText("Oczekujesz na zatwierdzenie:");

        jLabel2.setText("Do zatwierdzenia:");

        backButton.setText("Wróć");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        confirmButton.setText("Zatwierdź");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        detailsRButton.setText("Pokaż szczegóły");
        detailsRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsRButtonActionPerformed(evt);
            }
        });

        detailsLButton.setText("Pokaż szczegóły");
        detailsLButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsLButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(detailsLButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(confirmButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                    .addComponent(jLabel1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(detailsRButton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmButton)
                    .addComponent(detailsRButton)
                    .addComponent(detailsLButton))
                .addGap(12, 12, 12)
                .addComponent(backButton)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        getWindow().changeView(NavigateWindow.class);
    }//GEN-LAST:event_backButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        int i = toConfirmTable.getSelectedRow();
        TableModel model = toConfirmTable.getModel();
        int trID = Integer.parseInt(model.getValueAt(i, 0).toString());
        if (!getSession().confirmTransaction(trID)) {
            JOptionPane.showMessageDialog(this, "Błąd aktualizacji stanu transakcji");
        } else {
            refresh();
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void detailsLButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsLButtonActionPerformed
        int i = toConfirmTable.getSelectedRow();
        TableModel model = toConfirmTable.getModel();
        int trID = Integer.parseInt(model.getValueAt(i, 0).toString());
        trDialog.show(trID);
    }//GEN-LAST:event_detailsLButtonActionPerformed

    private void detailsRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsRButtonActionPerformed
        int i = waitToConfTable.getSelectedRow();
        TableModel model = waitToConfTable.getModel();
        int trID = Integer.parseInt(model.getValueAt(i, 0).toString());
        trDialog.show(trID);
    }//GEN-LAST:event_detailsRButtonActionPerformed

    @Override
    public void refresh() {
        try {
            toConfirmTable.setModel(getSession().getToConfirmTransactions());
            toConfirmTable.getColumnModel().removeColumn(toConfirmTable.getColumnModel().getColumn(0));
            toConfirmTable.changeSelection(0, 0, false, false);

            waitToConfTable.setModel(getSession().getWaitingForConfirmationTransactions());
            waitToConfTable.getColumnModel().removeColumn(waitToConfTable.getColumnModel().getColumn(0));
            waitToConfTable.changeSelection(0, 0, false, false);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JToggleButton confirmButton;
    private javax.swing.JButton detailsLButton;
    private javax.swing.JButton detailsRButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable toConfirmTable;
    private javax.swing.JTable waitToConfTable;
    // End of variables declaration//GEN-END:variables
}
