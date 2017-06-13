package bdapp.view;

import bdapp.AppWindow;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableModel;

public class OfferManagementWindow extends AppPageView {

    DialogMode dialogMode;
    
    public OfferManagementWindow(AppWindow parent) {
        super(parent);
        initComponents();
        
        wareTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wareTable.setRowSelectionAllowed(true);
        offerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        offerTable.setRowSelectionAllowed(true);
        dialogMode = DialogMode.ADD;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newOfferDialog = new javax.swing.JDialog();
        cancelNew = new javax.swing.JButton();
        submitOffer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        wareNameField = new javax.swing.JTextField();
        wareAmountField = new javax.swing.JTextField();
        warePriceField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        offerTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        wareTable = new javax.swing.JTable();
        addOfferButton = new javax.swing.JButton();
        removeOfferButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        editOfferButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        newOfferDialog.setResizable(false);
        newOfferDialog.setSize(new java.awt.Dimension(350, 300));

        cancelNew.setText("Anuluj");
        cancelNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelNewActionPerformed(evt);
            }
        });

        submitOffer.setText("Zatwierdź");
        submitOffer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitOfferActionPerformed(evt);
            }
        });

        jLabel1.setText("Nazwa towaru:");

        jLabel2.setText("Ilość:");

        jLabel3.setText("Cena jednostkowa:");

        javax.swing.GroupLayout newOfferDialogLayout = new javax.swing.GroupLayout(newOfferDialog.getContentPane());
        newOfferDialog.getContentPane().setLayout(newOfferDialogLayout);
        newOfferDialogLayout.setHorizontalGroup(
            newOfferDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newOfferDialogLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(newOfferDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newOfferDialogLayout.createSequentialGroup()
                        .addGroup(newOfferDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(newOfferDialogLayout.createSequentialGroup()
                                .addComponent(cancelNew)
                                .addGap(141, 141, 141)
                                .addComponent(submitOffer))
                            .addGroup(newOfferDialogLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(281, 281, 281)))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(newOfferDialogLayout.createSequentialGroup()
                        .addGroup(newOfferDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wareNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(newOfferDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addComponent(wareAmountField)
                                .addComponent(warePriceField)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        newOfferDialogLayout.setVerticalGroup(
            newOfferDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newOfferDialogLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wareNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wareAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warePriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(newOfferDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelNew)
                    .addComponent(submitOffer))
                .addGap(28, 28, 28))
        );

        setPreferredSize(new java.awt.Dimension(640, 480));

        offerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(offerTable);
        offerTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        wareTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(wareTable);
        wareTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        addOfferButton.setText("Dodaj ofertę");
        addOfferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOfferButtonActionPerformed(evt);
            }
        });

        removeOfferButton.setText("Usuń ofertę");
        removeOfferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOfferButtonActionPerformed(evt);
            }
        });

        backButton.setText("Wróć");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        editOfferButton.setText("Edytuj ofertę");
        editOfferButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editOfferButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Towary");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setText("Oferty");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(150, Short.MAX_VALUE)
                        .addComponent(addOfferButton)
                        .addGap(18, 18, 18)
                        .addComponent(editOfferButton)
                        .addGap(18, 18, 18)
                        .addComponent(removeOfferButton)))
                .addContainerGap(158, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(145, 145, 145))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editOfferButton)
                    .addComponent(removeOfferButton)
                    .addComponent(addOfferButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(backButton)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addOfferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOfferButtonActionPerformed
        dialogMode = DialogMode.ADD;
        showOfferDialog();
    }//GEN-LAST:event_addOfferButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        getWindow().changeView(NavigateWindow.class);
    }//GEN-LAST:event_backButtonActionPerformed

    private void removeOfferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOfferButtonActionPerformed
        int i = offerTable.getSelectedRow();
        TableModel model = offerTable.getModel();
        String wareName = model.getValueAt(i, 1).toString();
        double wareAmount = Double.parseDouble(model.getValueAt(i, 2).toString());
        double warePrize = Double.parseDouble(model.getValueAt(i, 3).toString());

        int wareId = Integer.parseInt(model.getValueAt(i, 0).toString());
        int confirm = JOptionPane.showOptionDialog(
                null, "Czy na pewno chcesz usunąć ofertę\nnazwa: " + wareName + "\nilość: " + wareAmount + "\ncena jednostkowa: " + warePrize,
                "Potwierdzenie usunięcia", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            if (getSession().removeOffer(wareId)) {
                refresh();
            } else {
                JOptionPane.showMessageDialog(this, "Błąd usuwania oferty");
            }
        }
    }//GEN-LAST:event_removeOfferButtonActionPerformed

    private void editOfferButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editOfferButtonActionPerformed
        dialogMode = DialogMode.MODIFY;
        showOfferDialog();
    }//GEN-LAST:event_editOfferButtonActionPerformed

    private void cancelNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelNewActionPerformed
        hideOfferDialog();
    }//GEN-LAST:event_cancelNewActionPerformed

    private void submitOfferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitOfferActionPerformed
        TableModel model;
        int i;
        int wareId;
        switch (dialogMode) {
            case ADD:
                i = wareTable.getSelectedRow();
                model = wareTable.getModel();
                wareId = Integer.parseInt(model.getValueAt(i, 0).toString());
                if (getSession().addOffer(wareId, Double.parseDouble(wareAmountField.getText()), Double.parseDouble(warePriceField.getText()))) {
                    hideOfferDialog();
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(this, "Błąd dodawania oferty");
                }
                break;
            case MODIFY:
                i = offerTable.getSelectedRow();
                model = offerTable.getModel();
                wareId = Integer.parseInt(model.getValueAt(i, 0).toString());
                getSession().updateOffer(wareId, Double.parseDouble(wareAmountField.getText()), Double.parseDouble(warePriceField.getText()));
                break;
        }
        hideOfferDialog();
        refreshTables();
    }//GEN-LAST:event_submitOfferActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOfferButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelNew;
    private javax.swing.JButton editOfferButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JDialog newOfferDialog;
    private javax.swing.JTable offerTable;
    private javax.swing.JButton removeOfferButton;
    private javax.swing.JButton submitOffer;
    private javax.swing.JTextField wareAmountField;
    private javax.swing.JTextField wareNameField;
    private javax.swing.JTextField warePriceField;
    private javax.swing.JTable wareTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refresh() {
        refreshTables();
        
    }
    
    private void refreshTables() {
        try {
            wareTable.setModel(getSession().getUserWaresWithoutOffer());
            wareTable.getColumnModel().removeColumn(wareTable.getColumnModel().getColumn(0));
            wareTable.changeSelection(0, 0, false, false);
            offerTable.setModel(getSession().getCurrentUserOffers());
            offerTable.getColumnModel().removeColumn(offerTable.getColumnModel().getColumn(0));
            offerTable.changeSelection(0, 0, false, false);
        } catch (SQLException ex) {
            Logger.getLogger(WareManagementWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showOfferDialog() {
        int i;
        TableModel model;
        String wareName;
        switch (dialogMode) {
            case ADD:
                newOfferDialog.setTitle("Dodawanie oferty");
                i = wareTable.getSelectedRow();
                model = wareTable.getModel();
                wareName = model.getValueAt(i, 1).toString();
                wareNameField.setText(wareName);
                wareNameField.setEditable(false);
                wareAmountField.setText("");
                warePriceField.setText("");
                break;
            case MODIFY:
                newOfferDialog.setTitle("Modyfikacja oferty");
                i = offerTable.getSelectedRow();
                model = offerTable.getModel();
                wareName = model.getValueAt(i, 1).toString();
                double wareAmount = Double.parseDouble(model.getValueAt(i, 2).toString());
                double warePrize = Double.parseDouble(model.getValueAt(i, 3).toString());
                wareNameField.setText(wareName);
                wareNameField.setEditable(false);
                wareAmountField.setText(Double.toString(wareAmount));
                warePriceField.setText(Double.toString(warePrize));
                break;
        }
        newOfferDialog.setVisible(true);
        this.setEnabled(false);
    }

    private void hideOfferDialog() {
        newOfferDialog.setVisible(false);
        wareNameField.setText("");
        wareAmountField.setText("");
        warePriceField.setText("");
        this.setEnabled(true);
    }
}


