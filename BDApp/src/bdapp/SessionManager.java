package bdapp;

import bdapp.entities.Offer;
import bdapp.entities.Transaction;
import bdapp.entities.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SessionManager {

    private final BDApp parent;
    private final String username;

    private HashMap<String, Integer> categoryIDs;

    public SessionManager(BDApp parent, String username) {
        this.parent = parent;
        this.username = username;

        try {
            categoryIDs = getCategoriesMap();
        } catch (SQLException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection getConnection() {
        return parent.getConnection();
    }

    public String getUsername() {
        return this.username;
    }

    public HashMap<String, Integer> getCategories() {
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
            model = BDApp.dataModelFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    public DefaultTableModel getUserWaresWithoutOffer() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement(
                    "SELECT t.id, t.nazwa AS nazwa, t.ilosc AS ilość, k.nazwa AS kategoria"
                    + " FROM g1_sgorski.towar t JOIN g1_sgorski.kategoria k ON (t.id_kategorii=k.id)"
                    + " WHERE wlasciciel = ?"
                    + " AND t.id NOT IN(SELECT o.id_towaru FROM oferta o)"
            );
            stmt.setString(1, getUsername());
            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    public boolean addOffer(int wareId, double wareAmount, double warePrize) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement(
                    "INSERT INTO g1_sgorski.oferta "
                    + "(cena_jednostkowa, ilosc, id_towaru) "
                    + "VALUES (?, ?, ?)"
            );
            stmt.setDouble(1, warePrize);
            stmt.setDouble(2, wareAmount);
            stmt.setInt(3, wareId);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean updateOffer(int wareId, double wareAmount, double warePrize) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement(
                    "UPDATE g1_sgorski.oferta "
                    + "SET cena_jednostkowa = ?, ilosc = ? "
                    + "WHERE id_towaru = ?"
            );
            stmt.setDouble(1, warePrize);
            stmt.setDouble(2, wareAmount);
            stmt.setInt(3, wareId);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean removeOffer(int wareId) {
        PreparedStatement stmt = null;
        try {
            stmt = getConnection().prepareStatement(
                    "DELETE FROM g1_sgorski.oferta "
                    + "WHERE id_towaru = ?"
            );
            stmt.setInt(1, wareId);

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BDApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public TableModel getCurrentUserOffers() throws SQLException {
        return getUserOffers(getUsername());
    }

    public TableModel getAllOffers(boolean hideMyOffers) throws SQLException {
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
            model = BDApp.dataModelFromResultSet(rs);
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

    public Offer getOffer(int wareID) throws SQLException {
        Offer offer = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().prepareStatement(
                    "SELECT t.id, t.nazwa AS nazwa_towaru, o.ilosc, o.cena_jednostkowa, k.nazwa AS kategoria"
                    + " FROM g1_sgorski.towar t JOIN g1_sgorski.oferta o ON (t.id=o.id_towaru) JOIN g1_sgorski.kategoria k ON (t.id_kategorii=k.id)"
                    + " WHERE t.id = ?"
            );
            stmt.setInt(1, wareID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                offer = new Offer(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return offer;
    }

    public boolean addToCart(Offer offer) {
        String call
                = "BEGIN"
                + " ? := CASE WHEN (g1_sgorski.add_to_cart(?, ?, ?)) "
                + "       THEN 1 "
                + "       ELSE 0"
                + "      END;"
                + "END;";
        CallableStatement cstmt = null;
        try {
            cstmt = getConnection().prepareCall(call);
            cstmt.setQueryTimeout(1800);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);

            cstmt.setInt(2, offer.wareID);
            cstmt.setDouble(3, offer.wareAmount);
            cstmt.setString(4, username);

            cstmt.execute();
            return cstmt.getInt(1) == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public DefaultTableModel getCart() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement("SELECT t.id, t.nazwa, twt.ilosc, twt.cena_jednostkowa"
                    + " FROM g1_sgorski.towar t JOIN g1_sgorski.towar_w_transakcji twt ON (t.id = twt.id_towaru) JOIN g1_sgorski.transakcja tr ON (twt.id_transakcji = tr.id)"
                    + " WHERE tr.kupiec = ? AND tr.stan = 0");
            stmt.setString(1, getUsername());

            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    public boolean submitCart() {
        String call = "BEGIN"
                + " ? := CASE WHEN (g1_sgorski.submit_cart(?)) "
                + "       THEN 1 "
                + "       ELSE 0"
                + "      END;"
                + "END;";
        CallableStatement cstmt = null;
        try {
            cstmt = getConnection().prepareCall(call);
            cstmt.setQueryTimeout(1800);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setString(2, username);

            cstmt.execute();
            return cstmt.getInt(1) == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean clearCart() {
        String call = "BEGIN"
                + " ? := CASE WHEN (g1_sgorski.clear_cart(?)) "
                + "       THEN 1 "
                + "       ELSE 0"
                + "      END;"
                + "END;";
        CallableStatement cstmt = null;
        try {
            cstmt = getConnection().prepareCall(call);
            cstmt.setQueryTimeout(1800);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setString(2, username);

            cstmt.execute();
            return cstmt.getInt(1) == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean removeFromCart(int wareID) {
        String call = "BEGIN"
                + " ? := CASE WHEN (g1_sgorski.remove_from_cart(?, ?)) "
                + "       THEN 1 "
                + "       ELSE 0"
                + "      END;"
                + "END;";
        CallableStatement cstmt = null;
        try {
            cstmt = getConnection().prepareCall(call);
            cstmt.setQueryTimeout(1800);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setInt(2, wareID);
            cstmt.setString(3, username);

            cstmt.execute();
            return cstmt.getInt(1) == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public TableModel getToConfirmTransactions() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement("SELECT tr.id, tr.kupiec, tr.sprzedawca, tr.stan, tr.data "
                    + "FROM g1_sgorski.transakcja tr "
                    + "WHERE (tr.sprzedawca = ? AND tr.stan = 1) OR (tr.kupiec = ? AND tr.stan = 2)");
            stmt.setString(1, getUsername());
            stmt.setString(2, getUsername());

            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs);
            formatStateOnModel(model);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    public TableModel getWaitingForConfirmationTransactions() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement("SELECT tr.id, tr.kupiec, tr.sprzedawca, tr.stan, tr.data "
                    + "FROM g1_sgorski.transakcja tr "
                    + "WHERE (tr.sprzedawca = ? AND tr.stan = 2) OR (tr.kupiec = ? AND tr.stan = 1)");
            stmt.setString(1, getUsername());
            stmt.setString(2, getUsername());

            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs);
            formatStateOnModel(model);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    public boolean confirmTransaction(int trID) {
        String call = "BEGIN"
                + " ? := CASE WHEN (g1_sgorski.confirm_transaction(?)) "
                + "       THEN 1 "
                + "       ELSE 0"
                + "      END;"
                + "END;";
        CallableStatement cstmt = null;
        try {
            cstmt = getConnection().prepareCall(call);
            cstmt.setQueryTimeout(1800);
            cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
            cstmt.setInt(2, trID);

            cstmt.execute();
            return cstmt.getInt(1) == 1;
        } catch (SQLException ex) {
            Logger.getLogger(SessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public TableModel getHistory() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement("SELECT tr.id, tr.kupiec, tr.sprzedawca, tr.stan, tr.data "
                    + "FROM g1_sgorski.transakcja tr "
                    + "WHERE (tr.sprzedawca = ? OR tr.kupiec = ?) AND tr.stan = 3");
            stmt.setString(1, getUsername());
            stmt.setString(2, getUsername());

            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs);
            formatStateOnModel(model);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    private void formatStateOnModel(TableModel model) {
        int colID = -1;
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (model.getColumnName(i).equals("STAN")) {
                colID = i;
                break;
            }
        }
        if (colID != -1) {
            for (int i = 0; i < model.getRowCount(); i++) {
                int s = Integer.parseInt(model.getValueAt(i, colID).toString());
                model.setValueAt(stateName(s), i, colID);
            }
        }
    }

    public static String stateName(int state) {
        switch (state) {
            case 0:
                return "KOSZYK";
            case 1:
                return "DO ZAPŁATY";
            case 2:
                return "DO WYSYŁKI";
            case 3:
                return "ZAKOŃCZONA";
        }
        return "BŁĄD";
    }

    public TableModel getTransactionWares(int trID) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement("SELECT t.nazwa, twt.ilosc, twt.cena_jednostkowa "
                    + "FROM g1_sgorski.towar t JOIN g1_sgorski.towar_w_transakcji twt ON (t.id = twt.id_towaru) JOIN g1_sgorski.transakcja tr ON (tr.id = twt.id_transakcji) "
                    + "WHERE tr.id = ?");
            stmt.setInt(1, trID);
            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    public Transaction getTransactionDetails(int trID) throws SQLException {
        Transaction tr = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().prepareStatement(
                    "SELECT tr.id, tr.kupiec, tr.sprzedawca, tr.data, tr.stan"
                    + " FROM g1_sgorski.transakcja tr"
                    + " WHERE tr.id = ?"
            );
            stmt.setInt(1, trID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                tr = new Transaction(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), stateName(rs.getInt(5)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return tr;
    }

    public TableModel getUsers() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement("SELECT u.nickname, u.imie, u.nazwisko, u.nr_telefonu, u.email FROM uzytkownik u");
            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return model;
    }

    public User getUserDetails(String nickname) throws SQLException {
        User user = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = getConnection().prepareStatement(
                    "SELECT u.nickname, u.imie, u.nazwisko, u.nr_telefonu, u.email "
                    + "FROM uzytkownik u "
                    + "WHERE u.nickname = ?"
            );
            stmt.setString(1, nickname);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return user;
    }

    public TableModel getUserOffers(String nickname) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        DefaultTableModel model = null;
        try {
            stmt = getConnection().prepareStatement(
                    "SELECT t.id, t.nazwa AS NAZWA_TOWARU, o.ilosc AS ILOŚĆ, o.cena_jednostkowa AS CENA_JEDNOSTKOWA, k.nazwa AS kategoria"
                    + " FROM g1_sgorski.oferta o JOIN g1_sgorski.towar t ON (o.id_towaru=t.id) JOIN g1_sgorski.kategoria k ON (t.id_kategorii=k.id)"
                    + " WHERE t.wlasciciel = ?"
            );
            stmt.setString(1, nickname);
            rs = stmt.executeQuery();
            model = BDApp.dataModelFromResultSet(rs);
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
