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
        PrintWriter out = rsp.getWriter();

        String naziv = req.getParameter("naziv");

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/djecjaigraonicahib", "root", "");
             PreparedStatement pst = con.prepareStatement("DELETE FROM usluga WHERE naziv = ?")) {

            pst.setString(1, naziv);
            int row = pst.executeUpdate();

            if (row > 0) {
                out.println("<font color='green'> Obrisanooooo </font>");
            } else {
                out.println("<font color='red'> Nema usluge s tim nazivom </font>");
            }
        } catch (SQLException ex) {
            out.println("<font color='red'> Record Failed </font>");
            ex.printStackTrace();
        }
    }
}