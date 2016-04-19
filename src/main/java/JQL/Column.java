/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JQL;

/**
 *
 * @author Jbeag_000
 */
public class Column {
    private int id;
    private String columnName;
    private String dataType;
    private Relationship relation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Relationship getRelation() {
        return relation;
    }

    public void setRelation(Relationship relation) {
        this.relation = relation;
    }

    public Column(int id, String columnName, String dataType, Relationship relation) {
        this.id = id;
        this.columnName = columnName;
        this.dataType = dataType;
        this.relation = relation;
    }

    public Column(int id, String columnName, String dataType) {
        this.id = id;
        this.columnName = columnName;
        this.dataType = dataType;
    }
}
