
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
@WebServlet("/VratiUslugu")
public class VratiUslugu extends HttpServlet {    
    

 
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;


        public void doGet(HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        String naziv = req.getParameter("naziv");
        
       try {
         //2b
	Class.forName("com.mysql.cj.jdbc.Driver");
            //3
          con = DriverManager.getConnection//jdbc:mysql://localhost/djecjaigraonicahib
                ("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
           
            pst = con.prepareStatement("select * from usluga where naziv = ? ");
          
         pst.setString(5, naziv);
         rs = pst.executeQuery();
           
           while(rs.next())
           {
               out.print("<form action='PromjeniUslugu' method='POST'");
                out.print("<table");
               
                 out.print("<tr> <td>sifra</td>    <td> <input type='text' name ='sifra' id='sifra' value= '" + rs.getString("sifra") + "'/> </td> </tr>");
                 out.print("<tr> <td>cijena</td>    <td> <input type='text' name ='cijena' id='cijena' value= '" + rs.getString("cijena") + "'/> </td> </tr>");
                 out.print("<tr> <td>jedinicaMjere</td>    <td> <input type='text' name ='jedinicaMjere' id='jedinicaMjere' value= '" + rs.getString("jedinicaMjere") + "'/> </td> </tr>");
                  out.print("<tr> <td>kolicina</td>    <td> <input type='text' name ='kolicina' id='kolicina' value= '" + rs.getString("kolicina") + "'/> </td> </tr>"); 
                 out.print("<tr> <td>naziv</td>    <td> <input type='text' name ='naziv' id='naziv' value= '" + rs.getString("naziv") + "'/> </td> </tr>");out.print("<tr>  <td colspan ='2'> <input type='submit'  value= 'Edit'/> </td> </tr>");
                out.print("<tr>  <td colspan ='2'> <input type='submit'  value= 'Uredi'/> </td> </tr>");
                 
               out.print("</table");
               out.print("</form");
               
           }
          
       } catch (ClassNotFoundException ex) {
            Logger.getLogger(novoselac.model.Usluga.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
            out.println("<font color='red'>  Uređivanje usluge nije uspjelo! šta nije ok?  </font>");
 
        }
    }

}