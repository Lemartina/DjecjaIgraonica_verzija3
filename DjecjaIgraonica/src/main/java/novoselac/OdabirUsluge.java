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


@WebServlet("/OdabirUsluge")
public class OdabirUsluge extends HttpServlet{
    

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
    
        out.println("<div class=\"index-intro\"> \n" +
"		<div class=\"wrapper\">\n" +
"			<h1>Rezervacija termina za igraonicu</h1>\n" +
"                 <h2>3. Odabir usluge </h2></div>\n" +
"                   ");
        
       //jdbc connection

       
     
       
       	try {
         //2b
	Class.forName("com.mysql.cj.jdbc.Driver");
	//3
        Connection con = DriverManager.getConnection//jdbc:mysql://localhost/djecjaigraonicahib
	("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
	
//      TABLICA USLUGA

        String sql;
        sql="select * from  usluga";
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery(sql);
        
        
            
        
        out.println("<table cellspacing='0' width='350px' border='1'>");
        out.println("<tr>");
        out.println("<td> Cijena</td>");
        out.println("<td> Jedinica mjere</td>");
        out.println("<td> Količina</td>");
        out.println("<td> Naziv</td>");
        out.println("<td> Dodaj</td>");
      
        
        out.println("</tr>");
        
      
        while (rs.next()) {
            out.println("<tr>");
            out.println("<td>" + rs.getString("cijena")+"</td>" );
            out.println("<td>" + rs.getString("jedinicaMjere")+"</td>" );
            out.println("<td>" + rs.getString("kolicina")+"</td>");
            out.println("<td>" + rs.getString("naziv")+"</td>");
            out.println("<td><a href='UslugaNaPosjeti?sifra=" + rs.getString("sifra") + "'><i class='fas fa-trash'></i> Dodaj</a></td>");
       out.println("</tr>");
        }
        
        out.println("<table>");
        
        out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
        
        
        
		} catch (ClassNotFoundException ex) {
              Logger.getLogger(novoselac.model.Posjeta.class.getName());
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

