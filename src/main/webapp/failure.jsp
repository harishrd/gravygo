<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Failed</title>
</head>
<body>
	<h2>Something went wrong</h2>
	<h3><%out.println(session.getAttribute("errorMessage")); %></h3>
</body>
</html>