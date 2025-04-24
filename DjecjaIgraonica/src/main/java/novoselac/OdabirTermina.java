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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

@WebServlet("/OdabirTermina")
public class OdabirTermina extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Dohvati parametar "posjeta" iz zahtjeva
        String selectedPosjeta = request.getParameter("posjeta");

        // Spremi posjetu u sesiju
        if (selectedPosjeta != null && !selectedPosjeta.isEmpty()) {
            HttpSession session = request.getSession(true); // kreira novu ako ne postoji
            String[] posjeteArray = { selectedPosjeta };
            session.setAttribute("odabranePosjete", posjeteArray);
        }

        // Prikaz forme za unos termina
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Odabir termina</title></head><body>");
        out.println("<div class=\"index-intro\">");
        out.println("<div class=\"wrapper\">");
        out.println("<h1>Rezervacija termina za igraonicu</h1>");
        out.println("<h2>2. Odabir termina </h2>");

        out.println("<form action=\"JavaServletVrijeme\" method=\"post\">");

        out.println("<h3>Datum i vrijeme dolaska</h3>");
        out.println("<input type=\"date\" id=\"datumVrijemeDolaska\" name=\"datumVrijemeDolaska\"><br><br>");
        out.println("<input type=\"time\" id=\"vrijemeDolaska\" name=\"vrijemeDolaska\"><br><br>");

        out.println("<h3>Datum i vrijeme odlaska</h3>");
        out.println("<input type=\"date\" id=\"datumVrijemeOdlaska\" name=\"datumVrijemeOdlaska\"><br><br>");
        out.println("<input type=\"time\" id=\"vrijemeOdlaska\" name=\"vrijemeOdlaska\"><br><br>");

        out.println("<input type=\"text\" id=\"napomena\" name=\"napomena\" placeholder='Napomena'><br><br>");

        out.println("<button type=\"submit\" class=\"btn btn-primary\">Dodaj</button>");
        out.println("</form>");
        out.println("</div></div>");
        out.println("</body></html>");

        // --- (Dodatni kod ako želiš prikaz iz baze podataka) ---
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost/djecjaigraonicahib", "root", "");

            String sql = "SELECT * FROM posjeta";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

           
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(novoselac.OdabirTermina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            out.println("<p style='color:red;'>Greška: " + ex.getMessage() + "</p>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Odabir termina za posjetu i spremanje u sesiju";
    }
}
