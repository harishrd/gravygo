<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - GravyGo</title>
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="js/login.js" defer></script>
</head>
<body>
    <div class="login-container">
        <h1>Login</h1>

        <!-- Display error message if exists -->
        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { 
        %>
        <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <form action="login" method="post" id="loginForm">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
            
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
