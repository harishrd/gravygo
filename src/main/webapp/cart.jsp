<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, com.gravygo.model.CartItem, com.gravygo.model.Cart" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Your Cart</title>
    <link rel="stylesheet" href="css/cart.css">
</head>
<body>
<header>
	<% Cart cart = (Cart) session.getAttribute("cart"); %>
    <div class="project-title">GravyGo</div>
    <nav class="nav-links">
        <% if (session.getAttribute("loggedInUser") != null) { 
        	if (cart != null && !cart.getItems().isEmpty()) {
        		%> <a href="menu.jsp">Menu</a> <%
        	} else {
        		%> <a href="home.jsp">Home</a> <%
        	}%> 
               <a href="order-history">Order History</a>
               <a href="logout">Logout</a>
           <% } else { %>
               <a href="login.jsp">Login</a>
           <% } %>
    </nav>
	</header>

	<h2>Your Cart</h2>
	
    <main class="cart-container">
        <%
            if (cart != null && !cart.getItems().isEmpty()) {
                Map<Integer, CartItem> cartItemsMap = cart.getItems();
        %>
            <table>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (CartItem item : cartItemsMap.values()) {
                    %>
                        <tr>
                            <td><%= item.getName() %></td>
                            <td>₹<%= item.getPrice() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td>₹<%= item.getSubTotal() %></td>
                            <td>
                                <!-- Form to decrease the quantity -->
                                <form action="cart" method="post" style="display:inline;">
                                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                                    <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                                    <input type="hidden" name="action" value="update">
                                    <button type="submit" <%= item.getQuantity() == 1 ? "disabled" : "" %>>-</button>
                                </form>

                                <!-- Form to increase the quantity -->
                                <form action="cart" method="post" style="display:inline;">
                                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                                    <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                                    <input type="hidden" name="action" value="update">
                                    <button type="submit">+</button>
                                </form>


                                <!-- Form to remove the item -->
                                <form action="cart" method="post" style="display:inline;">
                                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                                    <input type="hidden" name="action" value="remove">
                                    <button type="submit">Remove</button>
                                </form>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
			
            
            <!-- proceed to checkout -->
            <div class="cart-summary">
                
                <h3>Total Items: <%= cart.getItems().size() %></h3>

				<!-- add more items button -->
				<a href="menu?restaurantId=<%=session.getAttribute("restaurantId") %>">Want to add more?</a>

		   <%-- <p><strong>Total Price: ₹<%= cart.getItems().values().stream().mapToDouble(CartItem::getSubTotal).sum() %></strong></p> --%>
                <p><strong>Total Price: ₹<%= cart.getTotal()%></strong></p>
                <form action="checkout.jsp" method="post">
                    <button type="submit">Proceed to Checkout</button>
                </form>
            </div>
        <%
            } else {
        %>
            <p>Your cart is empty. <a href="home.jsp">See Available Restaurants</a></p>
        <%
            }
        %>
    </main>
</body>
</html>