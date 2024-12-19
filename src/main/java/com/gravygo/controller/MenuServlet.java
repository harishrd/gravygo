package com.gravygo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gravygo.dao.MenuDAO;
import com.gravygo.dao.RestaurantDAO;
import com.gravygo.daoimpl.MenuDAOImpl;
import com.gravygo.daoimpl.RestaurantDAOImpl;
import com.gravygo.model.Menu;

@SuppressWarnings("serial")
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int restaurantId = Integer.parseInt(req.getParameter("restaurantId"));
            
            MenuDAO menuDAO = new MenuDAOImpl();
            RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
            List<Menu> menuList = menuDAO.getMenusByRestaurantId(restaurantId);
            
            HttpSession session = req.getSession();
            
            if (!menuList.isEmpty()) {
                session.setAttribute("menuList", menuList);
                session.setAttribute("restaurantId", restaurantId);
                session.setAttribute("restaurantName", restaurantDAO.getRestaurantNameById(restaurantId));
                resp.sendRedirect("menu.jsp");
            } else {
                req.setAttribute("errorMessage", "No menu available for the selected restaurant.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            req.setAttribute("errorMessage", "An unexpected error occurred while fetching the Menu.");
            req.setAttribute("exception", e);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}