package com.example.demo;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("loginpage.html");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String userid = request.getParameter("user_id");
		String password = request.getParameter("password");

		Login login = new Login();
		Admin admin = login.check(userid, password);

		if(admin.isLogin_flag()) {
			System.out.println("ログイン成功");

			HttpSession admin_session = request.getSession(true);
			admin_session.setAttribute("user_id", admin);

			List<Employee> employee = null;
			employee = login.getCustomerInfo(user_id);
			request.setAttribute("employee", employee);

			RequestDispatcher dispatcher =
					request.getRequestDispatcher("loginpage.html");
			dispatcher.forward(request, response);
		} else {
			System.out.println("ログイン失敗");
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("loginpage.html");
			dispatcher.forward(request, response);
		}
	}
}