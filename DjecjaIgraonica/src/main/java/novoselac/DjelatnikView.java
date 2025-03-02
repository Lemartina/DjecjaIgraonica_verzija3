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


@WebServlet("/DjelatnikView")
public class DjelatnikView extends HttpServlet{
    

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
            out.println("<title>Servlet javaServletDjelatnici</title>");            
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
       
         
        //OBRAZAC ZA POPUNJAVANJE TABLICE DJELATNIKA

        
        out.println("<div class=\"index-intro\"> \n" +
"		<div class=\"wrapper\">\n" +
"			<h1>Djelatnici</h1>\n" +
"			<form action=\"JavaServletDjelatnici\" method=\"post\" novalidate>\n" +
"                            \n" +
"                                 \n"+
"                   <br><br>\n" +
"                <div>\n" +
"                   <input type=\"text\" id=\"ime\" name= \"ime\" placeholder='Ime'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"       \n" +
"                <div>\n" +
"                   <input type=\"text\" id=\"prezime\" name= \"prezime\" placeholder='Prezime'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"       \n" +
"                <div>\n" +
"                   <input type=\"text\" id=\"oib\" name= \"oib\" placeholder='Oib'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"               \n" +
"                            \n" +
"                 <div>\n" +
"                   <input type=\"text\" id=\"iban\" name= \"iban\" placeholder='Iban'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"                               \n" +
"                 <div>\n" +
"                   <input type=\"text\" id=\"radnoMjesto\" name= \"radnoMjesto\" placeholder='Radno mjesto'>\n" +
"                   <br><br>\n" +
"                </div>");
        
                  out.println("<button type=\"submit\" class=\"btn btn-primary\">Dodaj</button>");
        
        
//TABLICA
       //jdbc connection

       	try {
         //2b
	Class.forName("com.mysql.cj.jdbc.Driver");
	//3
        Connection con = DriverManager.getConnection//jdbc:mysql://localhost/djecjaigraonicahib
	("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
	
        
        String sql;
        sql="select * from djelatnik";
        Statement stmt = con.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery(sql);
        
        
 
        
       
        
             
        
        out.println("<table cellspacing='0' width='350px' border='1'>");
        out.println("<tr>");
        out.println("<td> Ime</td>");
        out.println("<td> Prezime</td>");
        out.println("<td> Oib</td>");
        out.println("<td> Iban</td>");
        out.println("<td> Radno mjesto</td>");
        out.println("<td> Uredi</td>");
        out.println("<td> Briši</td>");
        
        out.println("</tr>");
        
       
       
        
        while (rs.next()) {
            out.println("<tr>");
            out.println("<td>" + rs.getString("ime")+"</td>" );
            out.println("<td>" + rs.getString("prezime")+"</td>" );
            out.println("<td>" + rs.getString("oib")+"</td>");
            out.println("<td>" + rs.getString("iban")+"</td>");
            out.println("<td>" + rs.getString("radnoMjesto")+"</td>");
            out.println("<td>" +"<a href=urediDjelatnika.html"+ rs.getString("naziv")+"'>Uredi</a>" +"</td>");
            out.println("<td>" +"<a href=brisiDjelatnika.html"+ rs.getString("naziv")+" '>Briši</a>" +"</td>");
       out.println("</tr>");
        }
        
        out.println("<table>");
        
 out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
        
        


		} catch (ClassNotFoundException ex) {
              Logger.getLogger(novoselac.model.Djelatnik.class.getName());
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

