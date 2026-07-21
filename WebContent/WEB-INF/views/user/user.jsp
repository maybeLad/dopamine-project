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
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/footer.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/user.css">
	
	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	

</head>
<body>
	<header>
		<jsp:include page="../navigation/header.jsp"></jsp:include>
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
				<% } else { %> <%= u.getIndirizzo() %> 
				<% }	 %>
			
			</p>
			<p>
				<% if(u.getIndirizzo()==null){ %>
					<a href="<%=request.getContextPath()%>/setAddress">Aggiungi indirizzo</a>
				<%} else { %>
					<a href="<%=request.getContextPath()%>/setAddress">Cambia indirizzo</a>
				<%} %>
				</p>
			
			<h2>Metodo di pagamento</h2>
			<p><% if("Carta".equals(u.getMetodo_pagamento())){%>
				Metodo di pagamento: <%= u.getMetodo_pagamento() %> <br>
				Numero carta: ****.****.****.<%=u.getCarta_ultime_4_cifre() %> <br>
				Scadenza: <%= u.getScadenza_carta() %>
			
				<% } else { %>
					<%= u.getMetodo_pagamento() %>
				<% } %>
			</p>
			
			<p><a href="<%=request.getContextPath()%>/changePaymentMethod">Cambia metodo di pagamento</a>
			
			<h2>Password</h2>
			<p><a href="<%=request.getContextPath()%>/changePassword">Cambia password</a></p>
		</div>

		<div class="user-usage">
			<h1>Attivit&#224;</h1>

			<h2>Ordini effettuati</h2>
			<p class="usage-highlight"><%= ordineDAO.getNumberOfOrders(u.getId()) %></p>

		</div>

	</div>
	
	<div class="navigation-buttons">
	    <a href="<%=request.getContextPath()%>"><i class="fa-solid fa-arrow-left-long"></i> HOME PAGE</a>
	    <span class="separator">•</span>
	    <a href="<%=request.getContextPath()%>/login?type=logout">LOGOUT</a>
	</div>


	<footer>
		<jsp:include page="../navigation/footer.jsp"></jsp:include>
	</footer>

</body>
</html>