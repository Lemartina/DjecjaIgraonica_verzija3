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

@WebServlet("/BrisiDjelatnika")
public class BrisiDjelatnika extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        rsp.setContentType("text/html");

        try (PrintWriter out = rsp.getWriter()) {
            String sifra = req.getParameter("sifra");  // Promijenjeno iz 'naziv' u 'sifra'

            // Provjeri je li šifra proslijeđena
            if (sifra == null || sifra.trim().isEmpty()) {
                out.println("<font color='red'> Šifra djelatnika nije proslijeđena. </font>");
                out.println("<a href=DjelatniciView>Nazad na djelatnike</a>");
                return;
            }

            // Učitaj JDBC driver (opcionalno, ovisno o okruženju)
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BrisiDjelatnika.class.getName()).log(Level.SEVERE, "MySQL JDBC Driver nije pronađen", ex);
                out.println("<font color='red'> Greška u konfiguraciji sustava. </font>");
                out.println("<a href=DjelatniciView>Nazad na djelatnike</a>");
                return;
            }

            // Izvrši brisanje
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
                 PreparedStatement pst = con.prepareStatement("DELETE FROM djelatnik WHERE sifra = ?")) {

                pst.setString(1, sifra);  // Koristimo 'sifra' umjesto 'naziv'
                int row = pst.executeUpdate();

                if (row > 0) {
                    out.println("<font color='green'> Djelatnik je uspješno obrisan. </font>");
                    out.println("<a href=DjelatniciView>Nazad na djelatnike</a>");
                } else {
                    out.println("<font color='red'> Nema djelatnika s tom šifrom. </font>");
                    out.println("<a href=DjelatniciView>Nazad na djelatnike</a>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BrisiDjelatnika.class.getName()).log(Level.SEVERE, "Greška pri brisanju djelatnika", ex);
                out.println("<font color='red'> Brisanje nije uspjelo jer postoje zavisni podaci. Djelatnik je već dodan na posjetu. </font>");
                out.println("<a href=DjelatniciView>Nazad na djelatnike</a>");
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        doGet(req, rsp);
    }
}