<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="jsp/comprobarNavegacion.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>ShareMyTrip :: Datos del viaje ${param.id}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/w3.css">
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>
	<div class="w3-container w3-blue">
		<h1>Datos del viaje ${param.id} :: ShareMyTrip</h1>
		<a href='${pageContext.request.contextPath}/listarViajes' class="w3-btn w3-blue w3-btn-block"><i class="fa fa-arrow-left"></i> Volver</a>
	</div>
	<div class="w3-container">
			<jsp:include page="jsp/tripData.jsp"></jsp:include>
			
			<c:if test="${user != null}">
				<jsp:include page="jsp/tripDataPrivate.jsp"></jsp:include>
			</c:if>

	</div>
	<hr>
	<a href='${pageContext.request.contextPath}/listarViajes' class="w3-btn w3-blue w3-btn-block"><i class="fa fa-arrow-left"></i> Volver</a>

</body>
</html>