import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Logincertification extends HttpServlet {
    // Hardcoded credentials
    private static final String CORRECT_USERID = "user_id";
    private static final String CORRECT_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the parameters from the request (form submission)
        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");

        // Set response content type
        response.setContentType("text/html");

        // Check if credentials match
        if (CORRECT_USERID.equals(userId) && CORRECT_PASSWORD.equals(password)) {
            // Redirect to menu page on successful login
            response.sendRedirect("menu.jsp");
        } else {
            // Display an error message on failed login
            PrintWriter out = response.getWriter();
            out.println("<h2>ユーザーIDまたはパスワードが間違っています。</h2>");
            out.println("<a href='login.html'>もう一度ログイン</a>");
        }
    }
}
