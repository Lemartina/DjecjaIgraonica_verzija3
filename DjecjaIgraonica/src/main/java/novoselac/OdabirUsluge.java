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

@WebServlet("/OdabirUsluge")
public class OdabirUsluge extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        // Get usluga from request if available
        String usluga = request.getParameter("usluga");
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Odabir usluga</title>");
        out.println("<style>");
        out.println("table {border-collapse: collapse; width: 80%; margin: 20px auto;}");
        out.println("th, td {border: 1px solid #ddd; padding: 8px; text-align: left;}");
        out.println("th {background-color: #f2f2f2;}");
        out.println(".container {width: 90%; margin: 0 auto;}");
        out.println(".button {background-color: #4CAF50; color: white; padding: 10px 15px; text-decoration: none; margin: 10px;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Rezervacija termina za igraonicu</h1>");
        out.println("<h2>3. Odabir usluga</h2>");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
            
            // Create a form to submit selected services
            out.println("<form method='post' action='DodajUsluguNaPosjetu'>");
            if (usluga != null) {
                out.println("<input type='hidden' name='usluga' value='" + usluga + "'>");
            }
            
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Cijena</th>");
            out.println("<th>Jedinica mjere</th>");
            out.println("<th>Količina</th>");
            out.println("<th>Naziv</th>");
            out.println("<th>Odaberi</th>");
           out.println("</tr>");
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usluga");
            
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("cijena") + "</td>");
                out.println("<td>" + rs.getString("jedinicaMjere") + "</td>");
                out.println("<td>" + rs.getString("kolicina") + "</td>");
                out.println("<td>" + rs.getString("naziv") + "</td>");
                out.println("<td><input type='checkbox' name='selectedServices' value='" + 
                           rs.getString("sifra") + "'></td>");
                //out.println("<td><input type='number' name='kolicina_" + rs.getString("sifra") + 
                  //         "' value='1' min='1' style='width: 50px;'></td>");
                out.println("</tr>");
            }
            
            out.println("</table>");
            out.println("<input type='submit' class='button' value='Dodaj odabrane usluge'>");
            out.println("</form>");
            out.println("<a href='index.html' class='button'>Vrati se na glavni izbornik</a>");
            
            con.close();
        } catch (ClassNotFoundException ex) {
            out.println("<p style='color:red'>Greška: Database driver nije pronađen</p>");
            ex.printStackTrace();
        } catch (SQLException ex) {
            out.println("<p style='color:red'>Greška pri pristupu bazi podataka: " + ex.getMessage() + "</p>");
            ex.printStackTrace();
        }
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to doGet if needed, or implement separate POST handling
        doGet(request, response);
    }
}