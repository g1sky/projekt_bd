package bdapp;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AppWindow extends JFrame {

    private BDApp parent;

    private JTextField input;
    private JScrollPane sp;
    private JTable output_t;

    public AppWindow(BDApp parent) {
        super("Navigation");
        this.parent = parent;
        
        setSize(640, 480);

        WindowListener exitListener = new WindowAdapter() { // do zamykania połączenia przy wychodzeniu
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Are You Sure to Close Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    parent.close();
                    System.exit(0);
                }
            }
        };
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(exitListener);

        input = new JTextField(40);
        JButton btn = new JButton("execute");
        btn.addActionListener(e -> {
            try {
                ResultSet rs = this.parent.executeQuery(input.getText()); // wykonanie zapytania
                output_t.setModel(dataFromResultSet(rs)); // aktualizacja wyświetlanej tabeli
            } catch (SQLException ex) {
                Logger.getLogger(AppWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        output_t = new JTable();
        sp = new JScrollPane(output_t);

        Container pane = getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(input, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 40;
	c.gridx = 1;
	c.gridy = 0;
        pane.add(btn, c);
        
        c.fill = GridBagConstraints.SOUTH;
        c.ipady = 0;
	c.gridwidth = 2;
	c.gridx = 0;
	c.gridy = 1;
        pane.add(sp, c);

        pack();
        setVisible(true);
    }

    // zwraca model danych, którymi może być wypełniona JTable
    private DefaultTableModel dataFromResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int colNum = rsmd.getColumnCount();
        Vector<String> columnNames = new Vector<>();
        Vector<Vector> rowData = new Vector<>();

        for (int i = 0; i < colNum; i++) {
            columnNames.add(rsmd.getColumnLabel(i + 1));
        }

        while (rs.next()) {
            Vector<Object> row = new Vector<>();
            for (int i = 0; i < colNum; i++) {
                Object obj = rs.getString(i + 1);
                row.add(obj);
            }
            rowData.add(row);
        }
        return new DefaultTableModel(rowData, columnNames);
    }
}
