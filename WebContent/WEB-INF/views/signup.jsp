<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/signup.css">
	
<meta charset="UTF-8">
<title>Dopamine Shop - Sign Up</title>
</head>
<body>
	<div id="container">
	
		<!-- Gestione dei campi di input -->
		<div class="signup">

            <div class="logo-container">
                <img src="${pageContext.request.contextPath}/images/logo.png" alt="Dopamine Shop Logo">
            </div>

			<form action="" method="post">
			
				<!-- Campo per inserire il nome (Obbligatorio) -->
				<input type="text" name="nome" placeholder="Nome" required>
				<!-- Campo per inserire il cognome (Obbligatorio) -->
				<input type="text" name="cognome" placeholder="Cognome" required>
				<!-- Campo per inserire l'email (Obbligatorio) -->
				<input type="email" name="email" placeholder="Email" required>
				<!-- Campo per inserire il numero telefonico (Obbligatorio) -->
				<input type="tel" name="mobilenumber" placeholder="Telefono" required>
				<!-- Campo per inserire la password (Obbligatorio) -->
				<input type="password" name="password" placeholder="Password" required>
				<!--Bottone per inviare i dati -->
				<input type="submit" value="Registrati">
				
			</form>
			
            <p>Hai già un account?</p>
			<h2><a href="${pageContext.request.contextPath}/login">Accedi</a></h2>
			
		</div>
	</div>
</body>
</html>