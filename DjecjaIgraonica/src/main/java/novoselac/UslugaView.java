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


@WebServlet("/UslugaView")
public class UslugaView extends HttpServlet{
    

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
       
         
        //OBRAZAC ZA POPUNJAVANJE TABLICE USLUGA

        
        out.println("<div class=\"index-intro\"> \n" +
"		<div class=\"wrapper\">\n" +
"			<h1>Unos usluga</h1>\n" +
"		<form action=\"JavaServlet\" method =\"post\">\n" +
"                            \n" +
"                                 <!--dodavanje tablice-->\n" +
"                         \n" +
"                   \n" +
"                   \n" +
"                   <!--</textarea>-->\n" +
"                   <br><br>\n" +
"                <div>\n" +
"                   <input type=\"naziv\" id=\"naziv\" name= \"naziv\" placeholder='Naziv'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"       \n" +
"                <div>\n" +
"                   <input type=\"jedinicaMjere\" id=\"jedinicaMjere\" name= \"jedinicaMjere\" placeholder='Jedinica mjere'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"       \n" +
"                <div>\n" +
"                   <input type=\"cijena\" id=\"cijena\" name= \"cijena\" placeholder='Cijena'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"               \n" +
"                            \n" +
"                 <div>\n" +
"                   <input type=\"kolicina\" id=\"kolicina\" name= \"kolicina\" placeholder='Količina'>\n" +
"                   <br><br>\n" +
"                </div>\n" +
"                   ");
        
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
        out.println("<td> Uredi</td>");
        out.println("<td> Briši</td>");
        
        out.println("</tr>");
        
       
       
        
        while (rs.next()) {
            out.println("<tr>");
            out.println("<td>" + rs.getString("cijena")+"</td>" );
            out.println("<td>" + rs.getString("jedinicaMjere")+"</td>" );
            out.println("<td>" + rs.getString("kolicina")+"</td>");
            out.println("<td>" + rs.getString("naziv")+"</td>");
            out.println("<td>" +"<a href=PromjeniUslugu?naziv"+ rs.getString("naziv")+"'>Uredi</a>" +"</td>");
            out.println("<td>" +"<a href=BrisiUslugu?naziv"+ rs.getString("naziv")+" '>Briši</a>" +"</td>");
       out.println("</tr>");
        }
        
        out.println("<table>");
        
 out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
        
   
        
        


		} catch (ClassNotFoundException ex) {
              Logger.getLogger(novoselac.model.Usluga.class.getName());
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

