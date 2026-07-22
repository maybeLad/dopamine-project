<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="it.dopamine.model.Prodotto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Inserisci recensione</title>

	<!-- ICON -->
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">

    <!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/footer.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/form.css">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>

	<header>
		<jsp:include page="../navigation/header.jsp"></jsp:include>
	</header>


	<div id="container">

		<div class="form-card">

			<h1>Lascia una recensione</h1>

			<form id="createReviewForm" method="POST">
				<select id="prodotto" name="prodotto" required>
				
				<% List<Prodotto> l = (List<Prodotto>)request.getAttribute("prodotti");
					for(Prodotto p : l){	%>
						<option value="<%= p.getId() %>"><%= p.getNome() %></option>
					<% } %>
				</select>
				
				<textarea id="descrizione" name="descrizione" placeholder="Scrivi la tua recensione..." rows="5" required></textarea>

				<select id="voto" name="voto" required>
					<option value="">Seleziona un voto</option>
					<option value="1">1 - Pessimo</option>
					<option value="2">2 - Scarso</option>
					<option value="3">3 - Sufficiente</option>
					<option value="4">4 - Buono</option>
					<option value="5">5 - Ottimo</option>
				</select>

				<input type="submit" value="Invia recensione">

			</form>

			<p id="messaggio"></p>

		</div>

	</div>

	<footer>
		<jsp:include page="../navigation/footer.jsp"></jsp:include>
	</footer>

</body>
</html>