/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JQL;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jbeag_000
 */
public class Table {
    private int tableId;
    private String title;
    private ArrayList<Column> columns;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    public Table(int tableId, String title, ArrayList<Column> columns) {
        this.tableId = tableId;
        this.title = title;
        this.columns = columns;
    }
}
