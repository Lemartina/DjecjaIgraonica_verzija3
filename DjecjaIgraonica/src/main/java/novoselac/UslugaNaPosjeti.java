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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/UslugaNaPosjeti")
public class UslugaNaPosjeti extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        String sifra = req.getParameter("sifra");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
             PreparedStatement pst = con.prepareStatement("SELECT * FROM usluga WHERE sifra = ?")) {

            pst.setString(1, sifra);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    out.print("<form action='DodajUsluguNaPosjetu' method='POST'>");
                    out.print("<table>");
                    out.print("<tr> <td>sifra</td>    <td> <input type='text' name='sifra' id='sifra' value='" + rs.getString("sifra") + "'/> </td> </tr>");
                    out.print("<tr> <td>cijena</td>    <td> <input type='text' name='cijena' id='cijena' value='" + rs.getString("cijena") + "'/> </td> </tr>");
                    out.print("<tr> <td>jedinicaMjere</td>    <td> <input type='text' name='jedinicaMjere' id='jedinicaMjere' value='" + rs.getString("jedinicaMjere") + "'/> </td> </tr>");
                    out.print("<tr> <td>kolicina</td>    <td> <input type='text' name='kolicina' id='kolicina' value='" + rs.getString("kolicina") + "'/> </td> </tr>");
                    out.print("<tr> <td>naziv</td>    <td> <input type='text' name='naziv' id='naziv' value='" + rs.getString("naziv") + "'/> </td> </tr>");
                    out.print("<tr>  <td colspan='2'> <input type='checkbox' value='O'/> </td> </tr>");
                    out.print("</table>");
                    out.print("</form>");
                }
            }
        } catch (SQLException ex) {
            out.println("<font color='red'> Odabir usluge nije uspjelo! Šta nije ok? </font>");
            ex.printStackTrace();
        }
    }
}