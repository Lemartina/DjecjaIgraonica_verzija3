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

/**
 *
 * @author Administrator
 */
@WebServlet("/DjecaView")
public class DjecaView extends HttpServlet {

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
        //out.println("<link rel='stylesheet' type='text/css' href='style.css'>"); // Dodajte vaš CSS
        out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css'>"); // Dodajte Font Awesome
        out.println("</head>");
        out.println("<body>");

       

        // Tablica za prikaz usluga
        try {
            // Učitavanje JDBC drivera
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Povezivanje s bazom podataka
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");

            // SQL upit za dohvat podataka
            String sql = "SELECT * FROM dijete";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // Generiranje tablice
            out.println("<table cellspacing='0' width='400px' border='1'>");
            out.println("<tr>");
            out.println("<th>Ime</th>");
            out.println("<th>Ime roditelja</th>");
            out.println("<th>Oib</th>");
            out.println("<th>Prezime</th>");
            out.println("<th>Telefon roditelja</th>");
         
            out.println("</tr>");

            // Popunjavanje tablice podacima iz baze
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("ime") + "</td>");
                out.println("<td>" + rs.getString("imeRoditelja") + "</td>");
                out.println("<td>" + rs.getString("oib") + "</td>");
                out.println("<td>" + rs.getString("prezime") + "</td>");
                out.println("<td>" + rs.getString("telefonRoditelja") + "</td>");
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
        return "Servlet za prikaz i upravljanje djecom.";
    }
}