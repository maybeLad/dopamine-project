<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dopamine Shop - Cambia Metodo di Pagamento</title>

	<!-- ICON -->
	<link rel="icon" type="image/jpeg" href="<%=request.getContextPath()%>/images/logo.png">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/footer.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/form.css">

</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">

	<div id="container">
		<div class="form-card">

			<div class="logo-container">
				<img src="<%=request.getContextPath()%>/images/logo.png" alt="Dopamine Shop Logo">
			</div>

			<h1>Cambia Metodo di pagamento</h1>

			<form id="changePaymentMethodForm" method="POST">

				<span id="error_metodo" style="color:red"></span>
				<select id="metodoPagamento" name="metodoPagamento" required>
					<option value="">Seleziona un metodo</option>
					<option value="Paypal">Paypal</option>
					<option value="Carta">Carta</option>
				</select>

				<div id="cardFields" style="display:none;">

					<span id="error_numeroCarta" style="color:red"></span>
					<input type="text" id="numeroCarta" name="numeroCarta" placeholder="Numero carta" maxlength="19" pattern="[0-9 ]{16,19}">

					<span id="error_scadenzaCarta" style="color:red"></span>
					<input type="text" id="scadenzaCarta" name="scadenzaCarta" placeholder="MM/AA" maxlength="5" pattern="(0[1-9]|1[0-2])\/[0-9]{2}">	

				</div>

				<input type="submit" value="Salva Metodo di Pagamento">
			</form>

			<p id="messaggio"></p>

		</div>
	</div>


	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

	<!-- SCRIPTS -->

	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/validationScript.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/paymentMethodValidation.js"></script>
</body>
</html>