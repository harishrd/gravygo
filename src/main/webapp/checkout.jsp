<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, com.gravygo.model.CartItem, com.gravygo.model.Cart" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link rel="stylesheet" href="css/checkout.css">
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

<h2>Checkout</h2>

<div class="checkout-container">
    <div class="order-summary">
        <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null && !cart.getItems().isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (CartItem item : cart.getItems().values()) {
                    %>
                        <tr>
                            <td><%= item.getName() %></td>
                            <td>₹<%= item.getPrice() %></td>
                            <td><%= item.getQuantity() %></td>
                            <td>₹<%= item.getSubTotal() %></td>
                        </tr>
                    <%
                        }
                    %>
                    <tr>
                        <th></th>
                        <th></th>
                        <th>Total Items:</th>
                        <th><%= cart.getItems().size() %></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th></th>
                        <th>Grand Total:</th>
                        <th>₹
                            <%= cart.getTotal() %>
                        </th>
                    </tr>
                </tbody>
            </table>
		    </div>
		
		    <div class="order-details">
		        <h3>Delivery Details</h3>
		        <form action="order" method="post">
		            <label for="deliveryAddress">Delivery Address:</label>
		            <textarea id="deliveryAddress" name="deliveryAddress" required></textarea>
		
		            <label for="phoneNumber">Phone Number:</label>
		            <input type="tel" id="phoneNumber" name="phoneNumber" required>
		
		            <h3>Payment Details</h3>
		            <p>**Mode of Payment:**</p>
		            <input type="radio" id="cod" name="paymentMode" value="COD" required>
		            <label for="cod">Cash On Delivery</label>
		            <br>
		            <input type="radio" id="debit" name="paymentMode" value="Debit Card" required>
		            <label for="debit">Debit Card</label>
		            <br>
		            <input type="radio" id="credit" name="paymentMode" value="Credit Card" required>
		            <label for="credit">Credit Card</label>
		            <br>
		            <button type="submit" class="place-order-btn">Place Order</button>
		        </form>
		    </div>
		    
        <%
            } else {
        %>
            <p>Your cart is empty. <a href="home.jsp">Go to Home</a></p>
        <%
            }
        %>
		    
		    
</div>

</body>
</html>