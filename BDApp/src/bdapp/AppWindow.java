package bdapp;

import bdapp.view.AllOffersWindow;
import bdapp.view.WareManagementWindow;
import bdapp.view.SignUpWindow;
import bdapp.view.SignInWindow;
import bdapp.view.NavigateWindow;
import bdapp.view.AppPageView;
import bdapp.view.CartWindow;
import bdapp.view.OfferManagementWindow;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AppWindow extends JFrame {

    private final BDApp parent;

    private AppPageView pageView;
    private Map<Class, AppPageView> viewMap;

    public AppWindow(BDApp parent) {
        super("Gielda");
        this.parent = parent;

        setSize(640, 480);

        WindowListener exitListener = new WindowAdapter() { // do zamykania połączenia przy wychodzeniu
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(
                        null, "Czy na pewno chcesz zamknąć aplikację?",
                        "Potwierdzenie wyjścia", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    parent.close();
                    System.exit(0);
                }
            }
        };
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(exitListener);

        viewMap = new HashMap<>();
        viewMap.put(SignInWindow.class, new SignInWindow(this));
        viewMap.put(SignUpWindow.class, new SignUpWindow(this));
        viewMap.put(NavigateWindow.class, new NavigateWindow(this));
        viewMap.put(WareManagementWindow.class, new WareManagementWindow(this));
        viewMap.put(OfferManagementWindow.class, new OfferManagementWindow(this));
        viewMap.put(AllOffersWindow.class, new AllOffersWindow(this));
        viewMap.put(CartWindow.class, new CartWindow(this));
        pageView = new SignInWindow(this);
        add(pageView);

        pack();
        setVisible(true);
    }

    public void changeView(Class clas) {
        remove(pageView);
        AppPageView temp = viewMap.get(clas);
        if (temp != null) {
            pageView = temp;
            add(pageView);
            pageView.refresh();

            validate();
            repaint();
        }
    }

    public BDApp getApp() {
        return parent;
    }

    // raczej niepotrzebne i nieopłacalne
    /*
    public void refreshAll() {
        map.forEach((k, v) -> {
            v.refresh();
        });
    }
     */
}
