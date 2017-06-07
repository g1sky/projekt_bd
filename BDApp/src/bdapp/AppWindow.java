package bdapp;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class AppWindow extends JFrame {

    public final BDApp parent;
    public String activeUser = null;

    private JPanel pane;
    private Map<Class, JPanel> map;

    public AppWindow(BDApp parent) {
        super("Gielda");
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
        
        map = new HashMap<>();
        map.put(SignInWindow.class, new SignInWindow(this));
        map.put(SignUpWindow.class, new SignUpWindow(this));
        map.put(NavigateWindow.class, new NavigateWindow(this, activeUser));
        pane = new SignInWindow(this);
        add(pane);

        pack();
        setVisible(true);
    }
    
    public void changeView(Class clas){
        remove(pane);
        pane = map.get(clas);
        add(pane);
        validate();
        repaint();
    }
    
}
