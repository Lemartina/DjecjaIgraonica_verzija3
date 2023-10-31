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

@WebServlet("/JavaServletDjeca")
public class JavaServletDjeca extends HttpServlet {

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
        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String oib= request.getParameter("oib");
        String imeRoditelja= request.getParameter("imeRoditelja");
    String telefonRoditelja= request.getParameter("telefonRoditelja");
         

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
        st.executeUpdate("insert into dijete(ime,prezime, oib, imeRoditelja, telefonRoditelja ) "
                + "values ('"+ime+"', '"+prezime+"', '"+oib+"', '"+imeRoditelja+"', '"+telefonRoditelja+"')");
                        
                        out.println("Podaci uspjepšno uneseni!");
                           out.println("<a href=WebObrazac>Nazad na dijete</a>");
                
			
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
