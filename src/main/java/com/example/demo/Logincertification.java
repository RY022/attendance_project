import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Logincertification extends HttpServlet {

    private static final String CORRECT_USERID = "user_id";
    private static final String CORRECT_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("user_id");
        String password = request.getParameter("password");


        response.setContentType("text/html");


        if (CORRECT_USERID.equals(userId) && CORRECT_PASSWORD.equals(password)) {

            response.sendRedirect("menu.jsp");
        } else {

            PrintWriter out = response.getWriter();
            out.println("<h2>ユーザーIDまたはパスワードが間違っています。</h2>");
            out.println("<a href='login.html'>もう一度ログイン</a>");
        }
    }
}