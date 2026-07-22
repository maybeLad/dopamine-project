<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.dopamine.dao.OrdineDAO" %>
<%@ page import="it.dopamine.model.Ordine" %>
<%@ page import="it.dopamine.model.Utente" %>

<% Utente u = (Utente) session.getAttribute("utenteLoggato");
	if (u==null) {
		response.sendRedirect(request.getContextPath() + "/login");
		return;
	}
	OrdineDAO ordineDAO = new OrdineDAO();
	List<Ordine> ordini = ordineDAO.getOrdiniByUtente(u.getId());
%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - I miei ordini</title>

	<!-- ICON -->
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">

    <!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/footer.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/orders.css">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>
	<header>
		<jsp:include page="../navigation/header.jsp"></jsp:include>
	</header>

	<div class="ordini-container">

		<h1>I miei ordini</h1>

		<% if (ordini == null || ordini.isEmpty()) { %>
			<div class="ordini-empty">
				<i class="fa-solid fa-box-open"></i>
				<p>Non hai ancora effettuato nessun ordine.</p>
				<a href="<%=request.getContextPath()%>" class="ordini-shop-link">Vai allo shop</a>
			</div>
		<% } else { %>

			<table class="ordini-table">
				<thead>
					<tr>
						<th>N. Ordine</th>
						<th>Data</th>
						<th>Stato</th>
						<th>Totale</th>
					</tr>
				</thead>
				<tbody>
					<% for (Ordine o : ordini) {
						String statoClass = o.getStato().toLowerCase().replace(" ", "_");
					%>
						<tr>
							<td data-label="N. Ordine">#<%= o.getId() %></td>
							<td data-label="Data"><%= o.getData_ordine() %></td>
							<td data-label="Stato">
								<span class="stato-badge stato-<%= statoClass %>">
									<%= o.getStato() %>
								</span>
							</td>
							<td data-label="Totale">€ <%= String.format("%.2f", o.getPrezzo_totale()) %></td>
						</tr>
					<% } %>
				</tbody>
			</table>

		<% } %>

	</div>

	<div class="navigation-buttons">
	    <a href="<%=request.getContextPath()%>/user"><i class="fa-solid fa-arrow-left-long"></i> AREA PERSONALE</a>
	    <span class="separator">•</span>
	    <a href="<%=request.getContextPath()%>/login?type=logout">LOGOUT</a>
	</div>

	<footer>
		<jsp:include page="../navigation/footer.jsp"></jsp:include>
	</footer>

</body>
</html>