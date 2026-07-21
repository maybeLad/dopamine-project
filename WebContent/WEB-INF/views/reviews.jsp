<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="it.dopamine.dao.ReviewDAO" %>
<%@ page import="it.dopamine.dao.UtenteDAO" %>
<%@ page import="it.dopamine.dao.ProdottoDAO" %>
<%@ page import="it.dopamine.model.Recensione" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine shop - Recensioni</title>
	
	<!-- ICON -->
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">
    
    <!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/footer.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/reviews.css">
	
	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	
</head>


<body>

	<header>
		<jsp:include page="navigation/header.jsp"></jsp:include>
	</header>
	
	<div class="review-container">
	
		<%  ReviewDAO reviewDAO = new ReviewDAO(); 
			ProdottoDAO prodottoDAO = new ProdottoDAO();
			UtenteDAO utenteDAO = new UtenteDAO();
			ArrayList<Recensione> recensioni = reviewDAO.getReviews();
		
			if (recensioni.isEmpty()) { %>
	    		<p>Non e' stato possibile trovare alcuna recensione.</p>
	 	<%	} else {
	    		for (Recensione r : recensioni) {
		%>
		
	    <div class="review-card">
   			<div class="review-stars">
		        <% for( int i=0; i < r.getVoto(); i++){%>
		            <i style="color: gold;" class="fa-solid fa-star"></i>
		        <%}
		            for(int i = 5 - r.getVoto(); i > 0; i --){%>
		                <i class="fa-regular fa-star"></i>
		        <%	}
		        %>
    		</div>

    		<p class="review-text"><%= r.getDescrizione() %></p>

    		<p class="review-meta">
        		<%= utenteDAO.getUtente(r.getId_utente()).getNome() %> —
        		<%= prodottoDAO.getProdotto(r.getId_prodotto()).getNome() %>
    		</p>
		</div>
		<%
		    }
		} %>

	</div>
	<footer>
		<jsp:include page="navigation/footer.jsp"></jsp:include>
	</footer>

</body>
</html>