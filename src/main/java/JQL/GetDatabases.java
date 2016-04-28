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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "GetDatabases", urlPatterns = {"/GetDatabases"})
public class GetDatabases extends HttpServlet {

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
        String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
        File databaseList = new File(dataDirectory + "databases\\databaseList.txt");
        boolean databasesExist = false;
        if (databaseList.exists()) {
            databasesExist = true;
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(databaseList);
            doc.getDocumentElement().normalize();

            NodeList nDatabases = doc.getElementsByTagName("database"); 
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
            request.setAttribute("databases", databases);            
        }
        
        request.setAttribute("databasesExist", databasesExist);
        request.getRequestDispatcher("Interact.jsp").forward(request, response);
//        File databaseList = new File(dataDirectory + "databases\\currentDatabases.txt");
//        String starter = "";
//        if (!databaseList.exists()) {
//            starter = "<databases>\n" +
//"  <database>\n" +
//"    <database_id>1</database_id>\n" +
//"    <database_name>movie_manager</database_name>\n" +
//"    <database_path>" + dataDirectory + "databases\\movie_manager</database_path>    \n" +
//"  </database>\n" +
//"  <database>\n" +
//"    <database_id>2</database_id>\n" +
//"    <database_name>flirt_machine</database_name>\n" +
//"    <database_path>" + dataDirectory + "databases\\flirt_machine</database_path>    \n" +
//"  </database>\n" +
//"</databases>";
//            BufferedWriter bw = new BufferedWriter(new FileWriter(databaseList));
//            bw.write(starter);
//            bw.flush();
//            bw.close();
//        }
//        File movieFolder = new File(dataDirectory + "databases\\movie_manager");
//        if (!movieFolder.exists()) {
//            System.out.println("movie folder doesn't exists");
//            movieFolder.mkdirs();
//        }        
//        if (movieFolder.exists()) {
//            System.out.println("movie folder exists");
//        }
//        
//        File movieTableList = new File(dataDirectory + "databases\\movie_manager\\tableList.txt");
//        if (!movieTableList.exists()) {
//            starter = "<tables>\n" +
//"  <table>\n" +
//"    <table_id>1</table_id>\n" +
//"    <table_name>movie</table_name>\n" +
//"    <table_path>" + dataDirectory + "databases\\movie_manager\\tables\\movie</table_path>\n" +
//"  </table>\n" +
//"  <table>\n" +
//"    <table_id>2</table_id>\n" +
//"    <table_name>user</table_name>\n" +
//"    <table_path>" + dataDirectory + "databases\\movie_manager\\tables\\user</table_path>\n" +
//"  </table>\n" +
//"  <table>\n" +
//"    <table_id>3</table_id>\n" +
//"    <table_name>movie_user</table_name>\n" +
//"    <table_path>" + dataDirectory + "databases\\movie_manager\\tables\\movie_user</table_path>\n" +
//"  </table>\n" +
//"</tables>";
//            BufferedWriter bw2 = new BufferedWriter(new FileWriter(movieTableList));
//            bw2.write(starter);
//            bw2.flush();
//            bw2.close();
//        }
        
        
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
            Logger.getLogger(GetDatabases.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GetDatabases.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetDatabases.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GetDatabases.class.getName()).log(Level.SEVERE, null, ex);
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
