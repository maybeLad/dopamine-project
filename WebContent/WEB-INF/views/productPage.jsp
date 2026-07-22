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
	<title>Dopamine Shop - Prodotto</title>

	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/navigation/footer.css">	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/productPage.css">

</head>
<body>
	<header>
		<jsp:include page="navigation/header.jsp"></jsp:include>
	</header>
		
	<div class="product-container">
    <h1><%= prodotto.getNome() %></h1>

    <div class="product-media">
        <img id="product-picture" alt="Foto prodotto" src="<%= request.getContextPath() %><%= prodotto.getUrl_img() %>">
    </div>

    <div class="product-info">
        <div class="product-price-section">
            <h2>Prezzo</h2>
            <p class="product-price">€<%= String.format("%.2f", prodotto.getPrezzo()) %></p>
        </div>

        <div class="product-actions-group">
            <div class="quantity-selector">
                <button type="button" class="qty-btn" onclick="decreaseQty()">−</button>
                <input type="number" id="quantity" name="quantity" value="1" min="1" max="<%= prodotto.getStock() > 0 ? prodotto.getStock() : 1 %>" readonly>
                <button type="button" class="qty-btn" onclick="increaseQty()">+</button>
            </div>

            <button type="button" class="btn btn-add-cart" onclick="event.preventDefault(); addToCart(<%= prodotto.getId() %>)">
                Aggiungi al carrello
            </button>
        </div>

        <div class="product-description-section">
            <h2>Descrizione</h2>
            <p><%= prodotto.getDescrizione() %></p>
        </div>
    </div>
</div>



	<footer>
		<jsp:include page="navigation/footer.jsp"></jsp:include>
	</footer>
	
	<!-- SCRIPTS -->
	<script src="${pageContext.request.contextPath}/scripts/increaseQuantity.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/addToCart.js"></script>
</body>
</body>
</html>