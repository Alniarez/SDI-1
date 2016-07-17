<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ShareMyTrip :: Error</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
</head>
<body>
	<div class="w3-container">
		<header class="w3-container w3-blue">
			<h1>Error :: ShareMyTrip</h1>
		</header>
		<div class="w3-container w3-red">
			<h3>¡Se ha producido un error!</h3>
			<p>${requestScope.errorMessage}</p>
		</div>
		<a href='${pageContext.request.contextPath}' class="w3-btn w3-blue">Ir
			a Inicio</a>
	</div>
</body>
</html>