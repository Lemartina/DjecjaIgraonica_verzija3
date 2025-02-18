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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */


@WebServlet("/PromjeniUslugu")
public class PromjeniUslugu extends HttpServlet{
    

   Connection con;
   PreparedStatement pst;
   int row;
   
 
    public void doGet(HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        
        rsp.setContentType("text/html");
        PrintWriter out= rsp.getWriter();
       
         
     
//TABLICA
       //jdbc connection

       	try {
         //2b
	Class.forName("com.mysql.cj.jdbc.Driver");
	//3
        con = DriverManager.getConnection//jdbc:mysql://localhost/djecjaigraonicahib
	("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
	
             String sifra = req.getParameter("sifra");
             String cijena = req.getParameter("cijena");
             String jedinicaMjere  = req.getParameter("jedinicaMjere");
             String kolicina = req.getParameter("kolicina");
             String naziv = req.getParameter("naziv");
             
             pst = con.prepareStatement("update usluga set sifra = ?, cijena = ?, jedinicaMjere = ?, jedinicaMjere = ?where naziv = ?");
             pst.setString(1, sifra);
             pst.setString(2, cijena);
             pst.setString(3, jedinicaMjere);
             pst.setString(4, kolicina);
             pst.setString(5, naziv);
             
             row = pst.executeUpdate();
             
              out.println("<font color='green'>  Record Updateeeedd   </font>");
   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(novoselac.model.Usluga.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
 
        }

    }
  
}
