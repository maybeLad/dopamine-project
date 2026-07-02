<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav id="logo">
		<a href="<%= request.getContextPath()%>/welcome"><img alt="Dopamine log" src="<%= request.getContextPath()%>/images/logo.png"></a>
		
		<ul class="center-side">
			<li><a href="<%= request.getContextPath()%>/catalogue">Catalogo</a></li>
			<li><a href="<%= request.getContextPath()%>/news">Novità</a></li>
			<li><a href="<%= request.getContextPath()%>/reviews">Recensioni</a></li>
			<li><a href="<%= request.getContextPath()%>/contacts">Contatti</a></li>
		</ul>
	
		<ul class="right-side">
			<li><a href="<%= request.getContextPath()%>/search"><i class="fa-solid fa-magnifying-glass"></i></a></li>
			<li><a href="<%= request.getContextPath()%>/login"><i class="fa-solid fa-user"></i></a></li>
			<li><a href="<%= request.getContextPath()%>/cart"><i class="fa-solid fa-cart-shopping"></i></a></li>
		</ul>
	</nav>
	
	<h2 id="welcome">Benvenuto nel catalogo!</h2>
	<ul class="center-side">
			<li><a href="<%= request.getContextPath()%>">Lattine</a></li>
			<li><a href="<%= request.getContextPath()%>">Creatina</a></li>
			<li><a href="<%= request.getContextPath()%>">Borracce</a></li>
			<li><a href="<%= request.getContextPath()%>">Merch</a></li>
		</ul>
</body>
</html>