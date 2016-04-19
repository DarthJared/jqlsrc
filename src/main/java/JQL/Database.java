/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JQL;

import java.util.ArrayList;

/**
 *
 * @author Jbeag_000
 */
public class Database {
    private int id;
    private String name;
    private ArrayList<Table> tables;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public Database(int id, String name, ArrayList<Table> tables) {
        this.id = id;
        this.name = name;
        this.tables = tables;
    }
}
