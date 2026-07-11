<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Login</title>
	
	<!-- ICON -->
    <link rel="icon" type="image/jpeg" href="${pageContext.request.contextPath}/images/logo.png">

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/login.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/header.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/footer.css">

	<!--  FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<body>
	<div id="container">
	
		<div class="login">
            
            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="Dopamine Shop Logo">
            </div>
		
			<form action="<%= request.getContextPath() %>/login" method="post" onsubmit="return validateForm()">
			
				<% if(request.getAttribute("errore")!=null){ %>
					<p style="color: red; margin-bottom: 2px">Credenziali invalide. Inserire email e password.</p>
				<%} %>
					
				
				<span id="error_email" style="color:red"></span>
				<input type="email" id="email" name="email" placeholder="Email" required>

				<span id="error_password" style="color:red"></span>
				<input type="password" id="password"name="password" placeholder="Password" minlength="8" maxlength="20" required>
				
				<input type="submit" value="Login">
				
			</form>
			
            <p>Non hai un account?</p>
			<h2><a href="${pageContext.request.contextPath}/signup">Registrati</a></h2>
			
			<h2><a href="${pageContext.request.contextPath}"><i class="fa-solid fa-arrow-left-long"></i> HOME PAGE</a></h2>
		</div>
	</div>
	
	<!-- SCRIPTS -->
	<script src="${pageContext.request.contextPath}/scripts/validationScript.js"></script>
	<script src="${pageContext.request.contextPath}/scripts/loginValidation.js"></script>
</body>
</html>