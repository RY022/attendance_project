import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/attendance_users?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user_id");
        String pass = request.getParameter("password");

        // Set response content type
        response.setContentType("text/html");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user);
                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    // Get the password hash from the database
                    String storedHash = result.getString("password");

                    // Verify password (using password_verify equivalent in Java)
                    if (verifyPassword(pass, storedHash)) {
                        // Store username in session
                        HttpSession session = request.getSession();
                        session.setAttribute("username", result.getString("user_id"));

                        // Redirect to dashboard
                        response.sendRedirect("dashboard.jsp");
                    } else {
                        // Invalid credentials
                        response.getWriter().println("無効なユーザー名またはパスワードです。");
                    }
                } else {
                    // Invalid credentials
                    response.getWriter().println("無効なユーザー名またはパスワードです。");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("データベースエラー: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("接続エラー: " + e.getMessage());
        }
    }

    // Verify password using password_hash equivalent in Java
    private boolean verifyPassword(String password, String hashedPassword) {
        // Assuming the password is hashed with bcrypt
        // Use the BCrypt library for Java to verify the password hash
        // The method bcrypt.verify() can be used here
        return BCrypt.checkpw(password, hashedPassword);  // BCrypt is a popular library for hashing and verifying passwords
    }
}