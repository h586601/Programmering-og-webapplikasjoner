<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<!-- Fra https://purecss.io/ -->
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css">
<title>Deltagerliste</title>
</head>
<body>
	<h2>Deltagerliste</h2>
	<table class="pure-table">
		<tr bgcolor="#cccccc">
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
		
		<c:forEach items="${deltagere}" var="d">
		<c:choose>
		<c:when test="${deltager.mobil eq d.mobil}">
			<tr bgcolor="#aaffaa">
		</c:when>
		<c:otherwise>
		<tr bgcolor="#ffffff">
		</c:otherwise>
		</c:choose>
		
		<c:choose>
		<c:when test="${d.kjonn eq 'kvinne'}">
		 <td align="center">&#9792;</td>
		</c:when>
		<c:when test="${d.kjonn eq 'mann'}">
		 <td align="center">&#9794;</td>
		</c:when>
		</c:choose>
		
		<td>${d.fornavn} ${d.etternavn}</td>
		<td>${d.mobil}</td>		
		
		</c:forEach>
	</table>
	<p>
		<a href="utlogging">Ferdig</a>
	</p>
</body>
</html>