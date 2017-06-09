package bdapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

// tutaj lądują konkretne funkcje odpytujące bazę (chociaż nie wiem czy ta nazwa dobrze oddaje zadanie tej klasy)
// tutaj pewnie wyląduje też aktualny koszyk...
public class SessionManager {

    private final BDApp parent;
    private final String username;
    
    private HashMap<String, Integer> categoryIDs;

    private Cart cart;

    public SessionManager(BDApp parent, String username) {
        this.parent = parent;
        this.username = username;
        
        try {
            categoryIDs = getCategoriesMap(); // nie wiem jak często to powinno być odświeżane
        } catch (SQLException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.cart = new Cart();
    }

    private Connection getConnection() {
        return parent.getConnection();
    }

    public String getUsername() {
        return this.username;
    }

    public Cart getCart() {
        return cart;
    }
    
    public HashMap<String, Integer> getCategories(){
        return categoryIDs;
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
                    "SELECT t.id, t.nazwa AS nazwa, t.ilosc AS ilość, k.nazwa AS kategoria"
                    + " FROM g1_sgorski.towar t JOIN g1_sgorski.kategoria k ON (t.id_kategorii=k.id)"
                    + " WHERE wlasciciel = ?"
            );
            stmt.setString(1, getUsername());
            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs, new int[]{0}); // static - jakoś to nie pasuje
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
                    "SELECT t.id, t.nazwa AS NAZWA_TOWARU, o.ilosc AS ILOŚĆ, o.cena_jednostkowa AS CENA_JEDNOSTKOWA, k.nazwa AS kategoria"
                    + " FROM g1_sgorski.oferta o JOIN g1_sgorski.towar t ON (o.id_towaru=t.id) JOIN g1_sgorski.kategoria k ON (t.id_kategorii=k.id)"
                    + " WHERE t.wlasciciel = ?"
            );
            stmt.setString(1, getUsername());
            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs, new int[]{0});
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    public DefaultTableModel getAllOffers(boolean hideMyOffers) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            if (hideMyOffers) {
                stmt = getConnection().prepareStatement(
                        "SELECT t.id, t.nazwa, o.ilosc, o.cena_jednostkowa, k.nazwa AS kategoria, t.wlasciciel AS właściciel"
                        + " FROM g1_sgorski.oferta o JOIN g1_sgorski.towar t ON (o.id_towaru=t.id) JOIN g1_sgorski.kategoria k ON (t.id_kategorii=k.id)"
                        + " WHERE t.wlasciciel != ?"
                );
                stmt.setString(1, getUsername()); // to tak opcjonalnie
            } else {
                stmt = getConnection().prepareStatement(
                        "SELECT t.id, t.nazwa, o.ilosc, o.cena_jednostkowa, k.nazwa AS kategoria, t.wlasciciel AS właściciel"
                        + " FROM g1_sgorski.oferta o JOIN g1_sgorski.towar t ON (o.id_towaru=t.id) JOIN g1_sgorski.kategoria k ON (t.id_kategorii=k.id)"
                );
            }
            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs, new int[]{0}); // static - jakoś to nie pasuje
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    private HashMap<String, Integer> getCategoriesMap() throws SQLException {
        HashMap<String, Integer> result = new HashMap<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().prepareStatement(
                    "SELECT id, nazwa"
                    + " FROM g1_sgorski.kategoria"
            );
            rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2));
                Integer value = rs.getInt(1);
                String key = rs.getString(2);
                result.put(key, value);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return result;
    }
}
