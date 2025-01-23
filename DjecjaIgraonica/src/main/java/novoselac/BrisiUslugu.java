/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package novoselac;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet; // or javax.*





/**
 *
 * @author Administrator
 */


@WebServlet("/BrisiUslugu")
public class BrisiUslugu extends HttpServlet{
    Connection con;
    PreparedStatement pst;
    
    
     public void doGet(HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        
         
                 
        rsp.setContentType("text/html");
        PrintWriter out= rsp.getWriter();
       
         
      String naziv = req.getParameter("naziv");
        
//TABLICA
       //jdbc connection

       	try {
         //2b
	Class.forName("com.mysql.cj.jdbc.Driver");
	//3
         con = DriverManager.getConnection//jdbc:mysql://localhost/djecjaigraonicahib
	("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");                          
         pst = con.prepareStatement("delete from usluga where naziv = ?");
         pst.setString(4, naziv);
         pst.executeUpdate();
       
         out.println("<font color='green'> Obrisanooooo</font>");
               
         	} catch (ClassNotFoundException ex) {
			Logger.getLogger(novoselac.model.Usluga.class.getName())
                             //   .log(Level.SEVERE, null, ex)
                                ;
                        out.println(ex);
        
		}catch (SQLException ex) {
                    out.println("<font color ='red'>  Record Failed </font>");
                }
	
     }}
	
 
