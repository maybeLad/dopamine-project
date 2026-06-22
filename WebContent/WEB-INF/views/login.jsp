<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/login.css">
	
	<!-- Icon -->
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">
	
	
	
<meta charset="UTF-8">
<title>Dopamine Shop - Login</title>
</head>
<body>
	<div id="container">
	
		<!-- Gestione dei campi di input -->
		<div class="login">
            
            <div class="logo-container">
                <img src="<%= request.getContextPath()%>/images/logo.png" alt="Dopamine Shop Logo">
            </div>
		
			<form action="" method="post">
			
				<input type="text" name="email" placeholder="Email" required>
				<!-- Campo per inserire il numero telefonico (Obbligatorio) -->
				<input type="password" name="password" placeholder="Password" required>
				<!--Bottone per inviare i dati -->
				<input type="submit" value="Login">
				
			</form>
			
            <p>Non hai un account?</p>
			<h2><a href="<%= request.getContextPath() %>/signup">Registrati</a></h2>
			
		</div>
	</div>
</body>
</html>