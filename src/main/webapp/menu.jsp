<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.gravygo.model.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu Details</title>
    <link rel="stylesheet" type="text/css" href="css/menu.css">
</head>
<body>
    <header>
        <h1>GravyGo</h1>
        <nav>
            <a href="home.jsp">Home</a>
            <a href="cart">Cart</a>
            <a href="orderhistory">Order History</a>
            <a href="logout">Logout</a>
        </nav>
    </header>

    <main class="menu-container">
        <%
            List<Menu> menuList = (List<Menu>) session.getAttribute("menuList");
            if (menuList != null && !menuList.isEmpty()) {
                for (Menu menuItem : menuList) {
        %>
            <div class="menu-card">
                <img src="images/<%= menuItem.getImgPath() %>" alt="<%= menuItem.getMenuName() %>">
                <h2><%= menuItem.getMenuName() %></h2>
                <p><%= menuItem.getDescription() %></p>
                <p class="price">₹<%= menuItem.getPrice() %></p>
                <p class="<%= menuItem.isAvailable() ? "availability" : "unavailable" %>">
                    <%= menuItem.isAvailable() ? "Available" : "Currently Unavailable" %>
                </p>
                
                <form action="cart" method="post">
                	<input type="hidden" name="itemId" value=<%=menuItem.getMenuId()%>>
         Quantity: 	<input type="number" name="quantity" value="1" min="1">
					<input type="submit" value="Add">
					<input type="hidden" name="action" value="add"> 
                </form>
                
                <%-- <a href="cart?menuId=<%= menuItem.getMenuId() %>" class="btn add-to-cart">Add</a> --%>
            
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