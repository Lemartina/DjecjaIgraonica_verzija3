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
import java.util.logging.Level;
import java.util.logging.Logger;

/** treba dodati id usluge u posjetu
 *
 * @author Administrator
 */
@WebServlet("/UslugaView")
public class UslugaView extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UslugaView</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Pozdrav, ovo je UslugaView servlet!</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Generiranje HTML-a
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Usluga View</title>");
        
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css'>"); // Dodajte Font Awesome
        out.println("</head>");
        out.println("<body>");

        // Obrazac za unos usluga
        out.println("<div class='index-intro'>");
        out.println("<div class='wrapper'>");
        out.println("<h1>Unos usluga</h1>");
        out.println("<form action='JavaServlet' method='post'>");
        out.println("<div>");
        out.println("<input type='text' id='naziv' name='naziv' placeholder='Naziv'>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type='text' id='jedinicaMjere' name='jedinicaMjere' placeholder='Jedinica mjere'>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type='text' id='cijena' name='cijena' placeholder='Cijena'>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type='text' id='kolicina' name='kolicina' placeholder='Količina'>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<button type='submit' class='btn btn-primary'>Dodaj</button>");
        out.println("</form>");
        out.println("</div>");
        out.println("</div>");

        // Tablica za prikaz usluga
        try {
            // Učitavanje JDBC drivera
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Povezivanje s bazom podataka
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");

            // SQL upit za dohvat podataka
            String sql = "SELECT * FROM usluga";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Generiranje tablice
            out.println("<table cellspacing='0' width='400px' border='1'>");
            out.println("<tr>");
            out.println("<th>Cijena</th>");
            out.println("<th>Jedinica mjere</th>");
            out.println("<th>Količina</th>");
            out.println("<th>Naziv</th>");
            out.println("<th>Uredi</th>");
            out.println("<th>Briši</th>");
            out.println("</tr>");

            // Popunjavanje tablice podacima iz baze
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("cijena") + "</td>");
                out.println("<td>" + rs.getString("jedinicaMjere") + "</td>");
                out.println("<td>" + rs.getString("kolicina") + "</td>");
                out.println("<td>" + rs.getString("naziv") + "</td>");
                
                out.println("<td><a href='VratiUslugu?naziv=" + rs.getString("naziv") + "'><i class='fas fa-edit'></i> Uredi</a></td>");
                out.println("<td><a href='BrisiUslugu?naziv=" + rs.getString("naziv") + "'><i class='fas fa-trash'></i> Briši</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");

            // Link za povratak na glavni izbornik
            out.println("<br><br>");
            out.println("<a href='index.html'><i class='fas fa-home'></i> Vrati se nazad na glavni izbornik</a>");

            // Zatvaranje resursa
            rs.close();
            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            out.println("<font color='red'>Greška: Nije pronađen JDBC driver.</font>");
            Logger.getLogger(UslugaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println("<font color='red'>Greška: Problem s bazom podataka.</font>");
            Logger.getLogger(UslugaView.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet za prikaz i upravljanje uslugama.";
    }
}