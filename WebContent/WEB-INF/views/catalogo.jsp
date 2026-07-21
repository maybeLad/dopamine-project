<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.List" %>
<%@ page import="it.dopamine.model.Prodotto" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Catalogo</title>
	
	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">
	
	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Archivo+Black&display=swap" rel="stylesheet">
	
	<!-- CSS -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/catalogo.css">

</head>
<body>

	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<div class="benvenuto">
		<h2>Benvenuto nel catalogo!</h2>
	</div>

	<div class="catalogo">

		<%
			Map<String, List<Prodotto>> catalogo = (Map<String, List<Prodotto>>) request.getAttribute("catalogo");

			if (catalogo != null) {
				for (String categoria : catalogo.keySet()) {
					List<Prodotto> prodotti = catalogo.get(categoria);
		%>

					<h3><%= categoria %></h3>

					<div class="griglia-prodotti">
						<form action="<%=request.getContextPath()%>/productPage" method="POST" class="form-lattine">

							<%
								for (Prodotto prodotto : prodotti) {
							%>

							<div class="prodotto">
								<button type="submit" name="productName" value="<%= prodotto.getNome() %>">
									<img src="<%=request.getContextPath()%><%= prodotto.getUrl_img() %>" alt="<%= prodotto.getNome() %>">
								</button>
								<p><%= prodotto.getNome() %> <br> € <%= prodotto.getPrezzo() %></p>
							</div>

							<%
								}
							%>

						</form>
					</div>

		<%
				}
			} else {
				out.println("<p>Nessun prodotto trovato nel catalogo.</p>");
			}
		%>

	</div>

</body>
</html>