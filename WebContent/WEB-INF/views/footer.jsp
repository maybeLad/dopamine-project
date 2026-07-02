<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="footer-container">
    <div class="footer-brand">
        <img src="<%=request.getContextPath()%>/images/logo.png" alt="Dopamine">
    </div>

	<div class="dopamine-scope">
		<p>Dopamine è una realtà che si occupa di energy drink, 
		integratori e abbigliamento brandizzato. Ha deciso di approdare anche online, 
		tramite un e-commerce. L’obiettivo è quello di fornire agli utenti un punto di 
		riferimento per quanto riguarda sia la parte degli energy drink, che sono di varie 
		tipologie e gusti, sia la parte dell’abbigliamento, ovviamente personalizzato con 
		il nome e logo del brand, con lo scopo di entrare tra i big competitor che lavorano 
		nello stesso settore. 
	</div>


    <div class="footer-links">
        <h4>Informazioni</h4>
        <a href="<%= request.getContextPath()%>/info">Chi siamo</a>
        <a href="#">Termini e condizioni</a>
        <a href="#">Privacy Policy</a>
        <a href="#">Cookie Policy</a>
    </div>
    
</div>

<p id="motto_brand">Non solo energia, ma soprattutto concentrazione.</p>

<div class="footer-bottom">
    <p>© 2026 Dopamine Shop. Tutti i diritti riservati.</p>
</div>