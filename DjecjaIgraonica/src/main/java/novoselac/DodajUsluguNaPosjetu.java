package novoselac;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/DodajUsluguNaPosjetu")
public class DodajUsluguNaPosjetu extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rsp) 
            throws ServletException, IOException {

        rsp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = rsp.getWriter();

        // Dohvaćanje posjete iz sessiona
        HttpSession session = req.getSession(false);
        String[] selectedVisits = null;

        if (session != null) {
            selectedVisits = (String[]) session.getAttribute("odabranePosjete");
        }

        if (selectedVisits == null || selectedVisits.length == 0) {
            out.println("<font color='red'>Greška: Posjete nisu pronađene u sesiji!</font><br>");
            out.println("<a href='OdabirPosjeteZaUslugu'>Povratak na odabir posjete</a>");
            return;
        }

        // Dohvaćanje odabranih usluga iz forme
        String[] selectedServices = req.getParameterValues("selectedServices");

        if (selectedServices == null || selectedServices.length == 0) {
            out.println("<font color='red'>Greška: Niste odabrali niti jednu uslugu!</font><br>");
            out.println("<a href='OdabirUslugaZaPosjetu'>Povratak na odabir usluga</a>");
            return;
        }

        try (
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO uslugaposjeta (posjeta, usluga) VALUES (?, ?)")
        ) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            int totalAdded = 0;

            for (String posjeta : selectedVisits) {
                for (String usluga : selectedServices) {
                    pst.setString(1, posjeta);
                    pst.setString(2, usluga);
                    pst.addBatch();
                    totalAdded++;
                }
            }

            int[] results = pst.executeBatch();

            boolean allSuccess = true;
            for (int result : results) {
                if (result == PreparedStatement.EXECUTE_FAILED) {
                    allSuccess = false;
                    break;
                }
            }

            if (allSuccess) {
                out.println("<div style='margin:20px; padding:20px; border:1px solid green;'>");
                out.println("<h3 style='color:green;'>Operacija uspješno završena!</h3>");
                out.println("<p>Ukupno dodanih veza posjeta-usluga: " + totalAdded + "</p>");
                out.println("<p>Odabrane posjete: " + String.join(", ", selectedVisits) + "</p>");
                out.println("<p>Odabrane usluge: " + String.join(", ", selectedServices) + "</p>");
                out.println("</div>");
            } else {
                out.println("<font color='orange'>Neke veze posjeta-usluga nisu uspješno dodane</font><br>");
            }

            out.println("<a href='index.html'>Povratak na glavni izbornik</a>");

        } catch (ClassNotFoundException ex) {
            out.println("<font color='red'>Greška: JDBC drajver nije pronađen!</font><br>");
            Logger.getLogger(DodajUsluguNaPosjetu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            out.println("<font color='red'>Greška pri radu s bazom podataka: " +
                ex.getMessage() + "</font><br>");
            Logger.getLogger(DodajUsluguNaPosjetu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
