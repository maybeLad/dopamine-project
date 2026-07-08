<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dopamine Shop - Catalogo</title>

<link rel="icon" type="image/jpeg"
	href="<%=request.getContextPath()%>/images/logo.png">

<!-- FONTS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Archivo+Black&display=swap"
	rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/header.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/styles/catalogo.css">

</head>
<body>

	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>

	<div class="benvenuto">
		<h2>Benvenuto nel catalogo!</h2>
	</div>

	<div class="catalogo">

		<!-- LATTINE -->
		<h3>Lattine</h3>

		<div class="griglia-prodotti">
			<form action="<%=request.getContextPath()%>/productPage"
				method="POST" class="form-lattine">
				<div class="prodotto">
					<button type="submit" name="productName" value="classic">
						<img src="<%=request.getContextPath()%>/images/classic.png"
							alt="Classic">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="peach">
						<img src="<%=request.getContextPath()%>/images/peach.png"
							alt="Peach">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="lemon">
						<img src="<%=request.getContextPath()%>/images/lemon.png"
							alt="Lemon">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="sugar_free">
						<img src="<%=request.getContextPath()%>/images/sugar_free.png"
							alt="Sugar Free">
					</button>
				</div>
			</form>
		</div>

		<!-- CREATINA -->
		<h3>Creatina</h3>

		<div class="griglia-prodotti">
			<form action="<%=request.getContextPath()%>/productPage"
				method="POST" class="form-lattine">
				<div class="prodotto">
					<button type="submit" name="productName" value="senza_aroma">
						<img src="<%=request.getContextPath()%>/images/creatina1.png"
							alt="Senza Aroma">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="frutti_di_bosco">
						<img src="<%=request.getContextPath()%>/images/fruttidibosco1.png"
							alt="Frutti di Bosco">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="lampone_blu">
						<img src="<%=request.getContextPath()%>/images/lamponeblu1.png"
							alt="Lampone Blu">
					</button>
				</div>
			</form>
		</div>

		<!-- PROTEINE -->
		<h3>Proteine</h3>

		<div class="griglia-prodotti">
			<form action="<%=request.getContextPath()%>/productPage"
				method="POST" class="form-lattine">
				<div class="prodotto">
					<button type="submit" name="productName" value="rainbow">
						<img src="<%=request.getContextPath()%>/images/proteine1.png"
							alt="Rainbow">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="fragola_e_anguria">
						<img src="<%=request.getContextPath()%>/images/proteine2.png"
							alt="Fragola e Anguria">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="lampone_blu">
						<img src="<%=request.getContextPath()%>/images/proteine3.png"
							alt="Lampone Blu">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="arancia">
						<img src="<%=request.getContextPath()%>/images/proteine4.png"
							alt="Arancia">
					</button>
				</div>
			</form>
		</div>

		<!-- BORRACCE -->
		<h3>Borracce</h3>

		<div class="griglia-prodotti">
			<form action="<%=request.getContextPath()%>/productPage"
				method="POST" class="form-lattine">
				<div class="prodotto">
					<button type="submit" name="productName" value="borraccia_black">
						<img src="<%=request.getContextPath()%>/images/borraccianera.png"
							alt="Borraccia Black">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="borraccia_white">
						<img src="<%=request.getContextPath()%>/images/borracciabianca.png"
							alt="Borraccia White">
					</button>
				</div>
			</form>
		</div>

		<!-- MERCH -->
		<h3>Abbigliamento</h3>

		<div class="griglia-prodotti">
			<form action="<%=request.getContextPath()%>/productPage"
				method="POST" class="form-lattine">
				<div class="prodotto">
					<button type="submit" name="productName" value="cappellino">
						<img src="<%=request.getContextPath()%>/images/cappello.png"
							alt="Cappellino">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="maglia_black">
						<img src="<%=request.getContextPath()%>/images/magliettanera1.png"
							alt="Maglia Black">
					</button>
				</div>

				<div class="prodotto">
					<button type="submit" name="productName" value="maglia_white">
						<img src="<%=request.getContextPath()%>/images/magliettabianca1.png"
							alt="Maglia White">
					</button>
				</div>
			</form>
		</div>

	</div>

</body>
</html>