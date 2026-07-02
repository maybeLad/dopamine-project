<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dopamine Shop - Catalogo</title>

<link rel="icon" type="image/jpeg" href="<%= request.getContextPath() %>/images/logo.png">

<!-- FONTS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Archivo+Black&display=swap" rel="stylesheet">

<!-- CSS -->
<link rel="stylesheet" href="<%= request.getContextPath()%>/styles/header.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/styles/catalogo.css">

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

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/classic.png" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/peach.png" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/lemon.png" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/sugar_free.png" alt="">
        </a>

    </div>

    <!-- CREATINA -->
    <h3>Creatina</h3>

    <div class="griglia-prodotti">

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/creatina1.png" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/fruttidibosco1.png" alt="">
        </a>

    </div>

    <!-- PROTEINE -->
    <h3>Proteine</h3>

    <div class="griglia-prodotti">

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/proteine1.png" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/proteine2.png" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/proteine3.png" alt="">
        </a>

    </div>

    <!-- BORRACCE -->
    <h3>Borracce</h3>

    <div class="griglia-prodotti">

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/?" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/?" alt="">
        </a>

    </div>

    <!-- MERCH -->
    <h3>Merch</h3>

    <div class="griglia-prodotti">

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/?" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/?" alt="">
        </a>

        <a href="#">
            <img src="<%= request.getContextPath()%>/images/?" alt="">
        </a>

    </div>

</div>

</body>
</html>