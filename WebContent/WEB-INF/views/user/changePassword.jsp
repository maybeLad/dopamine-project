<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dopamine Shop - Cambia Password</title>

	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">
	
	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/navigation/footer.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/form.css">

</head>
<body>
	<header>
		<jsp:include page="../navigation/header.jsp"></jsp:include>
	</header>

	<input type="hidden" id="contextPath"value="<%=request.getContextPath()%>">

	<div id="container">
		<div class="form-card">

			<div class="logo-container">
				<img src="<%=request.getContextPath()%>/images/logo.png" alt="Dopamine Shop Logo">
			</div>

			<h1>Cambia Password</h1>

			<form id="changePasswordForm" onsubmit="return validateForm()">

				<span id="error_oldPassword" style="color: red"></span>
				<input type="password" id="oldPassword" placeholder="Password attuale" required>
				
				<span id="error_newPassword" style="color: red"></span>
				<input type="password" id="newPassword" placeholder="Nuova password" minlength="8" maxlength="20" required> 
				
				<span id="error_confirm" style="color: red"></span> 
				<input type="password" id="confirmPassword" placeholder="Conferma password" required>
				
				<input type="submit" value="Cambia Password">
			</form>

			<p id="messaggio"></p>

		</div>
	</div>


	<footer>
		<jsp:include page="../navigation/footer.jsp"></jsp:include>
	</footer>

	<!-- SCRIPTS -->
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validationScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/changePassword.js"></script>
</body>
</html>