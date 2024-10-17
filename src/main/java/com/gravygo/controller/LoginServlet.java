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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDAO userDAO = new UserDAOImpl();
		
		User user = userDAO.getUser(email); // the code in UserDAOImpl will throw an exception if user is null, so here user will not be null
		
		session = req.getSession();
		
		if (user != null && password.equals(user.getPassword())) {
			session.setAttribute("loggedInUser", user);	
			resp.sendRedirect("getAllRestaurants"); // call a servlet which adds all the restaurants into the session and then calls home.jsp
//			resp.sendRedirect("home.jsp");
		}
		else
		{
			session.setAttribute("errorMessage", "Invalid credentials. Please try again.");
			resp.sendRedirect("failure.jsp"); // rather we'll display a message saying invalid credentials
		}
	}
}
