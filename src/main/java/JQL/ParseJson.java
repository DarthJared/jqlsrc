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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            throws ServletException, IOException {
        String dataDirectory = System.getenv("OPENSHIFT_DATA_DIR");
        File xmlFile = new File(dataDirectory + "myData.txt");
        String starter = "";
        if (!xmlFile.exists()) {
            starter = "<databases>\n" +
"  <database>\n" +
"    <id>1</id>\n" +
"    <name>movie_manager</name>\n" +
"    <tables>\n" +
"      <table>\n" +
"        <table_id>1</table_id>\n" +
"        <title>movie</title>\n" +
"        <columns>\n" +
"          <column>\n" +
"            <column_id>1</column_id>\n" +
"            <column_name>id</column_name>\n" +
"            <data_type>int</data_type>\n" +
"            <relationship>\n" +
"              <table_id>1</table_id>\n" +
"              <column_id>1</column_id>\n" +
"              <relationship_type>1</relationship_type>\n" +
"            </relationship>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>2</column_id>\n" +
"            <column_name>name</column_name>\n" +
"            <data_type>string</data_type>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>3</column_id>\n" +
"            <column_name>Length</column_name>\n" +
"            <data_type>int</data_type>\n" +
"          </column>\n" +
"        </columns>\n" +
"      </table>\n" +
"      <table>\n" +
"        <table_id>2</table_id>\n" +
"        <title>user</title>\n" +
"        <columns>\n" +
"          <column>\n" +
"            <column_id>1</column_id>\n" +
"            <column_name>id</column_name>\n" +
"            <data_type>int</data_type>\n" +
"            <relationship>\n" +
"              <table_id>2</table_id>\n" +
"              <column_id>1</column_id>\n" +
"              <relationship_type>1</relationship_type>\n" +
"            </relationship>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>2</column_id>\n" +
"            <column_name>first_name</column_name>\n" +
"            <data_type>string</data_type>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>3</column_id>\n" +
"            <column_name>last_name</column_name>\n" +
"            <data_type>string</data_type>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>4</column_id>\n" +
"            <column_name>username</column_name>\n" +
"            <data_type>string</data_type>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>5</column_id>\n" +
"            <column_name>password</column_name>\n" +
"            <data_type>string</data_type>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>6</column_id>\n" +
"            <column_name>email</column_name>\n" +
"            <data_type>string</data_type>\n" +
"          </column>\n" +
"        </columns>\n" +
"      </table>\n" +
"      <table>\n" +
"        <table_id>3</table_id>\n" +
"        <title>movie_user</title>\n" +
"        <columns>\n" +
"          <column>\n" +
"            <column_id>1</column_id>\n" +
"            <column_name>id</column_name>\n" +
"            <data_type>int</data_type>\n" +
"            <relationship>\n" +
"              <table_id>3</table_id>\n" +
"              <column_id>1</column_id>\n" +
"              <relationship_type>1</relationship_type>\n" +
"            </relationship>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>2</column_id>\n" +
"            <column_name>user_id</column_name>\n" +
"            <data_type>int</data_type>\n" +
"            <relationship>\n" +
"              <table_id>1</table_id>\n" +
"              <column_id>1</column_id>\n" +
"              <relationship_type>2</relationship_type>\n" +
"            </relationship>\n" +
"          </column>\n" +
"          <column>\n" +
"            <column_id>3</column_id>\n" +
"            <column_name>movie_id</column_name>\n" +
"            <data_type>int</data_type>\n" +
"            <relationship>\n" +
"              <table_id>2</table_id>\n" +
"              <column_id>1</column_id>\n" +
"              <relationship_type>2</relationship_type>\n" +
"            </relationship>\n" +
"          </column>\n" +
"        </columns>\n" +
"      </table>\n" +
"    </tables>\n" +
"  </database>\n" +
"</databases>";
            BufferedWriter bw = new BufferedWriter(new FileWriter(xmlFile));
            bw.write(starter);
            bw.flush();
            bw.close();          
        }
        File readingFile =  new File(dataDirectory + "myData.txt");
        BufferedReader reader = new BufferedReader(new FileReader(readingFile));
        String line = "";
        String content = "";
        
        while((line = reader.readLine()) != null) {
            content += line;
        }       
        
        String tester = "Testing";
        request.setAttribute("savedData", content);
        request.setAttribute("test", tester);
        request.setAttribute("test2", starter);
        request.getRequestDispatcher("ShowCurrent.jsp").forward(request, response);
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
