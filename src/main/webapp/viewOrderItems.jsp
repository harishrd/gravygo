<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.gravygo.model.OrderItemDetails" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order Items</title>
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
            margin: 0;
            padding: 0;
        }

        .btn {
            display: block; /* Make button behave like a block-level element */
            background-color: #388e3c;
            color: #fff;
            padding: 10px 20px; /* Consistent padding */
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            width: fit-content;
            margin: 20px auto; /* Center the button horizontally */
            transition: background-color 0.3s ease, color 0.3s ease;
        }

        .btn:hover {
            background-color: #2c6e2f;
            color: #fff;
        }

        .btn:active {
            background-color: #1b4d21;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="container">
    <h1>Order Items</h1>
    <%
        List<OrderItemDetails> orderItemDetailsList = (List<OrderItemDetails>) session.getAttribute("orderItemDetailsList");
        if (orderItemDetailsList != null && !orderItemDetailsList.isEmpty()) {
    %>
    <table>
        <thead>
            <tr>
                <th>Menu Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Sub Total</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (OrderItemDetails itemDetails : orderItemDetailsList) {
            %>
            <tr>
                <td><%= itemDetails.getMenuName() %></td>
                <td>&#8377; <%= itemDetails.getPrice() %></td>
                <td><%= itemDetails.getQuantity() %></td>
                <td>&#8377; <%= itemDetails.getSubTotal() %></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        } else {
    %>
    <p class="message">No items found for this order.</p>
    <%
        }
    %>
    <div class="btn-container">
        <a href="order-history?userId=<%= session.getAttribute("userId") %>" class="btn">Back to Order History</a>
    </div>
</div>
</body>
</html>