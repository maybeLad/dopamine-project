<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Dopamine Shop</title>
	
	<!-- Logo anche nella scheda in alto del browser che cambia in base al tema che ha sul pc -->
	<link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png" media="(prefers-color-scheme: dark)">
    <link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png" media="(prefers-color-scheme: light)">
	<!-- FONTS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css2?family=Archivo+Black&display=swap" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/header.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/index.css">
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/footer.css">
	
	

</head>
<body>

	<section class="section">
		<div class="slider">
			<div class="slide" id="slide">
				<div class="st-first">
					<img alt="first banner" src="<%= request.getContextPath() %>/images/firstBanner.jpg">
				</div>

				<div class="st">
					<img alt="second banner" src="<%= request.getContextPath() %>/images/secondBanner.jpg">
				</div>

				<div class="st">
					<img alt="third banner" src="<%= request.getContextPath() %>/images/thirdBanner.jpg">
				</div>
			</div>
		</div>
	</section>
	
	<section class="can-section">
		<div class="can">
			<a href="<%= request.getContextPath() %>/products/classic"><img alt="Classic" src="<%= request.getContextPath() %>/images/classic.png"></a>
			<h2 id="classic_text">CLASSIC</h2>

		</div>
	
		<div class="can">
			<a href="<%= request.getContextPath() %>/products/peach"><img alt="Peach" src="<%= request.getContextPath() %>/images/peach.png"></a>
			<h2 id="peach_text">PEACH</h2>

		</div>
	
		<div class="can">
			<a href="<%= request.getContextPath() %>/products/lemon"><img alt="Lemon"  id="lemon" src="<%= request.getContextPath() %>/images/lemon.png"></a>
			<h2 id="lemon_text">LEMON</h2>

		</div>
		
		<div class="can">
			<a href="<%= request.getContextPath() %>/products/sugar_free"><img alt="Sugar free" id="sugar_free" src="<%= request.getContextPath() %>/images/sugar_free.png"></a>
			<h2 id="sugar_free_text">SUGAR FREE</h2>
		</div>
	
	</section>
	
	

	<script type="text/javascript">
		var counter = 0;
		var slides = document.querySelectorAll('.st-first, .st');
		var total = slides.length;

		setInterval(function() {
			counter++;
			if (counter >= total) {
				counter = 0;
			}
			var offset = counter * -33.33;
			document.querySelector('.st-first').style.marginLeft = offset + '%';
		}, 5000);
	</script>

</body>
</html>