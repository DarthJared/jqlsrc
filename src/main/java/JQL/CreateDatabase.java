/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JQL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
@WebServlet(name = "CreateDatabase", urlPatterns = {"/CreateDatabase"})
public class CreateDatabase extends HttpServlet {

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
            throws ServletException, IOException, SAXException, ParserConfigurationException {
        String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
        File databaseFolder = new File(dataDirectory + "databases");        
        if (!databaseFolder.exists()) {
            System.out.println("database folder doesn't exists");
            databaseFolder.mkdirs();
        }        
        if (databaseFolder.exists()) {
            System.out.println("database folder exists");
        }
        
        String databaseName = request.getParameter("databaseName");
        String databasePath = dataDirectory + "databases\\" + databaseName;
        
        File databaseList = new File(dataDirectory + "databases\\databaseList.txt");
        String insert = "";
        if(!databaseList.exists()) {
            insert = "<databases>\n" +
"  <next_id>2</next_id>\n" +
"  <database>\n" +
"    <database_id>1</database_id>\n" +
"    <database_name>" + databaseName + "</database_name>\n" +
"    <database_path>" + databasePath + "</database_path>    \n" +
"  </database>\n" +
"</databases>";
        }
        else {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(databaseList);
            doc.getDocumentElement().normalize();

            NodeList nDatabases = doc.getElementsByTagName("database");
            
            int nextId = Integer.parseInt(doc.getElementsByTagName("next_id").item(0).getTextContent());
            
            List<Database> databases = new ArrayList<>();
            
            for (int i = 0; i < nDatabases.getLength(); i++) {
                Node nDatabase = nDatabases.item(i);
                Element eDatabase = (Element) nDatabase;
                
                int databaseId = Integer.parseInt(eDatabase.getElementsByTagName("database_id").item(0).getTextContent());
                String savedDatabaseName = eDatabase.getElementsByTagName("database_name").item(0).getTextContent();
                String savedDatabasePath = eDatabase.getElementsByTagName("database_path").item(0).getTextContent();
                
                Database dAdder = new Database(databaseId, savedDatabaseName, savedDatabasePath);
                databases.add(dAdder);
            }
            
            int newNext = nextId++;
            insert = "<databases>\n" + 
                    "  <next_id>" + newNext + "</next_id>\n";
            for (int j = 0; j < databases.size(); j++) {
                Database uDatabase = databases.get(j);
                insert += "  <database>\n" +
"    <database_id>" + uDatabase.getId() + "</database_id>\n" +
"    <database_name>" + uDatabase.getName() + "</database_name>\n" +
"    <database_path>" + uDatabase.getPath() + "</database_path>    \n" +
"  </database>\n";
                
            }
            
            insert += "  <database>\n" +
"    <database_id>" + nextId + "</database_id>\n" +
"    <database_name>" + databaseName + "</database_name>\n" +
"    <database_path>" + databasePath + "</database_path>    \n" +
"  </database>\n" +
"</databases>";            
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(databaseList));
        bw.write(insert);
        bw.flush();
        bw.close();
        
        request.getRequestDispatcher("GetDatabases").forward(request, response);
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
        } catch (SAXException ex) {
            Logger.getLogger(CreateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreateDatabase.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SAXException ex) {
            Logger.getLogger(CreateDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CreateDatabase.class.getName()).log(Level.SEVERE, null, ex);
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
