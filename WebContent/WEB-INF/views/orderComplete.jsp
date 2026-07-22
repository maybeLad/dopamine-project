<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Ordine Effettuato</title>

	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/orderComplete.css">

</head>
<body>
	<header>
		<jsp:include page="navigation/header.jsp"></jsp:include>
	</header>

	<div class="order-complete-container">

		<div class="check-circle">
			<i class="fa-solid fa-check"></i>
		</div>

		<h1>Ordine effettuato!</h1>

		<a href="<%=request.getContextPath()%>" class="btn-back">
			Torna alla Home
		</a>

	</div>

</body>
</html>