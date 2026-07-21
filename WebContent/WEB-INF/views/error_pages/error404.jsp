<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Pagina non trovata</title>

	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/navigation/footer.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/error_pages/error404.css">

</head>
<body>
	<header>
		<jsp:include page="../navigation/header.jsp"></jsp:include>
	</header>

	<div class="error-container">

		<div class="can">
			<i class="fa-solid fa-bolt spark spark-1"></i>
			<i class="fa-solid fa-bolt spark spark-2"></i>
			<i class="fa-solid fa-bolt spark spark-3"></i>
			<i class="fa-solid fa-martini-glass-citrus can-icon"></i>
		</div>

		<h1>404</h1>
		<h2>Lattina vuota.</h2>
		<p>La pagina che cercavi si è scaricata prima del tempo, esattamente come la tua energia dopo il terzo caffè.</p>
		<p class="sub">Probabilmente il link è sbagliato, oppure abbiamo esaurito le scorte di questa pagina.</p>

		<a href="<%=request.getContextPath()%>" class="btn-back">
			<i class="fa-solid fa-arrow-left-long"></i> Torna alla Home
		</a>

	</div>

	<footer>
		<jsp:include page="../navigation/footer.jsp"></jsp:include>
	</footer>

</body>
</html>