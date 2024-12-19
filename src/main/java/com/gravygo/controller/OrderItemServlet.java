package com.gravygo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gravygo.dao.MenuDAO;
import com.gravygo.dao.OrderItemDAO;
import com.gravygo.daoimpl.MenuDAOImpl;
import com.gravygo.daoimpl.OrderItemDAOImpl;
import com.gravygo.model.Menu;
import com.gravygo.model.OrderItem;
import com.gravygo.model.OrderItemDetails;

@SuppressWarnings("serial")
@WebServlet("/order-item")
public class OrderItemServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // Create DAO instances
        OrderItemDAO orderItemDAOImpl = new OrderItemDAOImpl();
        MenuDAO menuDAOImpl = new MenuDAOImpl();

        // Get the orderId from the request
        int orderId = Integer.parseInt(req.getParameter("orderId"));

        // Fetch order items from the database
        List<OrderItem> orderItemList = orderItemDAOImpl.getItemsByOrderId(orderId);

        // Create a list to hold OrderItemDetails (menuName, price, quantity, subtotal)
        List<OrderItemDetails> orderItemDetailsList = new ArrayList<>();

        // For each OrderItem, fetch the corresponding menu details
        for (OrderItem orderItem : orderItemList) {
            Menu menu = menuDAOImpl.getMenuById(orderItem.getMenuId());  // Fetch menu details using menuId

            // Create an OrderItemDetails object to hold the necessary fields
            OrderItemDetails orderItemDetails = new OrderItemDetails(
                menu.getMenuName(), 
                menu.getPrice(), 
                orderItem.getQuantity(), 
                orderItem.getSubTotal()
            );

            // Add the OrderItemDetails object to the list
            orderItemDetailsList.add(orderItemDetails);
        }

        // Store the orderItemDetailsList in the session
        session.setAttribute("orderItemDetailsList", orderItemDetailsList);

        // Redirect to the JSP page to display the order item details
        resp.sendRedirect("viewOrderItems.jsp");
    }
}