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
@WebServlet("/register")
public class RegisterServlet extends HttpServlet 
{
	private HttpSession session;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String phoneNumber = req.getParameter("phoneNumber");
        String address = req.getParameter("address");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAOImpl();

        // Check if user already exists by email
        User existingUser = userDAO.getUser(email);

        if (existingUser != null) {
            // User already exists
            req.setAttribute("errorMessage", "Email is already registered. Please use a different email.");
            req.setAttribute("sourcePage", "signup");  // Indicate the source page
            req.getRequestDispatcher("error.jsp").forward(req, resp); // Forward to error page
        } else {
            // Save new user
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPhoneNumber(phoneNumber);
            newUser.setAddress(address);
            newUser.setPassword(password);

            int result = userDAO.addUser(newUser);
            if (result > 0) {
                // Create a session and store the user in the session
                session = req.getSession();
                session.setAttribute("loggedInUser", newUser);  // Set the user as logged in

                resp.sendRedirect("home"); // Redirect to home page on successful registration
            } else {
                req.setAttribute("errorMessage", "Registration failed. Please try again.");
                req.setAttribute("sourcePage", "signup");  // Indicate the source page
                req.getRequestDispatcher("/error.jsp").forward(req, resp); // Forward to error page
            }
        }
    }
}
