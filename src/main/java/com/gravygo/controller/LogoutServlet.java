package com.gravygo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false); // false to avoid creating a new session if one doesn't exist
        
        if (session != null) {
            session.invalidate(); // Invalidate the session to logout
        }
        
        // Redirect to login page or the home page, you can choose based on your needs
        resp.sendRedirect("login.jsp");  // Or use home.jsp if you prefer
    }
}