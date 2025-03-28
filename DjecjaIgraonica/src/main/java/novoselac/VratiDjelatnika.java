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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Administrator
 */
@WebServlet("/VratiDjelatnika")
public class VratiDjelatnika extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws ServletException, IOException {
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        String sifra = req.getParameter("sifra");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
             PreparedStatement pst = con.prepareStatement("SELECT * FROM djelatnik WHERE sifra = ?")) {

            pst.setString(1, sifra);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    out.print("<form action='PromjeniDjelatnika' method='POST'>");
                    out.print("<table>");
                    out.print("<tr> <td>Šifra</td>    <td> <input type='text' name='sifra' id='sifra' value='" + rs.getString("sifra") + "'/> </td> </tr>");
                    out.print("<tr> <td>Ime</td>    <td> <input type='text' name='ime' id='cijena' value='" + rs.getString("ime") + "'/> </td> </tr>");
                    out.print("<tr> <td>Prezime</td>    <td> <input type='text' name='prezime' id='prezime' value='" + rs.getString("prezime") + "'/> </td> </tr>");
                    out.print("<tr> <td>OIB</td>    <td> <input type='text' name='oib' id='oib' value='" + rs.getString("oib") + "'/> </td> </tr>");
                    out.print("<tr> <td>IBAN</td>    <td> <input type='text' name='iban' id='iban' value='" + rs.getString("iban") + "'/> </td> </tr>");
                     out.print("<tr> <td> Radnom mjesto</td>    <td> <input type='text' name='radnoMjesto' id='radnoMjesto' value='" + rs.getString("radnoMjesto") + "'/> </td> </tr>");
                    out.print("<tr>  <td colspan='2'> <input type='submit' value='Uredi'/> </td> </tr>");
                    out.print("</table>");
                    out.print("</form>");
                }
            }
        } catch (SQLException ex) {
            out.println("<font color='red'> Uređivanje djelatnika nije uspjelo! Šta nije ok? </font>");
            ex.printStackTrace();
        }
    }
}