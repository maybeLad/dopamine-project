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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/styles/navigation/catalogo.css">

</head>
<body>

	<header>
		<jsp:include page="../navigation/header.jsp"></jsp:include>
	</header>

	<div class="benvenuto">
		<h2>Benvenuto nel catalogo!</h2>
	</div>

	<div class="main-container">

	    <aside class="filtri-sidebar">
	        <h3>Filtra Prodotti</h3>
	        
	        <form action="<%=request.getContextPath()%>/catalogue" method="GET">
	            <div class="filtro-gruppo">
	                <label for="categoria">Categoria:</label>
	                <select name="categoria" id="categoria">
	                    <option value="tutte">Tutte le categorie</option>
	                    <option value="Abbigliamento" <%= "Abbigliamento".equals(request.getParameter("categoria")) ? "selected" : "" %>>Abbigliamento</option>
	                    <option value="Borracce" <%= "Borracce".equals(request.getParameter("categoria")) ? "selected" : "" %>>Borracce</option>
	                    <option value="Creatina" <%= "Creatina".equals(request.getParameter("categoria")) ? "selected" : "" %>>Creatina</option>
	                    <option value="Proteine" <%= "Proteine".equals(request.getParameter("categoria")) ? "selected" : "" %>>Proteine</option>
	                </select>
	            </div>
	
	            <div class="filtro-gruppo">
	                <label for="prezzoMin">Prezzo Min (€):</label>
	                <input type="number" name="prezzoMin" id="prezzoMin" value="<%= request.getParameter("prezzoMin") != null ? request.getParameter("prezzoMin") : "" %>">
	            </div>
	
	            <div class="filtro-gruppo">
	                <label for="prezzoMax">Prezzo Max (€):</label>
	                <input type="number" step="0.01" name="prezzoMax" id="prezzoMax" value="<%= request.getParameter("prezzoMax") != null ? request.getParameter("prezzoMax") : "" %>">
	            </div>
	
	            <button type="submit" class="btn-filtra">Applica Filtri</button>
	            <a href="<%=request.getContextPath()%>/catalogue" class="btn-reset">Rimuovi Filtri</a>
	        </form>
	    </aside>
	
	    <div class="catalogo">
	        <%
	            Map<String, List<Prodotto>> catalogo = (Map<String, List<Prodotto>>) request.getAttribute("catalogo");
	
	            if (catalogo != null && !catalogo.isEmpty()) {
	                for (String categoria : catalogo.keySet()) {
	                    List<Prodotto> prodotti = catalogo.get(categoria);
	        %>
	                    <h3><%= categoria %></h3>
	                    <div class="griglia-prodotti">
	                        <% for (Prodotto prodotto : prodotti) { %>
	                            <div class="prodotto">
	                                <form action="<%=request.getContextPath()%>/productPage" method="POST">
	                                    <input type="hidden" name="productName" value="<%= prodotto.getNome() %>">
	                                    <button type="submit" class="btn-img">
	                                        <img src="<%=request.getContextPath()%><%= prodotto.getUrl_img() %>" alt="<%= prodotto.getNome() %>">
	                                    </button>
	                                </form>
	                                <p class="prodotto-nome"><%= prodotto.getNome() %></p>
	                                <p class="prodotto-prezzo">€ <%= String.format("%.2f", prodotto.getPrezzo()) %></p>
	                            </div>
	                        <% } %>
	                    </div>
	        <%
	                }
	            } else { %>
	            	<p>Nessun prodotto trovato con i filtri selezionati.</p>
	            <% } %>
	    </div>
	</div>

</body>
</html>