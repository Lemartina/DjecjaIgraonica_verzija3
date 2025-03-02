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
import java.sql.SQLException;

@WebServlet("/JavaServletDjelatnici")
public class JavaServletDjelatnici extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String ime = request.getParameter("ime");
        String prezime = request.getParameter("prezime");
        String oib = request.getParameter("oib");
        String iban = request.getParameter("iban");
        String radnoMjesto = request.getParameter("radnoMjesto");

        // Validacija korisničkog unosa
        if (ime == null || ime.trim().isEmpty() ||
            prezime == null || prezime.trim().isEmpty() ||
            oib == null || oib.trim().isEmpty() ||
            iban == null || iban.trim().isEmpty() ||
            radnoMjesto == null || radnoMjesto.trim().isEmpty()) {
            out.println("<font color='red'> Svi podaci su obavezni. </font>");
            return;
        }

        // SQL upit s PreparedStatement
        String sql = "INSERT INTO djelatnik (ime, prezime, oib, iban, radnoMjesto) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, ime);
            pst.setString(2, prezime);
            pst.setString(3, oib);
            pst.setString(4, iban);
            pst.setString(5, radnoMjesto);

            int row = pst.executeUpdate();

            if (row > 0) {
                out.println("Podaci uspješno uneseni!");
                out.println("<a href='DjelatnikView'> Nazad na djelatnika </a>");
            } else {
                out.println("<font color='red'> Unos podataka nije uspio. </font>");
            }

        } catch (SQLException e) {
            out.println("<font color='red'> Greška pri unosu podataka: " + e.getMessage() + " </font>");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}