<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Sign Up</title>

	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">
	
	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/navigation/footer.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/form.css">
	
	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>
	<header>
		<jsp:include page="../navigation/header.jsp"></jsp:include>
	</header>
	
	<div id="container">
	
		<div class="form-card">

            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="Dopamine Shop Logo">
            </div>

			<form action="<%= request.getContextPath() %>/signup" method="post" onsubmit="return validateForm()">
			
				<span id="error_nome" style="color:red"></span>
				<input type="text" id="nome" name="nome" placeholder="Nome" required>
				
				<span id="error_cognome" style="color:red"></span>
				<input type="text" id="cognome" name="cognome" placeholder="Cognome" required>
				
				<span id="error_email" style="color:red"></span>
				<input type="email" id="email" name="email" placeholder="Email" required>
				
				<span id="error_telefono" style="color:red"></span>
				<input type="tel" id="telefono" name="mobilenumber" placeholder="Telefono" pattern="3[0-9]{9}" required>
				
				<span id="error_password" style="color:red"></span>
				<input type="password" id="password" name="password" placeholder="Password" minlength="8" maxlength="20" required>
				
				<input type="submit" value="Registrati">
				
			</form>
			
            <p>Hai già un account?</p>
			<h2><a href="${pageContext.request.contextPath}/login">Accedi</a></h2>
		
			<h2><a href="${pageContext.request.contextPath}"><i class="fa-solid fa-arrow-left-long"></i> HOME PAGE</a></h2>
			
		</div>
	</div>
	
	<!-- Scripts -->
	<script src="${pageContext.request.contextPath}/scripts/validationScript.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/signupValidation.js"></script>
	
	<footer>
		<jsp:include page="../navigation/footer.jsp"></jsp:include>
	</footer>
	
</body>
</html>