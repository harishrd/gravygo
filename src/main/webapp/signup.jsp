<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SignUp Page - GravyGo</title>
    <link rel="stylesheet" type="text/css" href="css/signup.css">
    <script src="js/signup.js" defer></script>
</head>
<body>
    <div class="signup-container">
        <h1>Sign Up</h1>
        
        <!-- Error Message Display -->
        <% String errorMessage = (String) request.getAttribute("errorMessage");
           if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <!-- SignUp Form -->
        <form action="register" method="post">
            <div class="input-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>
            </div>

            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <div class="input-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" maxlength="10" placeholder="Enter your phone number" required>
            </div>

            <div class="input-group">
                <label for="address">Address:</label>
                <input type="text" id="address" name="address" placeholder="Enter your address" required>
            </div>

            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <button type="submit">Sign Up</button>
        </form>
    </div>
</body>

</html>
