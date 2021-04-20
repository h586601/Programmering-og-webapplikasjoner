<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Innlogging</title>
</head>
<body>

<p>Gi inn passord</p>

<form action="InnloggingServlet" method="post">
	
	<p><input type="password" name="passord"></p>
	<p><input type="submit" value="Logg inn"></p>
	
</form>

</body>
</html>