<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error - GravyGo</title>
    
    <style>
        /* Body Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9; /* Light Grey Background */
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Error Container Styling */
        .error-container {
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            width: 300px;
            text-align: center;
        }

        /* Error Message Styling */
        .error-message {
            color: #e74c3c; /* Vibrant Red */
            font-size: 16px;
            background-color: #f8d7da; /* Light Red Background */
            border: 1px solid #f5c6cb; /* Light Red Border */
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 5px;
        }

        /* Go Back Link Styling */
        a {
            display: inline-block;
            margin-top: 15px;
            text-decoration: none;
            color: #388e3c; /* Tomato Leaf Green */
            font-weight: bold;
        }

        a:hover {
            color: #2c6e2f; /* Darker Green */
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error</h1>

        <!-- Display error message -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
        <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <!-- Dynamic Go Back Link based on sourcePage -->
        <%
            String sourcePage = (String) request.getAttribute("sourcePage");
            String goBackLink = "index.jsp"; // Default link (Home page)

            if ("login".equals(sourcePage)) {
                goBackLink = "login.jsp"; // Go back to login page
            } else if ("signup".equals(sourcePage)) {
                goBackLink = "signup.jsp"; // Go back to signup page
            }
        %>

        <a href="<%= goBackLink %>">Go back to the previous page</a>
    </div>
</body>
</html>
