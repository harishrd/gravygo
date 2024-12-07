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
</head><body>
    <%
        // Get the logged-in user from session
        User user = (User) session.getAttribute("loggedInUser");

        // If the user is logged in, display the restaurant list
        if (user != null) {
            // Get the RestaurantDAO object and fetch all restaurants
            /* RestaurantDAO restaurantDAO = new RestaurantDAOImpl();
            ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) restaurantDAO.getAllRestaurants(); */
            ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) session.getAttribute("restaurants");

            // Display the list of available restaurants
            if (restaurants != null && !restaurants.isEmpty()) {
                out.println("<h2>Available Restaurants:</h2>");
                out.println("<ul>");
                for (Restaurant restaurant : restaurants) {
                    out.println("<li>" + restaurant.getRestaurantName() + " - " + restaurant.getCuisineType() + "</li>");
                }
                out.println("</ul>");
            } else {
                out.println("<p>No restaurants available at the moment.</p>");
            }
        } else {
            out.println("<h1><a href='index.jsp'>Please Login</a></h1>");
        }
    %>
</body>

</html>

