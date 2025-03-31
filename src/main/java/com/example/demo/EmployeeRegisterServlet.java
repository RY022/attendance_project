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

@WebServlet("/CustomerRegisterServlet")
public class EmployeeRegisterServlet extends HttpServlet {
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

		HttpSession session = request.getSession(true);
		Admin admin = (Admin) session.getAttribute("admin");

		Register register = new Register();

		register.employee_register(admin.getId(), customer_name, customer_address);

		Login login = new Login();
		List<Employee> employee = null;

		employee = login.getCustomerInfo(String.valueOf(admin.getId()));
		request.setAttribute("customer", employee);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher("loginpage.html");
		dispatcher.forward(request, response);
	}
}