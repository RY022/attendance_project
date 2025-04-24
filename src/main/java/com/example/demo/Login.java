package com.example.demo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.getRequestDispatcher("loginpage.html").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String id = request.getParameter("user_id");
    String password = request.getParameter("password");

    String path = "menupage.html";

    try {
        String url = "jdbc:mysql://localhost:3306/attendance_project";
        String user = "root";
        String pass = "chaki8044";

        String sql = "SELECT id FROM users WHERE id=? AND password=?";

        Class.forName("org.postgresql.Driver");
        try (Connection connection = DriverManager.getConnection(url, user, pass);
                PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, id);
            pstmt.setString(2, password);

            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                request.setAttribute("user_id", res.getString("id"));

                path = "menupage.html";
            } else {
                request.setAttribute("loginFailure", "ログインに失敗しました");

                path = "loginpage.htm";
            }
        }
    }catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }

    RequestDispatcher rd = request.getRequestDispatcher(path);
    rd.forward(request, response);
    }
}