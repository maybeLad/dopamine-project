<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="it.dopamine.model.Prodotto"%>



<%
Prodotto prodotto = (Prodotto) request.getAttribute("productName");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Logo anche nella scheda in alto del browser -->
<link rel="icon" type="image/jpeg"
	href="<%=request.getContextPath()%>/images/logo.png">

<!-- FONTS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

<!-- CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/header.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/footer.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/styles/productPage.css">

<title>Dopamine Shop</title>
</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	<div class="product-container">
		<div>
			<img id="product-picture" alt="Foto lattina classic"
				src="<%=request.getContextPath()%>/images/<%=prodotto.getNome()%>.png">

		</div>

		<div class="product-info">
			<h1><%=prodotto.getNome()%></h1>

			<h2>Prezzo</h2>
			<p class="product-price">
				€<%=prodotto.getPrezzo()%></p>

			<h2>Descrizione</h2>
			<p><%=prodotto.getDescrizione()%></p>

			<div class="product-actions">
				<button class="btn btn-add-cart">Aggiungi al carrello</button>
				<button class="btn btn-buy-now">Compra ora</button>
			</div>
		</div>
		



	</div>



	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>
</body>
</html>