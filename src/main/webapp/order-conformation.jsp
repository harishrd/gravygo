<!DOCTYPE html>
<html>
<head>
    <title>Order Successful</title>
    <link rel="stylesheet" href="css/order-conformation.css">
</head>
<body>
    <div class="container">
        <% 
            Integer orderId = (Integer) session.getAttribute("orderId");
            if (orderId != null) { 
        %>
            <h1>Your Order is Confirmed!</h1>
            <p>Thank you for your order. Your order number is: <strong><span id="order-id">
                <%= orderId %>
            </span></strong></p>
            <p>You will receive an email confirmation with more details.</p>
            <a href="home.jsp" class="btn">Continue Shopping</a>
        <% 
            } else { 
        %>
            <h1>Order Not Found!</h1>
            <p>It seems there was an issue with your order. Please try again.</p>
            <a href="cart.jsp" class="btn">Return to Cart</a>
        <% 
            } 
        %>
    </div>
</body>
</html>