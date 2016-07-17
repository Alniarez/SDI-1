<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>ShareMyTrip :: Inicio</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>
	<div class="w3-container">
		<header class="w3-container w3-blue">
			<h1>ShareMyTrip</h1>
		</header>
		<!-- Contenedor principal -->
		<div class="w3-container">
			<div class="w3-btn-group">
		
			<!--  El usuario no está identificado -->
			<c:if test="${user == null}">
				<jsp:include page="jsp/login_form.jsp"></jsp:include>
			</c:if>

			<!--  El usuario sí está identificado -->
			<c:if test="${user != null}">
				<jsp:include page="jsp/user_services.jsp"></jsp:include>
			</c:if>

			<!-- Siempre visible-->
			<a id="listarViajes" href="listarViajes" class="w3-btn w3-blue w3-large w3-padding"><i class="fa fa-automobile" style="color:white;"></i> Ver próximos viajes</a>
			</div>
			
			<p>Es Vd el usuario número: ${contador}</p>
		</div>

		<!-- Mensajes -->
		<jsp:include page="jsp/message.jsp"></jsp:include>
		
	</div>
</body>
</html>