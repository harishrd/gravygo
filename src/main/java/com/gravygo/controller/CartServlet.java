package com.gravygo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gravygo.dao.MenuDAO;
import com.gravygo.daoimpl.MenuDAOImpl;
import com.gravygo.model.Cart;
import com.gravygo.model.CartItem;
import com.gravygo.model.Menu;

@SuppressWarnings("serial")
@WebServlet("/cart")
public class CartServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		if (cart == null) 
		{
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		
		String action = req.getParameter("action");
		if (action.equals("add")) 
		{
			addItemToCart(req, cart);
		} 
		else if (action.equals("update")) 
		{
//			updateCartItem(req, cart);
		} 
		else if (action.equals("remove")) 
		{
//			removeItemFromCart(req, cart);
		}
		
		session.setAttribute("cart", cart);
//		resp.sendRedirect("cart.jsp");
		
	}
	
	private void addItemToCart(HttpServletRequest req, Cart cart)
	{
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAO menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenuById(itemId);
		
		if (menuItem != null)
		{
			CartItem cartItem = new CartItem(
				itemId, 
				menuItem.getRestaurantId(),
				menuItem.getMenuName(),
				menuItem.getPrice(),
				quantity,
				quantity * menuItem.getPrice()
			);
			cart.addItem(cartItem);			
		}
		
				
	
	}
}


