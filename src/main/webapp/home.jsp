<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.gravygo.model.User, com.gravygo.model.Restaurant"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.gravygo.dao.RestaurantDAO"%>
<%@ page import="com.gravygo.daoimpl.RestaurantDAOImpl"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/home.css">
<title>Home</title>
</head>
<body>
    <header>
	    <div class="project-title">GravyGo</div>
	    <div class="nav-links">
	        <% 
		        User loggedInUser = (User) session.getAttribute("loggedInUser");
		        if (loggedInUser != null) { %>
		            <%-- <span>Welcome, <%= loggedInUser.getUsername() %>!</span> --%>
		            <a href="cart.jsp">Cart</a>
		            <a href="order-history">Order History</a>
		            <a href="logout">Logout</a>
	        <% } else { %>
	            <a href="login.jsp">Login</a>
	            <a href="register.jsp">Register</a>
	        <% } %>
	    </div>
	</header>	
    
    <h2>Featured Restaurants</h2>
    <section class="restaurant-list">
	   <%
	       // Get the RestaurantDAO object and fetch all restaurants
	       ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) session.getAttribute("restaurants");
	
	       // Display the list of available restaurants
	       if (restaurants != null && !restaurants.isEmpty()) {
	   %>
			<div class="restaurant-container">
			    <% for (Restaurant restaurant : restaurants) { %>
			        <div class="restaurant-card">
			            <img src="images/<%= restaurant.getImgPath() %>" alt="Image of <%= restaurant.getRestaurantName() %>">
			            <h3><%= restaurant.getRestaurantName() %></h3>
			            <p>
			                Cuisine: <%= restaurant.getCuisineType() %><br>
			                Delivery Time: <%= restaurant.getDeliveryTime() %> mins
			            </p>
			            <a href="menu?restaurantId=<%= restaurant.getRestaurantId() %>" class="view-menu">View Menu</a>
			        </div>
			    <% } %>
			</div>
	   <%
	       } else {
	           out.println("<p>No restaurants available at the moment.</p>");
	       }
	   %>
	</section>
</body>

</html>

