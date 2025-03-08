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

@WebServlet("/DjelatniciView")
public class DjelatniciView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Form for adding a new employee
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Djelatnici</title>");
        out.println("<style>");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th, td { border: 1px solid black; padding: 8px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("a { text-decoration: none; color: blue; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Djelatnici</h1>");
        out.println("<form action=\"JavaServletDjelatnici\" method=\"post\" novalidate>");
        out.println("<div>");
        out.println("<input type=\"text\" id=\"ime\" name=\"ime\" placeholder='Ime' required>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type=\"text\" id=\"prezime\" name=\"prezime\" placeholder='Prezime' required>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type=\"text\" id=\"oib\" name=\"oib\" placeholder='Oib' required>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type=\"text\" id=\"iban\" name=\"iban\" placeholder='Iban' required>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<div>");
        out.println("<input type=\"text\" id=\"radnoMjesto\" name=\"radnoMjesto\" placeholder='Radno mjesto' required>");
        out.println("<br><br>");
        out.println("</div>");
        out.println("<button type=\"submit\" class=\"btn btn-primary\">Dodaj</button>");
        out.println("</form>");
        out.println("<br>");

        // Table for displaying employees
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/djecjaigraonicahib", "root", ""
            );

            // Execute a query to fetch all employees
            String sql = "SELECT * FROM djelatnik";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // Display the table
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Ime</th>");
            out.println("<th>Prezime</th>");
            out.println("<th>Oib</th>");
            out.println("<th>Iban</th>");
            out.println("<th>Radno mjesto</th>");
            out.println("<th>Uredi</th>");
            out.println("<th>Briši</th>");
            out.println("</tr>");

            // Loop through the result set and display each row
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("ime") + "</td>");
                out.println("<td>" + rs.getString("prezime") + "</td>");
                out.println("<td>" + rs.getString("oib") + "</td>");
                out.println("<td>" + rs.getString("iban") + "</td>");
                out.println("<td>" + rs.getString("radnoMjesto") + "</td>");
                  
               out.println("<td><a href='vratiDjelatnika.html?id=" + rs.getInt("sifra") + "'>Uredi</a></td>");
               out.println("<td><a href='brisiDjelatnika.html?id=" + rs.getInt("sifra") + "'>Briši</a></td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br><a href='index.html'>Vrati se nazad na glavni izbornik</a>");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DjelatniciView.class.getName()).log(Level.SEVERE, "MySQL JDBC Driver not found", ex);
            out.println("<font color='red'>Greška: MySQL JDBC Driver nije pronađen.</font>");
        } catch (SQLException ex) {
            Logger.getLogger(DjelatniciView.class.getName()).log(Level.SEVERE, "Database error", ex);
            out.println("<font color='red'>Greška: Nije moguće dohvatiti podatke iz baze.</font>");
            out.println("<p>Detalji greške: " + ex.getMessage() + "</p>"); // Ispis dodatnih detalja
        } finally {
            // Close resources in finally block
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DjelatniciView.class.getName()).log(Level.SEVERE, "Error closing ResultSet", ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DjelatniciView.class.getName()).log(Level.SEVERE, "Error closing PreparedStatement", ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DjelatniciView.class.getName()).log(Level.SEVERE, "Error closing Connection", ex);
                }
            }
        }

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet za upravljanje djelatnicima";
    }
}