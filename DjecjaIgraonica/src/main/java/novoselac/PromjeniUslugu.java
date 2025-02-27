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

@WebServlet("/PromjeniUslugu")
public class PromjeniUslugu extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        String sifra = req.getParameter("sifra");
        String cijena = req.getParameter("cijena");
        String jedinicaMjere = req.getParameter("jedinicaMjere");
        String kolicina = req.getParameter("kolicina");
        String naziv = req.getParameter("naziv");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
             PreparedStatement pst = con.prepareStatement("UPDATE usluga SET sifra = ?, cijena = ?, jedinicaMjere = ?, kolicina = ? WHERE naziv = ?")) {

            pst.setString(1, sifra);
            pst.setString(2, cijena);
            pst.setString(3, jedinicaMjere);
            pst.setString(4, kolicina);
            pst.setString(5, naziv);

            int row = pst.executeUpdate();

            if (row > 0) {
                out.println("<font color='green'> Uređivanje je uspjelo! </font>");
                 out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
            } else {
                out.println("<font color='red'> Nema usluge s tim nazivom </font>");
                 out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
            }
        } catch (SQLException ex) {
            out.println("<font color='red'> Uređivanje usluge nije uspjelo! </font>");
            out.println("<a href=index.html>vrati se nazad na glavni izbornik</a>");
            ex.printStackTrace();
            
            
        }
    }
}