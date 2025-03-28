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

@WebServlet("/PromjeniDjelatnika")
public class PromjeniDjelatnika extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        String sifra = req.getParameter("sifra");
        String ime = req.getParameter("ime");
        String prezime = req.getParameter("prezime");
        String oib = req.getParameter("oib");
        String iban = req.getParameter("iban");
        String radnoMjesto = req.getParameter("rednoMjesto");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
             PreparedStatement pst = con.prepareStatement("UPDATE djelatnik SET sifra = ?, ime = ?, prezime = ?, oib = ?, iban = ?, radnoMjesto = ? WHERE sifra = ?")) {

            pst.setString(1, sifra);
            pst.setString(2, ime);
            pst.setString(3, prezime);
            pst.setString(4, oib);
            pst.setString(5, iban);
            pst.setString(5, radnoMjesto);

            int row = pst.executeUpdate();

            if (row > 0) {
                out.println("<font color='green'> Uređivanje je uspjelo! </font>");
                 out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
            } else {
                out.println("<font color='red'> Nema djelatnika s tim nazivom </font>");
                 out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
            }
        } catch (SQLException ex) {
            out.println("<font color='red'> Uređivanje djelatnika nije uspjelo! </font>");
            out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
            ex.printStackTrace();
            
            
        }
    }
}