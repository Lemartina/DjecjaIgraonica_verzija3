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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/BrisiUslugu")
public class BrisiUslugu extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        rsp.setContentType("text/html");

        try (PrintWriter out = rsp.getWriter()) {
            String naziv = req.getParameter("naziv");

            // Provjeri je li naziv proslijeđen
            if (naziv == null || naziv.trim().isEmpty()) {
                out.println("<font color='red'> Naziv usluge nije proslijeđen. </font>");
                out.println("<a href=UslugaView>Nazad na usluge</a>");
                return;
            }

            // Učitaj JDBC driver (opcionalno, ovisno o okruženju)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BrisiUslugu.class.getName()).log(Level.SEVERE, "MySQL JDBC Driver nije pronađen", ex);
                out.println("<font color='red'> Greška u konfiguraciji sustava. </font>");
                out.println("<a href=UslugaView>Nazad na usluge</a>");
                return;
            }

            // Izvrši brisanje
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
                 PreparedStatement pst = con.prepareStatement("DELETE FROM usluga WHERE naziv = ?")) {

                pst.setString(1, naziv);
                int row = pst.executeUpdate();

                if (row > 0) {
                    out.println("<font color='green'> Usluga je uspješno obrisana. </font>");
                    out.println("<a href=UslugaView>Nazad na usluge</a>");
                } else {
                    out.println("<font color='red'> Nema usluge s tim nazivom. </font>");
                    out.println("<a href=UslugaView>Nazad na usluge</a>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BrisiUslugu.class.getName()).log(Level.SEVERE, "Greška pri brisanju usluge", ex);
                out.println("<font color='red'> Brisanje nije uspjelo jer postoje zavisni podaci. Usluga je već dodana na posjetu. </font>");
                out.println("<a href=UslugaView>Nazad na usluge</a>");
                
                /*
Provjera zavisnih podataka u bazi
SELECT a.naziv, c.datumVrijemeDolaska
FROM usluga a
INNER JOIN uslugaposjeta b ON a.sifra = b.usluga
INNER JOIN posjeta c ON b.posjeta = c.sifra
ORDER BY c.datumVrijemeDolaska DESC;
                */
                
                
                out.println("<a href=UslugaView>Nazad na usluge</a>");
                
                
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        doGet(req, rsp);
    }
}