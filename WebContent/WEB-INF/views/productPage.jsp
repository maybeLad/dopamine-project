<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.dopamine.model.Prodotto" %>
    

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/footer.css">
	
	<title>Dopamine Shop</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	<%
	    Prodotto prodotto = (Prodotto) request.getAttribute("productName");
	%>
	
	<%= prodotto.getNome() %>

	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>
</body>
</html>