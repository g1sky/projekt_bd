package bdapp;

import bdapp.view.SignInWindow;
import java.awt.EventQueue;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class BDApp {

    public static void main(String[] args) {
        BDApp app = new BDApp();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginWindow(app);
            }
        });
    }

    // -------------------------------------------------------------------------
    private Connection conn;
    private SessionManager session;

    public BDApp() {
        session = null;
        conn = null;
    }

    public SessionManager getSession() {
        return session;
    }

    public void close() {
        System.out.print("closing connection... ");
        if (conn != null) {
            try {
                signOut();
                conn.close();
                System.out.println("done");
            } catch (SQLException ex) {
                Logger.getLogger(BDApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean connectToBD(String username, String passwrd) {
        try {
            conn = newConnection(username, passwrd);
            setRole(conn);
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    // zwraca połączenie z bazą
    public Connection newConnection(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:" + username + "/" + password + "@localhost:1521:ora2016");
        System.out.println("Connected to database");
        return conn;
    }

    public Connection getConnection() {
        return conn;
    }

    // to jest niefajne ale co zrobić...
    public void setRole(Connection con) throws SQLException {
        Statement stmt = null;
        String query = "set role projekt_bazodanowy";
        try {
            stmt = con.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    // lepiej tego nie używać, jedynie dla prostych zapytań BEZ ZMIENNYCH
    public ResultSet executeQuery(String query) throws SQLException {
        System.out.println("executing: " + query);
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    // przyjmuje jako dodatkowy parametr tablicę z numerami kolumn, które nie są edytowalne
    public static DefaultTableModel dataModelFromResultSet(ResultSet rs) throws SQLException {
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

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        return model;
    }

    //--------------------------------------------------------------------------
    public boolean signInAs(String login, char[] password) {
        try {
            if (!login.isEmpty()
                    && userExists(login)
                    && password.length != 0
                    && HashPassword.validatePassword(password, getUserPassword(login))) {
                session = new SessionManager(this, login);
                return true;
            }
        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(SignInWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void signOut() {
        session = null;
    }

    public boolean userExists(String login) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT COUNT(*) AS total FROM g1_sgorski.uzytkownik WHERE nickname = ?");
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("total") == 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return true;
    }

    public String getUserPassword(String login) throws SQLException {
        String password = null;
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT haslo FROM g1_sgorski.uzytkownik WHERE nickname = ?");
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                password = rs.getString("haslo");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return password;
    }
}
