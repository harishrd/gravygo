<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.gravygo.model.OrderHistory" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order History</title>
<style>
    /* General Styling */
	body {
	    font-family: Arial, sans-serif;
	    background-color: #f4f4f9;
	    margin: 0;
	    padding: 0;
	    color: #333;
	}
	
	.container {
	    background-color: #fff;
	    border-radius: 8px;
	    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	    margin: 30px auto;
	    max-width: 800px;
	    padding: 20px;
	}
	
	h1 {
	    text-align: center;
	    color: #e74c3c;
	}
	
	table {
	    width: 100%;
	    border-collapse: collapse;
	    margin-top: 20px;
	}
	
	table, th, td {
	    border: 1px solid #ccc;
	}
	
	th {
	    background-color: #388e3c;
	    color: #fff;
	    padding: 10px;
	}
	
	td {
	    text-align: center;
	    padding: 10px;
	}
	
	tr:nth-child(even) {
	    background-color: #f9f9f9;
	}
	
	tr:hover {
	    background-color: #f4f4f9;
	}
	
	.message {
	    text-align: center;
	    color: #333;
	    font-size: 16px;
	    margin: 20px 0;
	}
	
	a {
	    color: #388e3c;
	    text-decoration: none;
	    font-weight: bold;
	}
	
	a:hover {
	    color: #2c6e2f;
	}
	
	a:active {
	    color: #1b4d21;
	}
	
	td form {
	    margin: 0; /* Remove margin from form */
	    padding: 0; /* Remove padding from form */
	}
	
	.btn {
	    display: inline-block; /* Make button inline to avoid block level spacing */
	    background-color: #388e3c;
	    color: #fff;
	    padding: 5px 10px; /* Reduced padding to control button size */
	    text-decoration: none;
	    border-radius: 5px;
	    text-align: center;
	    transition: background-color 0.3s ease, color 0.3s ease;
	    vertical-align: middle; /* Align button vertically in the middle of the row */
	}
	
	.btn:hover {
	    background-color: #2c6e2f;
	    color: #fff; /* Ensures visibility */
	}
	
	.btn:active {
	    background-color: #1b4d21;
	    color: #fff; /* Ensures visibility */
	}
	
	/* Centering the 'Back to Home' button */
	a.btn {
	    display: block;  /* Ensure it behaves as a block element */
	    background-color: #388e3c;
	    color: #fff;
	    padding: 10px 20px;
	    text-decoration: none;
	    border-radius: 5px;
	    text-align: center;
	    width: fit-content;  /* Adjust width to fit the button text */
	    margin: 20px auto;   /* Automatically center the button horizontally */
	    transition: background-color 0.3s ease, color 0.3s ease;
	}
	
	a.btn:hover {
	    background-color: #2c6e2f;
	    color: #fff;
	}
	
	a.btn:active {
	    background-color: #1b4d21;
	    color: #fff;
	}
    
</style>
</head>
<body>
    <div class="container">
        <h1>Your Order History</h1>
        <%
            List<OrderHistory> orderHistoryList = (List<OrderHistory>) session.getAttribute("orderHistoryList");
            if (orderHistoryList != null && !orderHistoryList.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                    <th>Action</th> <!-- New Column -->
                </tr>
            </thead>
            <tbody>
                <%
                    for (OrderHistory order : orderHistoryList) {
                %>
                <tr>
                    <td><%= order.getOrderId() %></td>
                    <td><%= order.getOrderDate() %></td>
                    <td>&#8377; <%= order.getTotalAmount() %></td>
                    <td><%= order.getStatus() %></td>
                    <!-- New Action Column with Button -->
                    <td>
                        <form action="order-item" method="post">
                            <input type="hidden" name="orderId" value="<%= order.getOrderId() %>"/>
                            <button type="submit" class="btn">View Order Items</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            } else {
        %>
        <p class="message">You have no orders yet. Start ordering now!</p>
        <%
            }
        %>
        <a href="home.jsp" class="btn">Back to Home</a>
    </div>
</body>
</html>