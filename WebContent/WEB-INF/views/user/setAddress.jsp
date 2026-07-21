<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Inserisci Indirizzo</title>

	<!-- ICON -->
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">

    <!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/navigation/footer.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/form.css">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>

<body>

<header>
	<jsp:include page="../navigation/header.jsp"></jsp:include>
</header>

<input type="hidden" id="contextPath" value="<%= request.getContextPath() %>">

<div id="container">

	<div class="form-card">

		<div class="logo-container">
			<img src="<%= request.getContextPath() %>/images/logo.png" alt="Dopamine Shop Logo">
		</div>

		<h1>Inserisci Indirizzo</h1>

		<form id="addressForm" onsubmit="return validateForm()">

			<span id="error_via" style="color:red"></span>
			<input type="text" id="via" name="via" placeholder="Via" required minlength="3">

			<span id="error_civico" style="color:red"></span>
			<input type="text" id="civico" name="civico" placeholder="Numero civico" required>

			<span id="error_citta" style="color:red"></span>
			<input type="text" id="citta" name="citta" placeholder="Città" required>

			<span id="error_cap" style="color:red"></span>
			<input type="text" id="cap" name="cap" placeholder="CAP" maxlength="5" required>

			<input type="submit" value="Salva Indirizzo">

		</form>

		<p id="messaggio"></p>

	</div>

</div>

<footer>
	<jsp:include page="../navigation/footer.jsp"></jsp:include>
</footer>

<script src="<%= request.getContextPath()%>/scripts/validationScript.js"></script>
<script src="<%= request.getContextPath()%>/scripts/addressValidation.js"></script>

</body>
</html>