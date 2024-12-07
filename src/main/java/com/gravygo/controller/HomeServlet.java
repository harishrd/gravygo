package com.gravygo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gravygo.dao.RestaurantDAO;
import com.gravygo.daoimpl.RestaurantDAOImpl;
import com.gravygo.model.Restaurant;

@SuppressWarnings("serial")
@WebServlet("/home")
public class HomeServlet extends HttpServlet
{
	HttpSession session;
	RestaurantDAO restaurantDAO;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try 
		{
			session = req.getSession();
			restaurantDAO = new RestaurantDAOImpl();
			List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();

			session.setAttribute("restaurants", restaurants);
			

//			System.out.println("Redirecting to: " + req.getContextPath() + "/home.jsp");
			resp.sendRedirect(req.getContextPath() + "/home.jsp");
		}
		catch (Exception e) 
		{
			// Handle unexpected exceptions and forward to error page
			req.setAttribute("errorMessage", "A server issue occurred while processing your request. Please try again later.");
			req.setAttribute("exception", e);
			req.setAttribute("sourcePage", "login"); // Indicate the source page
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}


	}
}
