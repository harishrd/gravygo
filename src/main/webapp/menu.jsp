<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.List, com.gravygo.model.Menu, com.gravygo.model.Cart, com.gravygo.model.CartItem" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Details</title>
    <link rel="stylesheet" href="css/menu.css">
</head>
<body>
    <header>
        <div class="project-title">GravyGo</div>
        <div class="nav-links">
            <% if (session.getAttribute("loggedInUser") != null) { %>
            	<a href="home.jsp">Home</a>
            	<a href="cart.jsp">Cart</a>
                <a href="order-history">Order History</a>
                <a href="logout">Logout</a>
            <% } else { %>
                <a href="login.jsp">Login</a>
            <% } %>
        </div>
    </header>

    <h2><%=session.getAttribute("restaurantName") %>'s Menu</h2>
    <main class="menu-container">
        <%
            List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
            Cart cart = (Cart) session.getAttribute("Cart");
            Map<Integer, CartItem> cartItemsMap = (cart != null) ? cart.getItems() : null;

            if (menuList != null && !menuList.isEmpty()) {
                for (Menu menuItem : menuList) {
                    int menuId = menuItem.getMenuId();
                    CartItem cartItem = (cartItemsMap != null && cartItemsMap.containsKey(menuId)) 
                        ? cartItemsMap.get(menuId) 
                        : null;
        %>
            <div class="menu-card">
                <img src="images/menus/<%= menuItem.getImgPath() %>" alt="<%= menuItem.getMenuName() %>">
                <h2><%= menuItem.getMenuName() %></h2>
                <p><%= menuItem.getDescription() %></p>
                <p class="price">₹<%= menuItem.getPrice() %></p>
                <p class="<%= menuItem.isAvailable() ? "availability" : "unavailable" %>">
                    <%= menuItem.isAvailable() ? "Available" : "Currently Unavailable" %>
                </p>
				
				<% if (cartItem != null) { %>
				    <!-- If the item is already in the cart -->
				    <div class="cart-controls">
				        <form action="cart" method="post" style="display:inline;">
				            <input type="hidden" name="itemId" value="<%= menuId %>">
				            <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() + 1 %>">
				            <input type="hidden" name="action" value="update">
				            <!-- hidden input of the source page for appropriate redirection -->
							<input type="hidden" name="source" value="menu">
				            <button type="submit">+</button>
				        </form>
				        <span>Quantity: <%= cartItem.getQuantity() %></span>
				        <form action="cart" method="post" style="display:inline;">
				            <input type="hidden" name="itemId" value="<%= menuId %>">
				            <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() - 1 %>">
				            <input type="hidden" name="action" value="update">
				            <!-- hidden input of the source page for appropriate redirection -->
							<input type="hidden" name="source" value="menu">
							<button type="submit" <%= cartItem.getQuantity() == 1 ? "disabled" : "" %>>-</button>
				        </form>
				    </div>
				<% } else { %>
				    <!-- If the item is not in the cart -->
				    <form action="cart" method="post">
				        <input type="hidden" name="itemId" value="<%= menuId %>">
				        <input type="hidden" name="action" value="add">
				        <input type="hidden" name="quantity" value="1">
				        <!-- hidden input of the source page for appropriate redirection -->
						<input type="hidden" name="source" value="menu">
						<button type="submit" class="btn add-to-cart">Add</button>
				    </form>
				<% } %>
            </div>
        <% 
                }
            } else {
        %>
            <p>No menu details available. Please try again.</p>
        <% } %>
    </main>
</body>
</html>