<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.dopamine.dao.OrdineDAO" %>
<%@ page import="it.dopamine.model.Utente" %>

<% Utente u = (Utente) session.getAttribute("utenteLoggato"); %>
<% OrdineDAO ordineDAO = new OrdineDAO(); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - User</title>

	<!-- ICON -->
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">

    <!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/footer.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/user.css">

</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<div class="user-container">

		<div class="user-info">
			<h1>Dati anagrafici</h1>

			<h2>Nome</h2>
			<p><%= u.getNome() %></p>

			<h2>Cognome</h2>
			<p><%= u.getCognome() %></p>

			<h2>Email</h2>
			<p><%= u.getEmail() %></p>
			
			<h2>Indirizzo</h2>
			<p>
				<% if(u.getIndirizzo()==null) { %>
					Nessun indirizzo inserito.
				<% } else u.getIndirizzo(); %>
			
			</p>
			<p><a href="test">Cambia indirizzo</a></p>
			
			<h2>Password</h2>
			<p><a href="test">Cambia password</a></p>
		</div>

		<div class="user-usage">
			<h1>Attivit&#224;</h1>

			<h2>Ordini effettuati</h2>
			<p class="usage-highlight"><%= ordineDAO.getNumberOfOrders(u.getId()) %></p>

		</div>

	</div>

	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

</body>
</html>