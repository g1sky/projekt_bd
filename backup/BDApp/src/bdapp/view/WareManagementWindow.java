package bdapp.view;

import bdapp.AppWindow;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

public class WareManagementWindow extends AppPageView {

    DialogMode dialogMode;

    public WareManagementWindow(AppWindow parent) {
        super(parent);
        initComponents();

        dialogMode = DialogMode.ADD;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newWareDialog = new javax.swing.JDialog();
        submitWare = new javax.swing.JButton();
        cancelNew = new javax.swing.JButton();
        wareNameField = new javax.swing.JTextField();
        wareAmountField = new javax.swing.JTextField();
        wareCategoryBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tableScrollPane = new javax.swing.JScrollPane();
        wareTable = new javax.swing.JTable();
        refreshButton = new javax.swing.JButton();
        addWareButton = new javax.swing.JButton();
        manageOffer = new javax.swing.JButton();
        updateWare = new javax.swing.JButton();
        removeWares = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        newWareDialog.setResizable(false);
        newWareDialog.setSize(new java.awt.Dimension(400, 300));

        submitWare.setText("Zatwierdź");
        submitWare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitWareActionPerformed(evt);
            }
        });

        cancelNew.setText("Anuluj");
        cancelNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelNewActionPerformed(evt);
            }
        });

        wareCategoryBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Nazwa towaru:");

        jLabel3.setText("Ilość:");

        jLabel4.setText("Kategoria:");

        javax.swing.GroupLayout newWareDialogLayout = new javax.swing.GroupLayout(newWareDialog.getContentPane());
        newWareDialog.getContentPane().setLayout(newWareDialogLayout);
        newWareDialogLayout.setHorizontalGroup(
            newWareDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newWareDialogLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(cancelNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitWare)
                .addGap(25, 25, 25))
            .addGroup(newWareDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wareNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(newWareDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newWareDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(wareAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(wareCategoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        newWareDialogLayout.setVerticalGroup(
            newWareDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newWareDialogLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wareNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wareAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wareCategoryBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addGroup(newWareDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitWare)
                    .addComponent(cancelNew))
                .addGap(25, 25, 25))
        );

        setPreferredSize(new java.awt.Dimension(640, 480));

        wareTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        wareTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        wareTable.getTableHeader().setReorderingAllowed(false);
        tableScrollPane.setViewportView(wareTable);
        wareTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        refreshButton.setText("Odśwież");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        addWareButton.setText("Dodaj");
        addWareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addWareButtonActionPerformed(evt);
            }
        });

        manageOffer.setText("Zarządzaj ofertą");
        manageOffer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageOfferActionPerformed(evt);
            }
        });

        updateWare.setText("Aktualizuj");
        updateWare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateWareActionPerformed(evt);
            }
        });

        removeWares.setText("Usuń");
        removeWares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeWaresActionPerformed(evt);
            }
        });

        backButton.setText("Wróć");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel5.setText("Towary");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(backButton)
                            .addComponent(addWareButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeWares, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(updateWare, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0))
                    .addComponent(manageOffer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(198, 198, 198))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(addWareButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeWares)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateWare)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)
                        .addGap(76, 76, 76)
                        .addComponent(manageOffer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)))
                .addGap(35, 35, 35))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refreshWareTable();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void addWareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addWareButtonActionPerformed
        dialogMode = DialogMode.ADD;
        showWareDialog();
    }//GEN-LAST:event_addWareButtonActionPerformed

    private void manageOfferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageOfferActionPerformed
        getWindow().changeView(OfferManagementWindow.class);
    }//GEN-LAST:event_manageOfferActionPerformed

    private void updateWareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateWareActionPerformed
        dialogMode = DialogMode.MODIFY;
        showWareDialog();
    }//GEN-LAST:event_updateWareActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        getWindow().changeView(NavigateWindow.class);
    }//GEN-LAST:event_backButtonActionPerformed

    private void removeWaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeWaresActionPerformed
        int i = wareTable.getSelectedRow();
        TableModel model = wareTable.getModel();
        String wareName = model.getValueAt(i, 1).toString();
        double wareAmount = Double.parseDouble(model.getValueAt(i, 2).toString());
        int wareCategory = getSession().getCategories().get(model.getValueAt(i, 3).toString());

        int wareId = Integer.parseInt(model.getValueAt(i, 0).toString());
        int confirm = JOptionPane.showOptionDialog(
                null, "Czy na pewno chcesz usunąć towar z bazy?\nid: " + wareId + "\nnazwa: " + wareName + "\nilość: " + wareAmount + "\nkategoria: " + wareCategory,
                "Potwierdzenie usunięcia", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            if (getSession().removeWares(wareId)) {
                refresh();
            } else {
                JOptionPane.showMessageDialog(this, "Błąd usuwania danych towaru");
            }
        }
    }//GEN-LAST:event_removeWaresActionPerformed

    private void cancelNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelNewActionPerformed
        hideWareDialog();
    }//GEN-LAST:event_cancelNewActionPerformed

    private void submitWareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitWareActionPerformed
        int categoryID = getSession().getCategories().get(wareCategoryBox.getSelectedItem().toString());
        switch (dialogMode) {
            case ADD:
                if (getSession().addWares(wareNameField.getText(), Double.parseDouble(wareAmountField.getText()), categoryID)) {
                    hideWareDialog();
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(this, "Błąd dodawania towaru");
                }
                break;
            case MODIFY:
                int i = wareTable.getSelectedRow();
                TableModel model = wareTable.getModel();
                int wareID = Integer.parseInt(model.getValueAt(i, 0).toString());
                getSession().updateWares(wareID, wareNameField.getText(), Double.parseDouble(wareAmountField.getText()), categoryID);
                break;
        }
        hideWareDialog();
        refreshWareTable();
    }//GEN-LAST:event_submitWareActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addWareButton;
    private javax.swing.JButton backButton;
    private javax.swing.JButton cancelNew;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton manageOffer;
    private javax.swing.JDialog newWareDialog;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton removeWares;
    private javax.swing.JButton submitWare;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JButton updateWare;
    private javax.swing.JTextField wareAmountField;
    private javax.swing.JComboBox<String> wareCategoryBox;
    private javax.swing.JTextField wareNameField;
    private javax.swing.JTable wareTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refresh() {
        refreshWareTable();
    }

    private void refreshWareTable() {
        try {
            wareTable.setModel(getSession().getUserWares());
            wareTable.getColumnModel().removeColumn(wareTable.getColumnModel().getColumn(0)); // chowa kolumne z identyfikatorami
        } catch (SQLException ex) {
            Logger.getLogger(WareManagementWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showWareDialog() {
        wareCategoryBox.setModel(new DefaultComboBoxModel(getSession().getCategories().keySet().toArray()));
        switch (dialogMode) {
            case ADD:
                newWareDialog.setTitle("Dodawanie towaru");
                wareNameField.setText("");
                wareAmountField.setText("");
                wareCategoryBox.setSelectedIndex(0);
                break;
            case MODIFY: // nie wiem czy nie lepiej stworzyć do tego osobnego okna, bo jednak dane już raz stworzonego towaru nie mogą być dowolnie zmieniane - chociaż nazwa powinna pozostać żeby w transakcjach się zgadzało
                newWareDialog.setTitle("Modyfikacja towaru");

                int i = wareTable.getSelectedRow();
                TableModel model = wareTable.getModel();
                String wareName = model.getValueAt(i, 1).toString();
                double wareAmount = Double.parseDouble(model.getValueAt(i, 2).toString());
                String wareCategory = model.getValueAt(i, 3).toString();

                wareNameField.setText(wareName);
                wareAmountField.setText(Double.toString(wareAmount));
                wareCategoryBox.setSelectedItem(wareCategory);
                break;
        }
        newWareDialog.setVisible(true);
        this.setEnabled(false); // żeby nie modyfikować nic gdy okno dialogowe jest otwarte
    }

    private void hideWareDialog() {
        newWareDialog.setVisible(false);
        wareNameField.setText("");
        wareAmountField.setText("");
        wareCategoryBox.setSelectedIndex(0);
        this.setEnabled(true);
    }
}

enum DialogMode {
    ADD,
    MODIFY
}
