<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Errore</title>

	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/footer.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/errorGeneric.css">

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
			<i class="fa-solid fa-circle-question can-icon"></i>
		</div>

		<h1>Ops.</h1>
		<h2>Qualcosa non va come previsto.</h2>
		<p>È successo un imprevisto che nemmeno una lattina di energy drink può risolvere.</p>
		<p class="sub">Riprova tra un po', oppure torna alla home nel frattempo.</p>

		<a href="<%=request.getContextPath()%>/welcome" class="btn-back">
			<i class="fa-solid fa-arrow-left-long"></i> Torna alla Home
		</a>

	</div>

	<footer>
		<jsp:include page="../navigation/footer.jsp"></jsp:include>
	</footer>

</body>
</html>