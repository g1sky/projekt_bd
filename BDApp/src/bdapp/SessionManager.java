package bdapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

// tutaj lądują konkretne funkcje odpytujące bazę (chociaż nie wiem czy ta nazwa dobrze oddaje zadanie tej klasy)
public class SessionManager {

    private final BDApp parent;
    private final String username;

    public SessionManager(BDApp parent, String username) {
        this.parent = parent;
        this.username = username;
    }

    private Connection getConnection() {
        return parent.getConnection();
    }

    public String getUsername() {
        return this.username;
    }

    public boolean addWares(String wareName, double wareAmount, int wareCategory) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement(
                    "INSERT INTO g1_sgorski.towar "
                    + "(nazwa, ilosc, id_kategorii, wlasciciel) "
                    + "VALUES (?, ?, ?, ?)"
            );
            stmt.setString(1, wareName);
            stmt.setDouble(2, wareAmount);
            stmt.setInt(3, wareCategory);
            stmt.setString(4, getUsername());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean updateWares(int wareId, String wareName, double wareAmount, int wareCategory) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement(
                    "UPDATE g1_sgorski.towar "
                    + "SET nazwa = ?, ilosc = ?, id_kategorii = ? "
                    + "WHERE id = ? AND wlasciciel = ?"
            );
            stmt.setString(1, wareName);
            stmt.setDouble(2, wareAmount);
            stmt.setInt(3, wareCategory);
            stmt.setInt(4, wareId);
            stmt.setString(5, getUsername());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean removeWares(int wareId) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement(
                    "DELETE FROM g1_sgorski.towar "
                    + "WHERE id = ? AND wlasciciel = ?"
            );
            stmt.setInt(1, wareId);
            stmt.setString(2, getUsername());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public DefaultTableModel getUserWares() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement("SELECT id, nazwa, ilosc, id_kategorii FROM g1_sgorski.towar WHERE wlasciciel = ?"); // to zapytanie trzeba ulepszyć
            stmt.setString(1, getUsername());
            rs = stmt.executeQuery();
            model = BDApp.dataFromResultSet(rs, new int[]{0}); // static - jakoś to nie pasuje
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }
}
