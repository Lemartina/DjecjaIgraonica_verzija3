/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package novoselac;

import java.sql.*;//1
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Administrator
 */

@WebServlet("/JavaServletDjelatnici")
public class JavaServletDjelatnici extends HttpServlet {

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
        
        
        //POPUNJAVANJE TABLICE USLUGA
        
        out.println("<div class=\"index-intro\"> \n" +
"		<div class=\"wrapper\">\n" +
"			<h1>Djelatnici</h1>\n" +
"			<form action=\"JavaServletDjelatnici\" method=\"post\" novalidate>\n" +
"                            \n" +
"                                 \n" +
"                   <textarea name=\"message\"> Popis djelatnika </textarea>\n" +
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
"                   <input type=\"text\" id=\"radnoMjeato\" name= \"radnoMjesto\" placeholder='Radno mjesto'>\n" +
"                   <br><br>\n" +
"                </div>");
                
                  out.println("<button type=\"submit\" class=\"btn btn-primary\">Dodaj</button>");
  
        
        
        
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String oib= request.getParameter("oib");
        String iban= request.getParameter("iban");
    String radnoMjesto= request.getParameter("radnoMjesto");
         

       //jdbc connection
       //https://www.youtube.com/watch?v=y_YxwyYRJek
       //https://www.youtube.com/watch?v=5vzCjvUwMXg
       	try {
         //2b
	Class.forName("com.mysql.cj.jdbc.Driver");
	//3
        Connection con = DriverManager.getConnection//jdbc:mysql://localhost/djecjaigraonicahib
	("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
	Statement st = con.createStatement();
        st.executeUpdate("insert into djelatnik(ime,prezime, oib, iban, radnoMjesto ) "
                + "values ('"+ime+"', '"+prezime+"', '"+oib+"', '"+iban+"', '"+radnoMjesto+"')");
                        
                        out.println("Podaci uspjepšno uneseni!");
                           out.println("<a href=djelatnici.html>Nazad na djelanika</a>");
                
			
st.close();
con.close();


		} catch (Exception e) {
			out.println(e);

         
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
