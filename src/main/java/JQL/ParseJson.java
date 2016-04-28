/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JQL;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jbeagle
 */
@WebServlet(name = "ParseJson", urlPatterns = {"/ParseJson"})
public class ParseJson extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParserConfigurationException, SAXException {
//        String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
//        File xmlFile = new File(dataDirectory + "myData.txt");
//        String starter = "";
//        if (!xmlFile.exists()) {
//            starter = "<databases>\n" +
//"  <database>\n" +
//"    <id>1</id>\n" +
//"    <name>movie_manager</name>\n" +
//"    <tables>\n" +
//"      <table>\n" +
//"        <table_id>1</table_id>\n" +
//"        <title>movie</title>\n" +
//"        <columns>\n" +
//"          <column>\n" +
//"            <column_id>1</column_id>\n" +
//"            <column_name>id</column_name>\n" +
//"            <data_type>int</data_type>\n" +
//"            <relationship>\n" +
//"              <table_id>1</table_id>\n" +
//"              <column_id>1</column_id>\n" +
//"              <relationship_type>1</relationship_type>\n" +
//"            </relationship>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>2</column_id>\n" +
//"            <column_name>name</column_name>\n" +
//"            <data_type>string</data_type>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>3</column_id>\n" +
//"            <column_name>Length</column_name>\n" +
//"            <data_type>int</data_type>\n" +
//"          </column>\n" +
//"        </columns>\n" +
//"      </table>\n" +
//"      <table>\n" +
//"        <table_id>2</table_id>\n" +
//"        <title>user</title>\n" +
//"        <columns>\n" +
//"          <column>\n" +
//"            <column_id>1</column_id>\n" +
//"            <column_name>id</column_name>\n" +
//"            <data_type>int</data_type>\n" +
//"            <relationship>\n" +
//"              <table_id>2</table_id>\n" +
//"              <column_id>1</column_id>\n" +
//"              <relationship_type>1</relationship_type>\n" +
//"            </relationship>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>2</column_id>\n" +
//"            <column_name>first_name</column_name>\n" +
//"            <data_type>string</data_type>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>3</column_id>\n" +
//"            <column_name>last_name</column_name>\n" +
//"            <data_type>string</data_type>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>4</column_id>\n" +
//"            <column_name>username</column_name>\n" +
//"            <data_type>string</data_type>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>5</column_id>\n" +
//"            <column_name>password</column_name>\n" +
//"            <data_type>string</data_type>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>6</column_id>\n" +
//"            <column_name>email</column_name>\n" +
//"            <data_type>string</data_type>\n" +
//"          </column>\n" +
//"        </columns>\n" +
//"      </table>\n" +
//"      <table>\n" +
//"        <table_id>3</table_id>\n" +
//"        <title>movie_user</title>\n" +
//"        <columns>\n" +
//"          <column>\n" +
//"            <column_id>1</column_id>\n" +
//"            <column_name>id</column_name>\n" +
//"            <data_type>int</data_type>\n" +
//"            <relationship>\n" +
//"              <table_id>3</table_id>\n" +
//"              <column_id>1</column_id>\n" +
//"              <relationship_type>1</relationship_type>\n" +
//"            </relationship>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>2</column_id>\n" +
//"            <column_name>user_id</column_name>\n" +
//"            <data_type>int</data_type>\n" +
//"            <relationship>\n" +
//"              <table_id>1</table_id>\n" +
//"              <column_id>1</column_id>\n" +
//"              <relationship_type>2</relationship_type>\n" +
//"            </relationship>\n" +
//"          </column>\n" +
//"          <column>\n" +
//"            <column_id>3</column_id>\n" +
//"            <column_name>movie_id</column_name>\n" +
//"            <data_type>int</data_type>\n" +
//"            <relationship>\n" +
//"              <table_id>2</table_id>\n" +
//"              <column_id>1</column_id>\n" +
//"              <relationship_type>2</relationship_type>\n" +
//"            </relationship>\n" +
//"          </column>\n" +
//"        </columns>\n" +
//"      </table>\n" +
//"    </tables>\n" +
//"  </database>\n" +
//"</databases>";
//            BufferedWriter bw = new BufferedWriter(new FileWriter(xmlFile));
//            bw.write(starter);
//            bw.flush();
//            bw.close();          
//        }
//        File readingFile =  new File(dataDirectory + "myData.txt");
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//        Document doc = dBuilder.parse(readingFile);
//        doc.getDocumentElement().normalize();
//        
//        NodeList nDatabases = doc.getElementsByTagName("database");
//        List<Database> databases = new ArrayList<>();
//        
//        for (int i = 0; i < nDatabases.getLength(); i++) {
//            Node nDatabase = nDatabases.item(i);
//            Element eDatabase = (Element) nDatabase;
//            int databaseId = Integer.parseInt(eDatabase.getElementsByTagName("id").item(0).getTextContent());
//            String databaseName = eDatabase.getElementsByTagName("name").item(0).getTextContent();
//            
//            NodeList nTables = eDatabase.getElementsByTagName("table");
//            ArrayList<Table> tables = new ArrayList<>();
//            for (int j = 0; j < nTables.getLength(); j++) {
//                Node nTable = nTables.item(j);
//                Element eTable = (Element) nTable;
//                int tableId = Integer.parseInt(eTable.getElementsByTagName("table_id").item(0).getTextContent());
//                String tableTitle = eTable.getElementsByTagName("title").item(0).getTextContent();
//                
//                NodeList nColumns = eTable.getElementsByTagName("column");
//                ArrayList<Column> columns = new ArrayList<>();
//                for (int k = 0; k < nColumns.getLength(); k++) {
//                    Node nColumn = nColumns.item(k);
//                    Element eColumn = (Element) nColumn;
//                    int columnId = Integer.parseInt(eColumn.getElementsByTagName("column_id").item(0).getTextContent());
//                    String columnName = eColumn.getElementsByTagName("column_name").item(0).getTextContent();
//                    String dataType = eColumn.getElementsByTagName("data_type").item(0).getTextContent();
//                    
//                    NodeList nRelationships = eColumn.getElementsByTagName("relationship");
//                    ArrayList<Relationship> relationships = new ArrayList<>();
//                    for (int l = 0; l < nRelationships.getLength(); l++) {
//                        Node nRelationship = nRelationships.item(l);
//                        Element eRelationship = (Element) nRelationship;
//                        int rTableId = Integer.parseInt(eRelationship.getElementsByTagName("table_id").item(0).getTextContent());
//                        int rColumnId = Integer.parseInt(eRelationship.getElementsByTagName("column_id").item(0).getTextContent());
//                        int relationshipType = Integer.parseInt(eRelationship.getElementsByTagName("relationship_type").item(0).getTextContent());
//                        
//                        Relationship rAdder = new Relationship(rTableId, rColumnId, relationshipType);
//                        relationships.add(rAdder);
//                    }                    
//                    Column cAdder = new Column(columnId, columnName, dataType, relationships);
//                    columns.add(cAdder);
//                }                
//                Table tAdder = new Table(tableId, tableTitle, columns);
//                tables.add(tAdder);
//            }   
//            Database dAdder = new Database(databaseId, databaseName, tables);
//            databases.add(dAdder);
//        }
//        
//        
//        
//        
////        BufferedReader reader = new BufferedReader(new FileReader(readingFile));
////        String line = "";
////        String content = "";
////        
////        while((line = reader.readLine()) != null) {
////            content += line;
////        }       
////        
////        String tester = "Testing";
//        
//        request.setAttribute("savedDatabases", databases);
//        request.getRequestDispatcher("ShowCurrent.jsp").forward(request, response);
//        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ParseJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ParseJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ParseJson.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ParseJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
