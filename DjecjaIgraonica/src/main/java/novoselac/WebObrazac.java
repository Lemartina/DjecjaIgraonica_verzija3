/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package novoselac;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */


@WebServlet("/WebObrazac")
public class WebObrazac extends HttpServlet{
    

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet javaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Pozdrav da vidimo radi li!!! </h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        
        response.setContentType("text/html");
        PrintWriter out= response.getWriter();
       
        
//        OBRAZAC ZA POPUNAJVANJE - TABLICA DIJETE, DIJETEPOSJETA,POSJETA, USLUGAPOSJETE, USLUGA
         out.println("<div class=\"index-intro\"> \n" +
"		<div class=\"wrapper\">\n" +
"			<h1>Rezervacija termina za igraonicu</h1>\n" +
"                 <h2>1. Podaci o djetetu </h2>\n" +
"			<form action=\"JavaServletDjeca\" method=\"post\" >\n" +
"                            \n" +
"                     <!--ovo sve treba staviti u java servlet preko out.println-->          \n" +
"                   <!--<textarea name=\"message\"> Rezervacija termina za igraonicu </textarea>-->\n" +
"                   <br><br>\n" +
"                <div>\n" +
"                   <input type=\"ime\" id=\"ime\" name= \"ime\" placeholder='Ime djeteta'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"       \n" +
"                <div>\n" +
"                   <input type=\"prezime\" id=\"prezime\" name= \"prezime\" placeholder='Prezime djeteta'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"       \n" +
"                <div>\n" +
"                   <input type=\"oib\" id=\"oib\" name= \"oib\" placeholder='OIB djeteta'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"               \n" +
"                            \n" +
"                 <div>\n" +
"                   <input type=\"imeRoditelja\" id=\"imeRoditelja\" name= \"imeRoditelja\" placeholder='Ime roditelja'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"\n" +
"                    <div>\n" +
"                   <input type=\"telefonRoditelja\" id=\"telefonRoditelja\" name= \"telefonRoditelja\" placeholder='Telefon roditelja'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"                   \n" +
                 "<div>\n" +     
"                   ");

         
          out.println("<button type=\"submit\" class=\"btn btn-primary\">Dodaj</button>");
       //jdbc connection

       
     
       
       	try {
         //2b
	Class.forName("com.mysql.cj.jdbc.Driver");
	//3
        Connection con = DriverManager.getConnection//jdbc:mysql://localhost/djecjaigraonicahib
	("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
	
//      TABLICA USLUGA

        String sql;
        sql="select * from  dijete";
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery(sql);
        
        
                    
		} catch (ClassNotFoundException ex) {
              Logger.getLogger(novoselac.model.Dijete.class.getName());
			out.println(ex);

         
		}
	catch(SQLException ex){
            out.println("<font color= 'red'> Record Failed </fornt>");
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
      doGet(request, response);
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

