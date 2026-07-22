<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.dopamine.model.Utente" %>

<nav class="header-container">
	<div id="logo">
		<a href="<%= request.getContextPath()%>"><img alt="Dopamine log" src="<%= request.getContextPath()%>/images/logo.png"></a>
	</div>
	
	<ul class="center-side">
		<li><a href="<%= request.getContextPath()%>/catalogue">Catalogo</a></li>
		<li><a href="<%= request.getContextPath()%>/news">Novità</a></li>
		<li><a href="<%= request.getContextPath()%>/reviews">Recensioni</a></li>
		<li><a href="<%= request.getContextPath()%>/info">Contatti</a></li>
	</ul>
	
	<ul class="right-side">
		<% 
			Utente uLoggato = (Utente) session.getAttribute("utenteLoggato");
			boolean isAdmin = (uLoggato != null && uLoggato.isAdmin());
		%>
		
		<% if (isAdmin) { %>
			<li>
				<a href="<%= request.getContextPath()%>/adminDashboard" title="Gestione Database">
					<i class="fa-solid fa-database" style="color: #e67e22;"></i>
				</a>
			</li>
		<% } %>

		<li><a href="<%= request.getContextPath()%>/search"><i class="fa-solid fa-magnifying-glass"></i></a></li>
		<li>
			<% if(uLoggato != null) {%>
				<a href="<%= request.getContextPath()%>/user">
					<i style="color: green;" class="fa-solid fa-user"></i>
				</a>
			<% }else { %>
				<a href="<%= request.getContextPath()%>/login">
					<i class="fa-solid fa-user"></i>
				</a>
			<% } %>
		</li>
		<li><a href="<%= request.getContextPath()%>/cart"><i class="fa-solid fa-cart-shopping"></i></a></li>
	</ul>	
</nav>