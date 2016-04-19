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
public class Relationship {
    private int tableId;
    private int columnId;
    private int relationshipType;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public int getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(int relationshipType) {
        this.relationshipType = relationshipType;
    }    
    
    public Relationship(int tableId, int columnId, int relationshipType) {
        this.tableId = tableId;
        this.columnId = columnId;
        this.relationshipType = relationshipType;
    }
}
