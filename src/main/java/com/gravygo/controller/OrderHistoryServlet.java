package com.gravygo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gravygo.dao.OrderHistoryDAO;
import com.gravygo.daoimpl.OrderHistoryDAOImpl;
import com.gravygo.model.OrderHistory;
import com.gravygo.model.User;

@SuppressWarnings("serial")
@WebServlet("/order-history")
public class OrderHistoryServlet extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		OrderHistoryDAO orderHistoryDAOImpl = new OrderHistoryDAOImpl();
		
		User user = (User) session.getAttribute("loggedInUser");
		int userId = user.getUserId();
		
		List<OrderHistory> orderHistoryList = orderHistoryDAOImpl.getOrderHistoriesByUserId(userId);
		session.setAttribute("orderHistoryList", orderHistoryList);
		session.setAttribute("userId", userId);
		
		resp.sendRedirect("orderHistory.jsp");
		
	}
}
