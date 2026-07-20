<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="it.dopamine.model.CarrelloItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dopamine Shop - Carrello</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/cart.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/header.css">
    <script src="${pageContext.request.contextPath}/scripts/removeCartItems.js" defer></script>
</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
    
    <div id="contenitore-carrello" style="padding: 20px; max-width: 1000px; margin: 0 auto;">
        <h2>Il tuo Carrello</h2>
        
        <%
            List<CarrelloItem> items = (List<CarrelloItem>) request.getAttribute("itemsCarrello");
            Double totale = (Double) request.getAttribute("totaleGenerale");
            
            if (items == null || items.isEmpty()) {
        %>
            <p>Il tuo carrello è attualmente vuoto.</p>
            <button onclick="window.location.href='<%= request.getContextPath() %>/catalogue'">Torna allo shopping</button>
        <%
            } else {
        %>
            <table id="tabella-carrello" style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">
                <thead>
                    <tr style="border-bottom: 2px solid #ccc; text-align: left;">
                        <th>Prodotto</th>
                        <th>Prezzo</th>
                        <th>Quantità</th>
                        <th>Subtotale</th>
                        <th>Azione</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (CarrelloItem item : items) {
                    %>
                        <tr id="prodotto-row-<%= item.getProdotto().getId() %>" style="border-bottom: 1px solid #eee; height: 60px;">
                            <td>
                                <strong><%= item.getProdotto().getNome() %></strong>
                            </td>
                            <td><%= String.format("%.2f", item.getProdotto().getPrezzo()) %> €</td>
                            <td><%= item.getQuantita() %></td>
                            <td><%= String.format("%.2f", item.getSubtotale()) %> €</td>
                            <td>
                                <button type="button" onclick="removeFromCart(<%= item.getProdotto().getId() %>)" style="background: #ff4d4d; color: white; border: none; padding: 5px 10px; cursor: pointer; border-radius: 4px;">
                                    Rimuovi
                                </button>
                            </td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            
            <div style="text-align: right; font-size: 1.2em; margin-bottom: 20px;">
                <strong>Totale Complessivo: </strong>
                <span id="totale-carrello"><%= String.format("%.2f", totale) %> €</span>
            </div>
            
            <div style="text-align: right;">
                <a href="${pageContext.request.contextPath}/catalogo" class="btn" style="margin-right: 10px; background: #666; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px;">Continua lo Shopping</a>
                <a href="${pageContext.request.contextPath}/checkout" class="btn" style="background: #28a745; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; font-weight: bold;">Procedi al Checkout</a>
            </div>
        <%
            }
        %>
    </div>

</body>
</html>