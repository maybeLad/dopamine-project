<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.dopamine.model.Utente" %>
<%@ page import="it.dopamine.model.CarrelloItem" %>
<%@ page import="java.util.List" %>

<%
    Utente u = (Utente) session.getAttribute("utenteLoggato");
    Double totaleObj = (Double) request.getAttribute("totale");
    double totale = 0.0;

    if (totaleObj != null) {
        totale = totaleObj;
    } else {
        List<CarrelloItem> carrello = (List<CarrelloItem>) session.getAttribute("carrello");
        if (carrello != null) {
            for (CarrelloItem item : carrello) {
                totale += item.getProdotto().getPrezzo() * item.getQuantita();
            }
        }
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dopamine Shop - Checkout</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/checkout.css">
</head>
<body>
    <header>
        <jsp:include page="header.jsp"></jsp:include>
    </header>

    <div class="checkout-container">
        <h2>Riepilogo Checkout</h2>

        <% if (u != null) { %>
            <h3>Indirizzo di Spedizione</h3>
            <p><strong>Destinatario:</strong> <%= u.getNome() != null ? u.getNome() : "" %> <%= u.getCognome() != null ? u.getCognome() : "" %></p>
            <p><strong>Indirizzo:</strong> <%= u.getIndirizzo() != null ? u.getIndirizzo() : "Non impostato" %></p>
            <a href="${pageContext.request.contextPath}/user" class="checkout-link-edit">Modifica indirizzo nel profilo</a>

            <hr class="checkout-divider">

            <h3>Metodo di Pagamento Salvato</h3>
            <p><strong>Tipo:</strong> <%= u.getMetodo_pagamento() != null ? u.getMetodo_pagamento() : "Non impostato" %></p>
            <p><strong>Carta che termina con:</strong> **** **** **** <%= u.getCarta_ultime_4_cifre() != null ? u.getCarta_ultime_4_cifre() : "----" %></p>
            <a href="${pageContext.request.contextPath}/user" class="checkout-link-edit">Modifica carta nel profilo</a>

            <hr class="checkout-divider">

            <div class="checkout-totale">
                <span>Totale da Pagare:</span>
                <span class="checkout-totale-badge"><%= String.format("%.2f", totale) %> €</span>
            </div>

            <form action="${pageContext.request.contextPath}/checkout" method="POST">
                <button type="submit" class="btn-conferma-ordine">
                    Conferma e Paga (<%= String.format("%.2f", totale) %> €)
                </button>
            </form>
        <% } else { %>
            <p>Sessione scaduta. Effettua di nuovo il login.</p>
            <a href="${pageContext.request.contextPath}/login">Vai al Login</a>
        <% } %>
    </div>
</body>
</html>