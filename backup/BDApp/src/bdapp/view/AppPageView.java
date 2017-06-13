package bdapp.view;

import bdapp.AppWindow;
import bdapp.BDApp;
import bdapp.SessionManager;
import javax.swing.JPanel;

// nadklasa dla wszystkich "widoków"
public abstract class AppPageView extends JPanel {

    private AppWindow parent;
    
    public AppPageView(AppWindow parent) {
        this.parent = parent;
    }
    
    protected AppWindow getWindow(){
        return parent;
    }
    
    protected BDApp getApp(){
        return parent.getApp();
    }
    
    protected SessionManager getSession(){
        return parent.getApp().getSession();
    }
    
    // funkcja, która jest uruchamiana przy pokazywaniu danego widoku
    public abstract void refresh();
}
