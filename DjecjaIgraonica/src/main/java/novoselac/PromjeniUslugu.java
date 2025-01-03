/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package novoselac;

import jakarta.servlet.ServletException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class PromjeniUslugu extends  HttpServlet{
    
    /**
     *
     * @param reg
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    @Override
      public void doGet(HttpServletRequest reg, HttpServletResponse rsp)
            throws ServletException, IOException {
        
        rsp.setContentType("text/html");
        PrintWriter out= rsp.getWriter();
        
        try {
            //  String eid= reg.getParameter("id");
            
            //      	try {
            //2b
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PromjeniUslugu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //3
            Connection con = DriverManager.getConnection//jdbc:mysql://localhost/djecjaigraonicahib
                ("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(PromjeniUslugu.class.getName()).log(Level.SEVERE, null, ex);
        }
                
         
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
        
                  out.println("<button type=\"submit\" class=\"btn btn-primary\">Spremi promjenu</button>");
        
        
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
       
       out.println("</tr>");
        }
        
        out.println("<table>");
        
 out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
        
   
        
        


		} catch (ClassNotFoundException ex) {
              Logger.getLogger(novoselac.model.Usluga.class.getName());
			out.println(ex);

         
		}
	catch(SQLException ex){
            out.println("<font color= 'red'> neuspjelp </fornt>");
        }
  
      }
  }
    

