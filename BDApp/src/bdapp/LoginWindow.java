package bdapp;

import bdapp.AppWindow;
import bdapp.BDApp;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {

    private BDApp parent;

    private JTextField username_tf;
    private JTextField passwrd_tf;

    public LoginWindow(BDApp parent) {
        super("Login");
        this.parent = parent;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        username_tf = new JTextField(20);
        passwrd_tf = new JPasswordField(20);

        JButton b = new JButton("submit");
        b.addActionListener(e -> {
            if (this.parent.connectToBD(username_tf.getText(), passwrd_tf.getText())) {
                System.out.println("polaczono z baza");
                dispose();
                new AppWindow(this.parent);
            }
        });

        JLabel l1 = new JLabel("username: ");
        JLabel l2 = new JLabel("password: ");

        JPanel panel = new JPanel(new GridLayout(3, 2));

        panel.add(l1);
        panel.add(username_tf);
        panel.add(l2);
        panel.add(passwrd_tf);
        panel.add(b);

        add(panel, BorderLayout.CENTER);
        setSize(400, 200);
        setVisible(true);
    }
}
