<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop - Novità</title>

	<!-- ICON -->
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">

	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

	<!-- CSS -->
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/footer.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/news.css">

</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<div class="news-container">

		<h1>Novità</h1>

		<div class="news-grid">

			<div class="news-card">
				<div class="news-image">
					<img src="<%= request.getContextPath() %>/images/mango_passion.png" alt="Nuovo gusto in arrivo">
				</div>
				<div class="news-content">
					<span class="news-badge">In arrivo</span>
					<h2>Nuovo gusto Mango Passion</h2>
					<p>Un mix esplosivo di mango e frutto della passione, pensato per darti la carica in modo ancora più intenso.</p>
					<div class="countdown-wrapper">
						<span id="countdown1">--g --:--:--</span>
					</div>
				</div>
			</div>

			<div class="news-card">
				<div class="news-image">
					<img src="<%= request.getContextPath() %>/images/berry_blast.png" alt="Nuovo gusto in arrivo">
				</div>
				<div class="news-content">
					<span class="news-badge">In arrivo</span>
					<h2>Nuovo gusto Berry Blast</h2>
					<p>Frutti di bosco freschi e un tocco acidulo, per chi cerca energia senza rinunciare al gusto.</p>
					<div class="countdown-wrapper">
						<span id="countdown2">--g --:--:--</span>
					</div>
				</div>
			</div>

		</div>

	</div>

	<footer>
		<jsp:include page="footer.jsp"></jsp:include>
	</footer>

	<!-- SCRIPTS -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/countdown.js"></script>

</body>
</html>