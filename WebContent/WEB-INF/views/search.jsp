<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="it.dopamine.model.Prodotto"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Cerca</title>

	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Archivo+Black&display=swap" rel="stylesheet">

	<!-- CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/footer.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/catalogo.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/search.css">

</head>
<body>

	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<div class="benvenuto">
		<h2>Cerca nel catalogo</h2>
	</div>

	<div class="search-container">
		<form action="<%=request.getContextPath()%>/search" method="get" class="search-bar">
			<input type="text" name="query" placeholder="Cerca per nome, descrizione o categoria..."
				value="<%= request.getAttribute("query") != null ? request.getAttribute("query") : "" %>">
			<button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
		</form>
	</div>

	<div class="catalogo">

		<%
			List<Prodotto> risultati = (List<Prodotto>) request.getAttribute("risultati");
			String query = (String) request.getAttribute("query");
		%>

		<% if (risultati != null && !risultati.isEmpty()) { %>

			<h3><%= risultati.size() %> risultati per "<%= query %>"</h3>

			<div class="griglia-prodotti">
				<form action="<%=request.getContextPath()%>/productPage" method="POST" class="form-lattine">

					<% for (Prodotto p : risultati) { %>

						<div class="prodotto">
							<button type="submit" name="productName" value="<%= p.getNome() %>">
								<img src="<%=request.getContextPath()%><%= p.getUrl_img() %>" alt="<%= p.getNome() %>">
							</button>
							<p class="prodotto-nome"><%= p.getNome() %></p>
							<p class="prodotto-prezzo">€ <%= p.getPrezzo() %></p>
						</div>

					<% } %>

				</form>
			</div>

		<% } else if (query != null) { %>

			<p class="no-results">Nessun prodotto trovato per "<%= query %>".</p>

		<% } %>

	</div>

	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

</body>
</html>
