package com.gravygo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gravygo.dao.UserDAO;
import com.gravygo.daoimpl.UserDAOImpl;
import com.gravygo.model.User;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet 
{
	private HttpSession session;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			UserDAO userDAO = new UserDAOImpl();
			User user = userDAO.getUser(email);

			session = req.getSession();

			if (user != null && password.equals(user.getPassword())) {
				session.setAttribute("loggedInUser", user);

				resp.sendRedirect("home");  // Redirect to home page on successful login
			} else {
				// Forward to error page with custom error message and sourcePage parameter
				req.setAttribute("errorMessage", "Invalid credentials. Please try again.");
				req.setAttribute("sourcePage", "login"); // Indicate the source page
				req.getRequestDispatcher("/error.jsp").forward(req, resp);  // Forward to centralized error page
			}
		} catch (Exception e) {
			// Handle unexpected exceptions and forward to error page
			req.setAttribute("errorMessage", "An unexpected error occurred during login.");
			req.setAttribute("exception", e);
			req.setAttribute("sourcePage", "login"); // Indicate the source page
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}
}
