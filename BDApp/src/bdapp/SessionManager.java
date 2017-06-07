package bdapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

// tutaj lądują konkretne funkcje odpytujące bazę (chociaż nie wiem czy ta nazwa dobrze oddaje zadanie tej klasy)
// tutaj pewnie wyląduje też aktualny koszyk...
public class SessionManager {

    private final BDApp parent;
    private final String username;
    
    private Cart cart;

    public SessionManager(BDApp parent, String username) {
        this.parent = parent;
        this.username = username;
        
        this.cart = new Cart();
    }

    private Connection getConnection() {
        return parent.getConnection();
    }

    public String getUsername() {
        return this.username;
    }
    
    public Cart getCart(){
        return cart;
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
            stmt = getConnection().prepareStatement(
                    "SELECT id, nazwa, ilosc, id_kategorii"
                    + " FROM g1_sgorski.towar"
                    + " WHERE wlasciciel = ?"); // to zapytanie trzeba ulepszyć
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

    public DefaultTableModel getUserOffers() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement(
                    "SELECT t.id, t.nazwa, o.ilosc, o.cena_jednostkowa"
                    + " FROM g1_sgorski.oferta o JOIN g1_sgorski.towar t ON (o.id_towaru=t.id)"
                    + " WHERE t.wlasciciel = ?"
            );
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

    public DefaultTableModel getAllOffers() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement(
                    "SELECT t.id, t.nazwa, o.ilosc, o.cena_jednostkowa, t.wlasciciel"
                    + " FROM g1_sgorski.oferta o JOIN g1_sgorski.towar t ON (o.id_towaru=t.id)"
            );
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
