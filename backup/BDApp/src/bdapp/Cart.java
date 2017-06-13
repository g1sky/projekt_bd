/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdapp;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class Cart {

    Vector<CartEntry> entries;

    Cart() {
        entries = new Vector<>();
    }

    public void add(int id, double amount, double value) {
        entries.add(new CartEntry(id, amount, value));
    }

    public void remove(int id) {
        entries.removeIf(e -> e.id == id);
    }

    public DefaultTableModel getTableModel() {
        Vector<Vector> rowData = new Vector<>();
        Vector<String> columns = new Vector<>(Arrays.asList(new String[]{"id", "ilość", "cena jednostkowa"}));
        for(CartEntry e: entries){
            Vector<Object> row = new Vector<>(3);
            row.add(e.id);
            row.add(e.amount);
            row.add(e.value);
            rowData.add(row);
        }
        return new DefaultTableModel(rowData, columns);
    }

}

class CartEntry {

    public int id;
    public double amount;
    public double value; // żeby nie było że ktoś w międzyczasie zmienił sobie cenę

    public CartEntry(int id, double amount, double value) {
        this.id = id;
        this.amount = amount;
        this.value = value;
    }

}
