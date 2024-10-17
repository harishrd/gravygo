<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gravygo.model.User, com.gravygo.model.Restaurant" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
	<%
	User user = (User)session.getAttribute("loggedInUser"); 
	
	@SuppressWarnings("unchecked")
	ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>)session.getAttribute("restaurants");
	%>
	
	<%if (user != null) {%>
		<h1>Welcome back <%out.println(user.getUsername()); %></h1>
		
		
	<%} else {%>
		<h1>Please Login</h1>
	<%} %>
</body>
</html>