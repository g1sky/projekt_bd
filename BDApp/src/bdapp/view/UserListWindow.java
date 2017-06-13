package bdapp.view;

import bdapp.AppWindow;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

public class UserListWindow extends AppPageView {
    
    UserDetailsDialog userDetailsDialog;

    public UserListWindow(AppWindow parent) {
        super(parent);
        initComponents();
        
        userDetailsDialog = new UserDetailsDialog(parent, true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        detailsButton = new javax.swing.JButton();

        userTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(userTable);

        backButton.setText("Wróć");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        detailsButton.setText("Pokaż szczegóły");
        detailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addComponent(detailsButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(detailsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        getWindow().changeView(NavigateWindow.class);
    }//GEN-LAST:event_backButtonActionPerformed

    private void detailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsButtonActionPerformed
        int i = userTable.getSelectedRow();
        TableModel model = userTable.getModel();
        String nickname = model.getValueAt(i, 0).toString();
        userDetailsDialog.show(nickname);
    }//GEN-LAST:event_detailsButtonActionPerformed

    @Override
    public void refresh() {
        try {
            userTable.setModel(getSession().getUsers());
            userTable.changeSelection(0, 0, false, false);
        } catch (SQLException ex) {
            Logger.getLogger(UserListWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton detailsButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
