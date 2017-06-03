package bdapp;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private Connection conn;

    public BDApp() {
        conn = null;
    }

    public void close() {
        System.out.println("closing connection");
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(BDApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean connectToBD(String username, String passwrd) {
        try {
            conn = getConnection(username, passwrd);
            setRole(conn);
            //viewTable("kategoria"); // test
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return true;
    }

    // tylko test
    /*
    public void viewTable(String tableName) throws SQLException {
        Statement stmt = null;
        String query = "select * from G1_sgorski." + tableName;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int colNum = rsmd.getColumnCount();
            String[] columnNames = new String[colNum + 1];
            columnNames[0] = "KOLUMNY: ";
            System.out.print(columnNames[0]);
            for (int i = 1; i <= colNum; i++) {
                columnNames[i] = rsmd.getColumnLabel(i);
                System.out.print(columnNames[i] + "|");
            }
            System.out.print("\n");
            while (rs.next()) {
                System.out.print("\t");
                for (int i = 1; i <= colNum; i++) {
                    System.out.print(rs.getString(i) + "|");
                }
                System.out.print("\n");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
     */
    // zwraca połączenie z bazą
    public Connection getConnection(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:" + username + "/" + password + "@localhost:1521:ora2016");
        System.out.println("Connected to database");
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

    public ResultSet executeQuery(String query) throws SQLException {
        System.out.println("executing: " + query);
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
}
