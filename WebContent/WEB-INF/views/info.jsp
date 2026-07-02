<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dopamine Shop - Chi siamo</title>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/header.css">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/styles/info.css">
</head>
<body>
	<header>
		<jsp:include page="header.jsp"></jsp:include>
	</header>
	<div>
		<table>
			<tr>
				<th>Email</th>
				<th>Matricola</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Instagram</th>
			</tr>
			<tr>
				<td>l.defilippo17@studenti.unisa.it</td>
				<td>0512122431</td>
				<td>Luca</td>
				<td>De Filippo</td>
				<td>@lad_gtfz</td>
			</tr>
			<tr>
				<td>f.pesce11@studenti.unisa.it</td>
				<td>0512122050</td>
				<td>Francesco</td>
				<td>Pesce</td>
				<td>@alt_fr4_</td>
			</tr>
		</table>
	</div>
</body>
</html>